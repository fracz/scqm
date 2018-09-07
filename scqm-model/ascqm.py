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

#from flask_cors import CORS
from flask import Flask, request

def parseArguments():
    parser = argparse.ArgumentParser()
    parser.add_argument("datasetName", help="Dataset name.")
    parser.add_argument("-nh", "--numHidden", help="Num hidden", type=int, default=128)
    parser.add_argument("-s", "--steps", help="Training steps.", type=int, default=20)
    parser.add_argument("-b", "--batchSize", help="Batch size.", type=int, default=64)
    parser.add_argument("-d", "--displayStep", help="Display step.", type=int, default=10)
    parser.add_argument("-t", "--tokensCount", help="Vocabulary size.", type=int, default=129)
    args = parser.parse_args()
    return args

args = parseArguments()

####################### PARAMS
vocabulary_size = args.tokensCount # liczba tokenow w kodzie
embedding_size = 100 # rozmiar wektora wejsciowego (arbitralny chyba?)
num_hidden = args.numHidden
num_classes = 2
training_steps = args.steps
batch_size = args.batchSize
display_step = args.displayStep
learning_rate = 1e-4

################# DATA INPUT

datasetName = args.datasetName

print("""
*****************************************************
Dataset: {}
Num hidden: {}, Steps: {}, Display step: {}
*****************************************************

""".format(datasetName, num_hidden, training_steps, display_step))

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

print(savePath)
with tf.Session(config=config) as sess:
    print("model meta")
    new_saver = tf.train.import_meta_graph(savePath + '/model.meta')

    graph = tf.get_default_graph()
    train_inputs = graph.get_tensor_by_name("train_inputs:0")
    print(tf.shape(train_inputs)[0])
    train_outputs = graph.get_tensor_by_name("train_outputs:0")
    seqlen = graph.get_tensor_by_name("seqlen:0")
    prediction = graph.get_tensor_by_name("prediction:0")

#    print(str([n.name for n in tf.get_default_graph().as_graph_def().node]))

    print("model restore")
    new_saver.restore(sess, tf.train.latest_checkpoint(savePath))
    print("savePath")
#    all_vars = tf.get_collection('vars')
#    for v in all_vars:
#        v_ = sess.run(v)
#        print(v_)
    print("testin")
    test_x, test_seqlen = dataset.test()

    print("Prediction:", sess.run(prediction, feed_dict={train_inputs: test_x,
                                                            seqlen: test_seqlen}))

    app = Flask(__name__)
    #cors = CORS(app)
    @app.route("/api/predict", methods=['POST', 'GET'])
    def predict():
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
        pred = sess.run(prediction, feed_dict={train_inputs: test_x, seqlen: test_seqlen})
        ##################################################
        # END Tensorflow part
        ##################################################

        json_data = json.dumps({'pred': pred.tolist()})
        print("Time spent handling the request: %f" % (time.time() - start))

        return json_data
    app.run(host='0.0.0.0')
