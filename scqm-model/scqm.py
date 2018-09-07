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

#from flask_cors import CORS
from flask import Flask, request, send_from_directory

################# DATA INPUT

savePath = '../trained/code-fracz-645/ascqm'
if not os.path.exists(savePath):
    os.makedirs(savePath)

class RefactorDataset():
    def __init__(self):
        self.data = np.genfromtxt('../scqm-input/input.csv', delimiter=',')
        self.seqlen = np.genfromtxt('../scqm-input/lengths.csv', delimiter=',')
        self.max_seqlen = len(self.data[0])
        self.test_len = math.floor(len(self.data))
        print("Dataset read success! Size: " + str(self.max_seqlen) + 'x' + str(len(self.data)))

    def test(self):
        return self.data, self.seqlen

dataset = RefactorDataset()

############################################ RNN

config = tf.ConfigProto(
#    device_count = {'GPU': 2}
#    , log_device_placement=True
)

app = Flask(__name__)
"""
print(savePath)
ascqmGraph = tf.Graph()
ascqmSession = tf.Session(graph=ascqmGraph)
print("model meta")

with ascqmGraph.as_default():
    ascqm_saver = tf.train.import_meta_graph(savePath + '/model.meta')

    ascqm_train_inputs = ascqmGraph.get_tensor_by_name("train_inputs:0")
    ascqm_train_outputs = ascqmGraph.get_tensor_by_name("train_outputs:0")
    ascqm_seqlen = ascqmGraph.get_tensor_by_name("seqlen:0")
    ascqm_prediction = ascqmGraph.get_tensor_by_name("prediction:0")

#    print(str([n.name for n in tf.get_default_graph().as_graph_def().node]))

    print("model restore")
    ascqm_saver.restore(ascqmSession, tf.train.latest_checkpoint(savePath))

    print("testin")
    ascqm_test_x, ascqm_test_seqlen = dataset.test()

    #cors = CORS(app)
    @app.route("/ascqm", methods=['POST', 'GET'])
    def predictAscqm():
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
        pred = ascqmSession.run(ascqm_prediction, feed_dict={ascqm_train_inputs: ascqm_test_x, ascqm_seqlen: ascqm_test_seqlen})
        ##################################################
        # END Tensorflow part
        ##################################################

        json_data = json.dumps({'pred': pred.tolist()})
        print("Time spent handling the request: %f" % (time.time() - start))

        return json_data
    predictAscqm()

rsavePath = '../trained/code-fracz-645/rscqm'
if not os.path.exists(savePath):
    os.makedirs(savePath)

class RefactorDatasetr():
    def __init__(self):
        self.data_before = np.genfromtxt('../scqm-input/input-before.csv', delimiter=',')
        self.data_after = np.genfromtxt('../scqm-input/input-after.csv', delimiter=',')
        self.lengths_before = np.genfromtxt('../scqm-input/lengths-before.csv', delimiter=',')
        self.lengths_after = np.genfromtxt('../scqm-input/lengths-after.csv', delimiter=',')
        self.max_seqlen = len(self.data_before[0])
        print("Input size: " + str(self.max_seqlen) + 'x' + str(len(self.data_before)))

    def test(self):
        return self.data_before, self.data_after, self.lengths_before, self.lengths_after

rdataset = RefactorDatasetr()

#batch_before, batch_after, seqlen_before, seqlen_after, batch_labels = dataset.next(5)
#print("BEFORE:\n", batch_before, seqlen_before, "\nAFTER:\n", batch_after, seqlen_after, "\nLABELS:\n", batch_labels)


############################################ RNN
"""
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
    return r.text

@app.route("/", methods=['GET'])
def index():
    return send_from_directory('.', 'index.html')

app.run(host='0.0.0.0')
