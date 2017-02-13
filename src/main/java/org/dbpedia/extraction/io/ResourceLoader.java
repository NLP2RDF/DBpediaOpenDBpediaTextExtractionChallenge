package org.dbpedia.extraction.io;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public final class ResourceLoader {

    private ResourceLoader() {
    }

    public static String getContent(String path) throws Exception {
        Path resPath = Paths.get(path);
        return new String(Files.readAllBytes(resPath), StandardCharsets.UTF_8.name());
    }
}
