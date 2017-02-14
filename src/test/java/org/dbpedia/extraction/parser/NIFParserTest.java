package org.dbpedia.extraction.parser;

import org.dbpedia.extraction.io.ResourceLoader;
import org.dbpedia.extraction.nif.NIF;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class NIFParserTest {

    @Test(expected = NullPointerException.class)
    public void nifparser_with_constructor_must_return_nullpointer() {
        //Arrange
        String nif = null;
        //Act
        NIFParser parser = new NIFParser(nif);
    }

    @Test
    public void nifparser_must_parser_a_valid_nif() throws IOException {
        //Arrange
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("BO.ttl").getFile());
        String content = ResourceLoader.getContent(file.getAbsolutePath());
        //Act
        NIFParser parser = new NIFParser(content);
        NIF nif = parser.getNIF();

        //Assert
        Assert.assertNotNull(nif);
    }

}
