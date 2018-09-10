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
    inputSize = ascqm_train_inputs.get_shape().as_list()[1]
    ascqm_saver.restore(ascqmSession, tf.train.latest_checkpoint(ascqmPath))

    @app.route("/ascqm", methods=['POST', 'GET'])
    def predictAscqm():
        r = requests.post("http://parser:8080/parse", data={'source': request.get_json()['source']})
        data = r.json()
        for i, methodDef in enumerate(data):
            if len(data[i]['tokens']) <= inputSize:
                arr = np.array([data[i]['tokens']])
                input = np.pad(arr, ((0,0), (0, inputSize-len(data[i]['tokens']))), 'constant')
                seqlen = np.array([len(data[i]['tokens'])])
                pred = ascqmSession.run(ascqm_prediction, feed_dict={ascqm_train_inputs: input, ascqm_seqlen: seqlen})
                data[i]['prediction'] = pred.tolist()[0]
        json_data = json.dumps(data)
        return json_data

rsavePath = '../trained/code-fracz-645/rscqm'

#batch_before, batch_after, seqlen_before, seqlen_after, batch_labels = dataset.next(5)
#print("BEFORE:\n", batch_before, seqlen_before, "\nAFTER:\n", batch_after, seqlen_after, "\nLABELS:\n", batch_labels)


############################################ RNN

"""
rscqmGraph = tf.Graph()
rscqmSession = tf.Session(graph=rscqmGraph)
with rscqmGraph.as_default():
    print("model meta")
    rscqmSaver = tf.train.import_meta_graph(rsavePath + '/model.meta')

    train_inputs_before = rscqmGraph.get_tensor_by_name("train_inputs_before:0")
    train_inputs_after = rscqmGraph.get_tensor_by_name("train_inputs_after:0")
    train_outputs = rscqmGraph.get_tensor_by_name("train_outputs:0")
    seqlen_before = rscqmGraph.get_tensor_by_name("seqlen_before:0")
    seqlen_after = rscqmGraph.get_tensor_by_name("seqlen_after:0")
    prediction = rscqmGraph.get_tensor_by_name("prediction:0")

#    print(str([n.name for n in tf.get_default_graph().as_graph_def().node]))

    print("model restore")
    rscqmSaver.restore(rscqmSession, tf.train.latest_checkpoint(rsavePath))
    print("savePath")
#    all_vars = tf.get_collection('vars')
#    for v in all_vars:
#        v_ = sess.run(v)
#        print(v_)
    print("testin")
    test_before, test_after, test_seqlen_before, test_seqlen_after = rdataset.test()

    @app.route("/rscqm", methods=['POST', 'GET'])
    def predictRscqm():
        start = time.time()

        #data = request.data.decode("utf-8")
        #if data == "":
        #    params = request.form
        #    x_in = json.loads(params['x'])
        #else:
        #    params = json.loads(data)
        #    x_in = params['x']

        ##################################################
        # Tensorflow part
        ##################################################
        pred = rscqmSession.run(prediction, feed_dict={train_inputs_before: test_before,
                                                             train_inputs_after: test_after,
                                                             seqlen_before: test_seqlen_before,
                                                             seqlen_after: test_seqlen_after})
        ##################################################
        # END Tensorflow part
        ##################################################

        json_data = json.dumps({'pred': pred.tolist()})
        print("Time spent handling the request: %f" % (time.time() - start))

        return json_data

    predictRscqm()
"""
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
