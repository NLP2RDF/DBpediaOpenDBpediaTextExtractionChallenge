FROM java:8 

MAINTAINER  NLP2RDF <nlp2rdf@lists.informatik.uni-leipzig.de>

RUN apt-get update && \
    apt-get -y install maven git  && \
    mkdir -p /opt/extraction && \
    cd /opt/extraction && \
    git clone https://github.com/NLP2RDF/DBpediaOpenDBpediaTextExtractionChallenge.git && \
    cd DBpediaOpenDBpediaTextExtractionChallenge && \
    mvn clean install
    
    


