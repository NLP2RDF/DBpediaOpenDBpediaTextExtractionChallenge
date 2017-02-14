package org.dbpedia.extraction.parser;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.RDF;
import org.dbpedia.extraction.nif.NIF;
import org.dbpedia.extraction.nif.NIFContext;
import org.dbpedia.extraction.nif.NIFParagraph;
import org.dbpedia.extraction.nif.NIFPhrase;
import org.dbpedia.extraction.nif.NIFSection;
import org.dbpedia.extraction.nif.NIFStructure;
import org.dbpedia.extraction.nif.NIFTitle;
import org.dbpedia.extraction.nif.NIFWord;
import org.semanticweb.yars.nx.Node;
import org.semanticweb.yars.nx.parser.NxParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_CONTEXT;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_PARAGRAPH;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_PHRASE;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_SECTION;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_STRUCTURE;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_TITLE;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_WORD;

public class NTripleParser extends Parser {

    private NIFContext context;

    private List<NIFParagraph> paragraphs;

    private List<NIFPhrase> phases;

    private List<NIFSection> sections;

    private List<NIFStructure> structures;

    private List<NIFTitle> titles;

    private List<NIFWord> words;

    private NxParser nxParser;

    private Stream<String> stream;

    private String lastTriple;

    public NTripleParser(Stream<String> stream) {

        Objects.requireNonNull(stream);
        this.nxParser = new NxParser();
        this.stream = stream;
        this.lastTriple = "";
    }

    public Model getModel(String content) {
        return super.getModel("N3", content);
    }


    private void fill(final String content) {

        Model model = getModel(content);

        StmtIterator stmtIterator = model.listStatements();

        while (stmtIterator.hasNext()) {
            Statement stm = stmtIterator.nextStatement();
            Resource resource = stm.getSubject().asResource();
            String type = resource.getPropertyResourceValue(RDF.type).getURI();
            if (NIF_PROPERTY_PHRASE.equals(type)) {
                extractNIFPhase(phases, resource, model);
            } else if (NIF_PROPERTY_WORD.equals(type)) {
                extractNIFWord(words, resource, model);
            } else if ( NIF_PROPERTY_TITLE.equals(type)) {
                extractTitle(titles, resource, model);
            } else if (NIF_PROPERTY_STRUCTURE.equals(type)) {
                extractStructure(structures, resource, model);
            } else if (NIF_PROPERTY_SECTION.equals(type)) {
                extractSection(sections, resource, model);
            } else if (NIF_PROPERTY_PARAGRAPH.equals(type)) {
                extractParagraph(paragraphs, resource, model);
            } else if (NIF_PROPERTY_CONTEXT.equals(type)) {
                extractContext(context, resource, model);
            }
        }

    }


    private Boolean subjectChanged(String line) {
        InputStream stream = new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8));
        nxParser.parse(stream);
        String currentSubject = "";
        for (Node[] nx : nxParser) {
            currentSubject =  nx[0].toString();
            break;
        }

        if (lastTriple.equals(currentSubject)) {
            return Boolean.FALSE;
        }

        lastTriple = currentSubject;

        return Boolean.TRUE;
    }


    public List<NIF> getNIF() {

        List<NIF> result = new ArrayList<>();

        final StringBuilder builder = new StringBuilder();

        stream.forEach(line-> {

            if (subjectChanged(line)) {

                this.context = new NIFContext();
                this.paragraphs = new ArrayList<>();
                this.phases = new ArrayList<>();
                this.sections = new ArrayList<>();
                this.structures = new ArrayList<>();
                this.titles = new ArrayList<>();
                this.words = new ArrayList<>();

                fill(builder.toString());

                NIF nif = new NIF();

                nif.setContext(this.context);
                nif.setPhases(this.phases);
                nif.setParagraphs(this.paragraphs);
                nif.setSections(this.sections);
                nif.setTitles(this.titles);
                nif.setWords(this.words);

                result.add(nif);
                builder.setLength(0);
            }

            builder.append(line);
            builder.append("\n");

        });


        return result;
    }
}
