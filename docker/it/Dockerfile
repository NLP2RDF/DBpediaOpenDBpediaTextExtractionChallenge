FROM java:8 

MAINTAINER  NLP2RDF <nlp2rdf@lists.informatik.uni-leipzig.de>


ENV DATA_SERVER    downloads.dbpedia.org/2016-04/ext/nif-abstracts/
ENV LANGUAGE	   it

RUN mkdir -p /opt/data/${LANGUAGE} && \
    cd /opt/data/${LANGUAGE} && \
    curl -O "http://$DATA_SERVER/${LANGUAGE}/nif-abstract-context_${LANGUAGE}.ttl.bz2" && \
    curl -O "http://$DATA_SERVER/${LANGUAGE}/nif-page-structure_${LANGUAGE}.ttl.bz2" && \
    curl -O "http://$DATA_SERVER/${LANGUAGE}/nif-text-links_${LANGUAGE}.ttl.bz2 " && \
    bzip2 -d nif-abstract-context_${LANGUAGE}.ttl.bz2 && \
    bzip2 -d nif-page-structure_${LANGUAGE}.ttl.bz2 && \
    bzip2 -d nif-text-links_${LANGUAGE}.ttl.bz2

