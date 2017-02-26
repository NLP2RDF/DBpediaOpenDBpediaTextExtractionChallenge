#!/bin/bash

export LANGUAGES=(en de es fr it ja ko nl pl pt)
export DATA_SERVER=downloads.dbpedia.org/2016-04/ext/nif-abstracts/

cd /home/developer/data

git clone https://github.com/NLP2RDF/DBpediaOpenDBpediaTextExtractionChallenge.git
cd DBpediaOpenDBpediaTextExtractionChallenge
mvn clean install

cd /home/developer/data


for ((i=0;i<${#LANGUAGES[@]};++i)); do

    LANGUAGE=${LANGUAGES[i]}

    echo "Downloading abstract context in ${LANGUAGE}..."
    wget "http://$DATA_SERVER/${LANGUAGE}/nif-abstract-context_${LANGUAGE}.ttl.bz2"

    echo "Downloading page structure in ${LANGUAGE}..."
    wget "http://$DATA_SERVER/${LANGUAGE}/nif-page-structure_${LANGUAGE}.ttl.bz2"

    echo "Downloading text links in ${LANGUAGE}..."
    wget "http://$DATA_SERVER/${LANGUAGE}/nif-text-links_${LANGUAGE}.ttl.bz2 "


    echo "Unpacking abstracts..."
    bzip2 -d nif-abstract-context_${LANGUAGE}.ttl.bz2

    echo "Unpacking structure..."
    bzip2 -d nif-page-structure_${LANGUAGE}.ttl.bz2

    echo "Unpacking links..."
    bzip2 -d nif-text-links_${LANGUAGE}.ttl.bz2

done
