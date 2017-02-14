package org.dbpedia.extraction.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public final class ResourceLoader {

    private ResourceLoader() {
    }

    public static String getContent(String path) throws IOException {
        Path resPath = Paths.get(path);
        return new String(Files.readAllBytes(resPath), StandardCharsets.UTF_8.name());
    }

    public static Stream<String> getStream(String path) throws IOException {
        Stream<String> stream = Files.lines(Paths.get(path));
        return stream;
    }
}
