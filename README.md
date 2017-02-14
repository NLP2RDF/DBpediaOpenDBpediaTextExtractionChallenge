## DBpedia Open Text Extraction Challenge - a never ending knowledge acquisition spiral

[![Join the chat at https://gitter.im/NLP2RDF/DBpediaOpenDBpediaTextExtractionChallenge](https://badges.gitter.im/NLP2RDF/DBpediaOpenDBpediaTextExtractionChallenge.svg)](https://gitter.im/NLP2RDF/DBpediaOpenDBpediaTextExtractionChallenge?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

The DBpedia Open Text Extraction Challenge differs significantly from other challenges in the language technology and other areas in that it is not a one time call, but a continuous growing and expanding challenge with the focus to sustainably advance the state of the art and transcend boundaries in a systematic way. The DBpedia Association and the people behind this challenge are committed to provide the necessary infrastructure and drive the challenge for an indefinite time as well as potentially extend the challenge beyond Wikipedia. 

We provide the extracted and cleaned full text for all Wikipedia articles from 9 different languages in regular intervals for download and as Docker in the machine readable NIF-RDF format. Challenge participants are asked to wrap their NLP and extraction engines in Docker images and submit them to us. We will run participants’ tools in regular intervals in order to extract: 
Facts, relations, events, terminology, ontologies as RDF triples (Triple track)
Useful NLP annotations such as pos-tags, dependencies, co-reference (Annotation track)


Get more at [http://wiki.dbpedia.org/textext](http://wiki.dbpedia.org/textext)


## Tags 

  - nlp2rdf/dbpediaopendbpediatextextractionchallenge:tools

## How to run


1) Create a volume to store the data

``docker volume create --name nif-datasets``

2) Start our software image 

``docker run -v nif-datasets:/home/developer/data  -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix -it nlp2rdf/dbpediaopendbpediatextextractionchallenge:tools bash``

3) Run once our install script (Get some coffee. This will take some time)

``/opt/install.sh``

Now, you are ready to start to work. Our software image contains some useful tools to help you to take advantage of [NIF](https://site.nlp2rdf.org/)
  
  
* JDK 1.8
* [Maven 3.x](http://maven.apache.org/)
* [Rapper - Raptor RDF Syntax Library](http://librdf.org/raptor/)
* Python 2.7.x 
* [IntelliJ Community Edition 2016](https://www.jetbrains.com/idea/) - Path: /opt/idea-IC-163.12024.16/bin/idea.sh 
* Datasets in NIF - Path:  /home/developer/data/
* And finally, a small java stub to read NIF/turtle or N3 files to help you build your own code 


### Stubs:

NIF / Turtle files:

``
  String content = ResourceLoader.getContent("PATH");
  NIFParser parser = new NIFParser(content);
  NIF nif = parser.getNIF();

``


N3 files:

``
   Stream<String> content = ResourceLoader.getStream("PATH");
   
   NTripleParser parser = new NTripleParser(content);
   List<NIF> nif = parser.getNIF();

``



 Didn't found a software or a info that you need and not in list? Please tell us opening an issue


## How to submit

//TODO



## Supported Docker versions
This image is officially supported on Docker version 1.9.1.

Please see the [Docker installation documentation] (https://docs.docker.com/installation/) for details on how to upgrade your Docker daemon.

## Documentation

Documentation for this image is stored in [GitHub repo](https://github.com/NLP2RDF/DBpediaOpenDBpediaTextExtractionChallenge/wiki).

## Issues
If you have any problems with or questions about this image, please contact us through a [GitHub issue](https://github.com/NLP2RDF/DBpediaOpenDBpediaTextExtractionChallenges).


## Maintainers

<a href="http://infai.org"><img src="http://infai.org/de/Aktuelles/files?get=10_jahre_infai_gold.PNG" align="left" height="20%" width="20%" ></a>


