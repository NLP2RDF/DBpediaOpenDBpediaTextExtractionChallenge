# LinkedHypernymDataset in Docker

A docker image of the current LHD extraction framework with all required dependencies (Gate, TreeTagger, etc.) can be created by a following docker build script:

```docker build -t lhd:latest https://github.com/KIZI/LinkedHypernymsDataset.git#:docker```

After the docker image has been successfully built, you can use the image for running of an extraction process by this command:

```docker run --name lhd -d [-v </path/to/host/output>:/root/LinkedHypernymsDataset/data/output] --env-file <path-to-env-vars-file> lhd <language(en|de|nl)> <dbpedia-version>```

* __\<path-to-env-vars-file\>__ is a path to a file which contains required environment variables. The variables should contain URL links to DBpedia datasets that are required at the input. Supported dataset formats are N-Triples (.nt) and Turtle (.ttl) which may be compressed by GZIP (.gz) or BZIP2 (.bz2).
  * LHD_ONTOLOGY_URL=http://path/to/dbpedia.owl(.bz2|.gz)?
  * LHD_INSTANCE_TYPES_URL=http://path/to/lang-instance-types.(ttl|nt)(.bz2|.gz)?
  * LHD_INSTANCE_TYPES_EN_URL=http://path/to/en-instance-types.(ttl|nt)(.bz2|.gz)?
  * LHD_INSTANCE_TYPES_TRANSITIVE_URL=http://path/to/lang-instance-types-transitive.(ttl|nt)(.bz2|.gz)?
  * LHD_INSTANCE_TYPES_TRANSITIVE_EN_URL=http://path/to/en-instance-types-transitive.(ttl|nt)(.bz2|.gz)?
  * LHD_INTERLANGUAGE_LINKS_EN_URL=http://path/to/en-interlanguage-links.(ttl|nt)(.bz2|.gz)?
  * LHD_DISAMBIGUATIONS_URL=http://path/to/lang-disambiguations.(ttl|nt)(.bz2|.gz)?
  * LHD_SHORT_ABSTRACTS_URL=http://path/to/lang-short-abstracts.(ttl|nt)(.bz2|.gz)?
* __\<language\>__ is a selected language for which linked hypernym datasets are going to be created. Supported languages are English (en), German (de) and Dutch (nl).
* __\<dbpedia-version\>__ is a dbpedia version which is associated with the actual LHD extraction process.

Example:

```docker run --name lhd -d --env-file examples/datasets_en lhd en 2015-10```

or

```docker run --name lhd -d -v /tmp/output:/root/LinkedHypernymsDataset/data/output --env-file examples/datasets_en lhd en 2015-10```

After running an LHD docker container from the image, the extraction process is being in progress. It can take several hours or days - it depends on the number of available cores and the size of input datasets. After the completion of the extraction process, the docker container will contain all linked hypernym datasets for the selected language that are placed in the data/output directory. It only remains to copy datasets from the container to your local disk for other purposes (you can specify mounting of this directory to the host by volume settings):

```docker cp lhd:/root/LinkedHypernymsDataset/data/output ./```

The output directory will be copied to your local disk. It contains basic LHD datasets with other auxiliary files that have been created in different steps of the extraction process. Most important datasets, that you are probably looking for, are:

* \<lang\>.lhd.core.\<version\>.nt
* \<lang\>.lhd.raw.\<version\>.nt
* \<lang\>.lhd.extension.\<version\>.nt
* \<lang\>.lhd.inference.\<version\>.nt

See docs - [result section](https://github.com/KIZI/LinkedHypernymsDataset#results). With regard to this you may prefer these copying commands (*\<lang\>* is a selected language and *\<version\>* is a used dbpedia version):

```
docker cp lhd:/root/LinkedHypernymsDataset/data/output/<lang>.lhd.core.<version>.nt ./
docker cp lhd:/root/LinkedHypernymsDataset/data/output/<lang>.lhd.raw.<version>.nt ./
docker cp lhd:/root/LinkedHypernymsDataset/data/output/<lang>.lhd.extension.<version>.nt ./
docker cp lhd:/root/LinkedHypernymsDataset/data/output/<lang>.lhd.inference.<version>.nt ./
```

