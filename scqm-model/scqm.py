#!/usr/local/bin/python

import tensorflow as tf
from tensorflow.contrib import rnn
import numpy as np
import csv
import math
from random import shuffle
import sys
import os
import argparse, json, time
import requests

from flask import Flask, request, send_from_directory

app = Flask(__name__)

ascqmPath = '../trained/code-fracz-645/ascqm-500'
ascqmGraph = tf.Graph()
ascqmSession = tf.Session(graph=ascqmGraph)

with ascqmGraph.as_default():
    ascqm_saver = tf.train.import_meta_graph(ascqmPath + '/model.meta')
    ascqm_train_inputs = ascqmGraph.get_tensor_by_name("train_inputs:0")
    ascqm_train_outputs = ascqmGraph.get_tensor_by_name("train_outputs:0")
    ascqm_seqlen = ascqmGraph.get_tensor_by_name("seqlen:0")
    ascqm_prediction = ascqmGraph.get_tensor_by_name("prediction:0")
    ascqmInputSize = ascqm_train_inputs.get_shape().as_list()[1]
    ascqm_saver.restore(ascqmSession, tf.train.latest_checkpoint(ascqmPath))

    @app.route("/ascqm", methods=['POST', 'GET'])
    def predictAscqm():
        r = requests.post("http://parser:8080/parse", data={'source': request.get_json()['source']})
        data = r.json()
        for i, methodDef in enumerate(data):
            if len(data[i]['tokens']) <= ascqmInputSize:
                arr = np.array([data[i]['tokens']])
                input = np.pad(arr, ((0,0), (0, ascqmInputSize-len(data[i]['tokens']))), 'constant')
                seqlen = np.array([len(data[i]['tokens'])])
                pred = ascqmSession.run(ascqm_prediction, feed_dict={ascqm_train_inputs: input, ascqm_seqlen: seqlen})
                data[i]['prediction'] = pred.tolist()[0]
        json_data = json.dumps(data)
        return json_data

rscqmPath = '../trained/code-fracz-645/rscqm'
rscqmGraph = tf.Graph()
rscqmSession = tf.Session(graph=rscqmGraph)

with rscqmGraph.as_default():
    rscqmSaver = tf.train.import_meta_graph(rscqmPath + '/model.meta')
    train_inputs_before = rscqmGraph.get_tensor_by_name("train_inputs_before:0")
    train_inputs_after = rscqmGraph.get_tensor_by_name("train_inputs_after:0")
    train_outputs = rscqmGraph.get_tensor_by_name("train_outputs:0")
    seqlen_before = rscqmGraph.get_tensor_by_name("seqlen_before:0")
    seqlen_after = rscqmGraph.get_tensor_by_name("seqlen_after:0")
    prediction = rscqmGraph.get_tensor_by_name("prediction:0")
    rscqmSaver.restore(rscqmSession, tf.train.latest_checkpoint(rscqmPath))
    rscqmInputSize = train_inputs_before.get_shape().as_list()[1]

    @app.route("/rscqm", methods=['POST', 'GET'])
    def predictRscqm():
        jsonBefore = requests.post("http://parser:8080/parse", data={'source': request.get_json()['sourceBefore']}).json()
        jsonAfter = requests.post("http://parser:8080/parse", data={'source': request.get_json()['sourceAfter']}).json()
        result = {
            'astBefore': jsonBefore,
            'astAfter': jsonAfter,
            'predictions': []
        }

        for i, methodDef in enumerate(jsonBefore):
            methodDefsBefore = [m for m in jsonBefore if m['methodName'] == methodDef['methodName']]
            methodDefsAfter = [m for m in jsonAfter if m['methodName'] == methodDef['methodName']]
            if len(methodDefsBefore) == len(methodDefsAfter) and len(methodDefsBefore) == 1 and methodDefsBefore[0]['tokens'] != methodDefsAfter[0]['tokens'] and len(methodDefsBefore[0]['tokens']) <= rscqmInputSize and len(methodDefsAfter[0]['tokens']) <= rscqmInputSize:
                arrBefore = np.array([methodDefsBefore[0]['tokens']])
                arrAfter = np.array([methodDefsAfter[0]['tokens']])
                inputBefore = np.pad(arrBefore, ((0,0), (0, rscqmInputSize-len(methodDefsBefore[0]['tokens']))), 'constant')
                inputAfter = np.pad(arrAfter, ((0,0), (0, rscqmInputSize-len(methodDefsAfter[0]['tokens']))), 'constant')
                seqlenBefore = np.array([len(methodDefsBefore[0]['tokens'])])
                seqlenAfter = np.array([len(methodDefsAfter[0]['tokens'])])
                pred = rscqmSession.run(prediction, feed_dict={train_inputs_before: inputBefore,
                                                                     train_inputs_after: inputAfter,
                                                                     seqlen_before: seqlenBefore,
                                                                     seqlen_after: seqlenAfter})
                result['predictions'].append({'methodName': methodDef['methodName'], 'methodBefore': methodDefsBefore[0], 'methodAfter': methodDefsAfter[0], 'prediction': pred.tolist()[0]});
        json_data = json.dumps(result)
        return json_data

@app.route("/parse", methods=['POST'])
def parser():
    r = requests.post("http://parser:8080/parse", data={'source': request.form.get('source')})
    data = r.json()
    for i, methodDef in enumerate(data):
        arr = np.array([data[i]['tokens']])
        data[i]['zeros'] = np.pad(arr, ((0,0), (0, max(0, 200-len(arr)))), 'constant').tolist()
    return json.dumps(data)

@app.route("/", methods=['GET'])
def index():
    return send_from_directory('.', 'index.html')

app.run(host='0.0.0.0')
