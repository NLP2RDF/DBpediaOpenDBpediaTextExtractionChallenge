package org.dbpedia.extraction;

import org.dbpedia.extraction.io.ResourceLoader;
import org.dbpedia.extraction.parser.NIFParser;

public class DBpediaOpenDBpediaTextExtractionChallenge {


    public static void main(String[] args) throws Exception {

        NIFParser parser = new NIFParser(ResourceLoader.getContent(""));

        parser.getNIF();













    }

}
