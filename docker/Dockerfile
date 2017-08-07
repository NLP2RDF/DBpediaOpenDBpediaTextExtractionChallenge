FROM java:8

MAINTAINER  NLP2RDF <nlp2rdf@lists.informatik.uni-leipzig.de>

ADD install.sh /opt

RUN export uid=1000 gid=1000 && \
    mkdir -p /home/developer/data && \
    mkdir -p /etc/sudoers.d && \
    echo "developer:x:${uid}:${gid}:Developer,,,:/home/developer:/bin/bash" >> /etc/passwd && \
    echo "developer:x:${uid}:" >> /etc/group && \
    echo "developer ALL=(ALL) NOPASSWD: ALL" > /etc/sudoers.d/developer && \
    chmod 0440 /etc/sudoers.d/developer && \
    chown ${uid}:${gid} -R /home/developer && \
    apt-get update && \
    apt-get -y install maven git raptor2-utils  && \
    cd /opt && \
    wget https://download.jetbrains.com/idea/ideaIC-2017.2.1.tar.gz && \
    tar -xvzf ideaIC-2017.2.1.tar.gz && \
    rm  ideaIC-2017.2.1.tar.gz && \
    chmod a+x install.sh

USER developer
ENV HOME /home/developer
