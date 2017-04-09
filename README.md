# LDAModel
### A Java Implementation of Latent Dirichlet Allocation (LDA) using Gibbs Sampling for Parameter Estimation and Inference

# Usage 
#### Command Line & Input Parameters
`-est [-alpha <double>] [-beta <double>] [-ntopics <int>] [-niters <int>] [-savestep <int>] [-twords <int>] –dir <string> -dfile <string>`

#### Example 
`-est -alpha 0.5 -beta 0.1 -ntopics 100 -niters 1000 -savestep 100 -twords 20 -dfile /input/newdocs.dat -dir /<path to project>/LDAModel/models/casestudy`

_here, (parameters in [ ] are optional):_
* `-est:` Estimate the LDA model from scratch
* `-alpha <double>:` The value of alpha, hyper-parameter of LDA. The default value of alpha is 50 / K (K is the the number of topics). See  [Griffiths04] for a detailed discussion of choosing alpha and beta values.
* `-beta <double>:` The value of beta, also the hyper-parameter of LDA. Its default value is 0.1
* `-ntopics <int>:` The number of topics. Its default value is 100. This depends on the input dataset.
* `-niters <int>:` The number of Gibbs sampling iterations. The default value is 2000.
* `-savestep <int>:` The step (counted by the number of Gibbs sampling iterations) at which the LDA model is saved to hard disk. The default value is 200.
* `-twords <int>:` The number of most likely words for each topic. The default value is zero. If you set this parameter a value larger than zero, e.g., 20, It will print out the list of top 20 most likely words per each topic each time it save the model to hard disk according to the parameter savestep above.
* `-dir <string>:` The input training data directory
* `-dfile <string>:` The input training data file.

# Output
Outputs will include the following sampling estimation files:
* <model_name>.others
* <model_name>.phi 
* <model_name>.theta
* <model_name>.tassign
* <model_name>.twords

*in which,*

*<model_name>:* is the name of a LDA model corresponding to the time step it was saved on the hard disk. For example, the name of the model was saved at the sampling iteration 400th will be model-00400. Similarly, the model was saved at the 1200th iteration is model-01200. The model name of the last  sampling iteration is model-final.

*<model_name>.others:* This file contains some parameters of LDA model, such as:
alpha=?
beta=?
ntopics=? # i.e., number of topics
ndocs=? # i.e., number of documents
nwords=? # i.e., the vocabulary size
liter=? # i.e., the sampling iteration at which the model was saved

*<model_name>.phi:* This file contains the word-topic distributions, i.e., p(wordw|topict). Each line is a topic, each column is a word in the vocabulary

*<model_name>.theta:* This file contains the topic-document distributions, i.e., p(topict|documentm). Each line is a document and each column is a topic.

*<model_name>.tassign:* This file contains the topic assignments for words in training data. Each line is a document that consists of a list of `<wordij>:<topic of wordij>`

*<model_file>.twords:* This file contains twords most likely words of each topic. twords is specified in the command.

*wordmap.txt:* It also saves a file called wordmap.txt that contains the maps between words and word's IDs (integer). This is because it works directly with integer IDs of words/terms inside instead of text strings.

# License
LDAModel is released under the [GNU General Public License v3.0](https://github.com/vikrantkakad/LDAModel/blob/master/LICENSE).

# References
* [JGibbLDA](http://jgibblda.sourceforge.net/)
