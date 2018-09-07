import tensorflow as tf
from tensorflow.contrib import rnn
import numpy as np
import csv
import math
from random import shuffle
import sys
import os
import argparse
import random
from datetime import datetime

random.seed(datetime.now())

def parseArguments():
    parser = argparse.ArgumentParser()
    parser.add_argument("datasetName", help="Dataset name.")
    parser.add_argument("-nh", "--numHidden", help="Num hidden", type=int, default=128)
    parser.add_argument("-s", "--steps", help="Training steps.", type=int, default=50000)
    parser.add_argument("-b", "--batchSize", help="Batch size.", type=int, default=64)
    parser.add_argument("-d", "--displayStep", help="Display step.", type=int, default=200)
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
restore = False

################# DATA INPUT

datasetName = args.datasetName

print("""
*****************************************************
Dataset: {}
Num hidden: {}, Steps: {}, Display step: {}
*****************************************************

""".format(datasetName, num_hidden, training_steps, display_step))

savePath = '../trained/code-fracz-645/rscqm'
if not os.path.exists(savePath):
    os.makedirs(savePath)

class RefactorDataset():
    def __init__(self):
        self.data_before = np.genfromtxt('../scqm-input/input-before.csv', delimiter=',')
        self.data_after = np.genfromtxt('../scqm-input/input-after.csv', delimiter=',')
        self.lengths_before = np.genfromtxt('../scqm-input/lengths-before.csv', delimiter=',')
        self.lengths_after = np.genfromtxt('../scqm-input/lengths-after.csv', delimiter=',')
        self.max_seqlen = len(self.data_before[0])
        print("Input size: " + str(self.max_seqlen) + 'x' + str(len(self.data_before)))

    def test(self):
        return self.data_before, self.data_after, self.lengths_before, self.lengths_after

dataset = RefactorDataset()

#batch_before, batch_after, seqlen_before, seqlen_after, batch_labels = dataset.next(5)
#print("BEFORE:\n", batch_before, seqlen_before, "\nAFTER:\n", batch_after, seqlen_after, "\nLABELS:\n", batch_labels)


############################################ RNN

config = tf.ConfigProto(
#    device_count = {'GPU': 2}
#    , log_device_placement=True
)



with tf.Session(config=config) as sess:
    print("model meta")
    new_saver = tf.train.import_meta_graph(savePath + '/model.meta')

    graph = tf.get_default_graph()
    train_inputs_before = graph.get_tensor_by_name("train_inputs_before:0")
    train_inputs_after = graph.get_tensor_by_name("train_inputs_after:0")
    train_outputs = graph.get_tensor_by_name("train_outputs:0")
    seqlen_before = graph.get_tensor_by_name("seqlen_before:0")
    seqlen_after = graph.get_tensor_by_name("seqlen_after:0")
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
    test_before, test_after, test_seqlen_before, test_seqlen_after = dataset.test()

    print("Prediction:", sess.run(prediction, feed_dict={train_inputs_before: test_before,
                                                         train_inputs_after: test_after,
                                                         seqlen_before: test_seqlen_before,
                                                         seqlen_after: test_seqlen_after}))
