# docker build -t lhd:latest https://github.com/KIZI/LinkedHypernymsDataset.git#:docker
# docker run --name lhd -d --env-file <path-to-env-vars-file> lhd <language(en|de|nl)> <dbpedia-version>
# docker cp lhd:/root/LinkedHypernymsDataset/data/output ./

FROM debian:jessie

MAINTAINER kizi "prozeman@gmail.com"

WORKDIR /root

RUN echo 'deb http://http.debian.net/debian jessie-backports main' >> /etc/apt/sources.list && \
    apt-get update && \
    apt-get install -y -t jessie-backports openjdk-8-jdk && \
    apt-get install -y git curl maven wget unzip memcached && \
    update-alternatives --set java /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java
    
RUN git clone https://github.com/KIZI/LinkedHypernymsDataset.git && \
    mkdir LinkedHypernymsDataset/data/output && \
    mkdir LinkedHypernymsDataset/utils
    
WORKDIR LinkedHypernymsDataset

RUN cd utils && \
    wget http://netassist.dl.sourceforge.net/project/gate/gate/8.0/gate-8.0-build4825-BIN.zip && \
    unzip gate-8.0-build4825-BIN.zip && \
    rm gate-8.0-build4825-BIN.zip && \
    mkdir treetagger && \
    wget http://www.cis.uni-muenchen.de/~schmid/tools/TreeTagger/data/tree-tagger-linux-3.2.1.tar.gz -P treetagger/ && \
    wget http://www.cis.uni-muenchen.de/~schmid/tools/TreeTagger/data/tagger-scripts.tar.gz -P treetagger/ && \
    wget http://www.cis.uni-muenchen.de/~schmid/tools/TreeTagger/data/install-tagger.sh -P treetagger/ && \
    wget http://www.cis.uni-muenchen.de/~schmid/tools/TreeTagger/data/dutch-par-linux-3.2-utf8.bin.gz -P treetagger/ && \
    wget http://www.cis.uni-muenchen.de/~schmid/tools/TreeTagger/data/german-par-linux-3.2-utf8.bin.gz -P treetagger/ && \
    wget http://www.cis.uni-muenchen.de/~schmid/tools/TreeTagger/data/german-chunker-par-linux-3.2-utf8.bin.gz -P treetagger/ && \
    chmod 755 treetagger/install-tagger.sh && \
    cd treetagger && \
    ./install-tagger.sh
    
ADD tree-tagger-* utils/gate-8.0-build4825-BIN/plugins/Tagger_Framework/resources/TreeTagger/
ADD application.* ./
ADD start.sh ./

RUN chmod 755 utils/gate-8.0-build4825-BIN/plugins/Tagger_Framework/resources/TreeTagger/* && \
    chmod 755 start.sh && \
    mvn install
    
VOLUME ["/root/LinkedHypernymsDataset/data/output"]

ENTRYPOINT ["./start.sh"]