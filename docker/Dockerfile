FROM java:8 

MAINTAINER  NLP2RDF <nlp2rdf@lists.informatik.uni-leipzig.de>

ADD install.sh /opt

RUN apt-get update && \
    apt-get -y install maven git raptor2-utils  && \
    cd /opt && \
    wget https://download.jetbrains.com/idea/ideaIC-2016.3.4.tar.gz && \
    tar -xvzf ideaIC-2016.3.4.tar.gz && \
    chmod a+x install.sh && \
    mkdir -p /opt/data && \
    cd /opt/data && \
    git clone https://github.com/NLP2RDF/DBpediaOpenDBpediaTextExtractionChallenge.git && \
    cd DBpediaOpenDBpediaTextExtractionChallenge && \
    mvn clean install
    
    


