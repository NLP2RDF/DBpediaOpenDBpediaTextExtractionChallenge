package org.dbpedia.extraction.parser;

import org.dbpedia.extraction.io.ResourceLoader;
import org.dbpedia.extraction.nif.NIF;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class NTripleParserTest {


    @Test(expected = NullPointerException.class)
    public void ntriple_parser_with_constructor_must_return_nullpointer() {
        //Arrange
        Stream<String> content  = null;
        //Act
        NTripleParser parser = new NTripleParser(content);
    }

    @Test
    public void ntriple_must_parser_a_valid_nif() throws IOException {

        //Arrange
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("NIF.n3").getFile());
        Stream<String> content = ResourceLoader.getStream(file.getAbsolutePath());
        //Act
        NTripleParser parser = new NTripleParser(content);
        List<NIF> nif = parser.getNIF();

        //Assert
        Assert.assertNotNull(nif);
    }
}
