package org.dbpedia.extraction.parser;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
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
import org.dbpedia.extraction.nif.annotation.Type;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.dbpedia.extraction.nif.specification.NIFSpec.NAMESPACE;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_CONTEXT;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_PARAGRAPH;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_PHRASE;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_SECTION;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_STRUCTURE;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_TITLE;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_WORD;

public class NIFParser {

    private String nif;

    private NIFContext context;

    private List<NIFParagraph> paragraphs;

    private List<NIFPhrase> phases;

    private List<NIFSection> sections;

    private List<NIFStructure> structures;

    private List<NIFTitle> titles;

    private List<NIFWord> words;

    public NIFParser(String nif) {

        this.nif = nif;
        this.context = new NIFContext();
        this.paragraphs = new ArrayList<>();
        this.phases = new ArrayList<>();
        this.sections = new ArrayList<>();
        this.structures = new ArrayList<>();
        this.titles = new ArrayList<>();
        this.words = new ArrayList<>();

    }

    public Model getModel() {

        Model model = ModelFactory.createDefaultModel();
        model.read(new ByteArrayInputStream(nif.getBytes()), null, "TTL");

        return model;
    }

    private void fill() {

        Model model = getModel();

        StmtIterator stmtIterator = model.listStatements();

        while (stmtIterator.hasNext()) {
            Statement stm = stmtIterator.nextStatement();
            Resource resource = stm.getSubject().asResource();
            String type = resource.getPropertyResourceValue(RDF.type).getURI();
            if (NIF_PROPERTY_PHRASE.equals(type)) {
                extractNIFPhase(resource, model);
            } else if (NIF_PROPERTY_WORD.equals(type)) {
                extractNIFWord(resource, model);
            } else if (NIF_PROPERTY_WORD.equals(type)) {
                extractNIFWord(resource, model);
            } else if ( NIF_PROPERTY_TITLE.equals(type)) {
                extractTitle(resource, model);
            } else if (NIF_PROPERTY_STRUCTURE.equals(type)) {
                extractStructure(resource, model);
            } else if (NIF_PROPERTY_SECTION.equals(type)) {
                extractSection(resource, model);
            } else if (NIF_PROPERTY_PARAGRAPH.equals(type)) {
                extractParagraph(resource, model);
            } else if (NIF_PROPERTY_CONTEXT.equals(type)) {
                extractContext(resource, model);
            }
        }

    }

    public NIF getNIF() {

        fill();

        NIF result = new NIF();

        result.setContext(this.context);
        result.setPhases(this.phases);
        result.setParagraphs(this.paragraphs);
        result.setSections(this.sections);
        result.setTitles(this.titles);
        result.setWords(this.words);


        return result;
    }


    private void extractContext(Resource resource, Model model) {

        Class<?> currentClass = context.getClass();

        for (Field currentField : currentClass.getDeclaredFields()) {
            setValue(resource, model, context, currentField);
        }
    }

    private boolean hasParagraphNamespace(String namespace) {
        return paragraphs.stream().filter(p -> namespace.equals(p.getNamespace())).count() > 0;
    }

    private void extractParagraph(Resource resource, Model model) {

        if (hasParagraphNamespace(resource.getNameSpace())) {
            return;
        }

        NIFParagraph paragraph = new NIFParagraph();

        Class<?> currentClass = paragraph.getClass();

        for (Field currentField : currentClass.getDeclaredFields()) {
            setValue(resource, model, paragraph, currentField);

        }

        paragraphs.add(paragraph);
    }

    private boolean hasSectionNamespace(String namespace) {
        return sections.stream().filter(p -> namespace.equals(p.getNamespace())).count() > 0;
    }

    private void extractSection(Resource resource, Model model) {

        if (hasSectionNamespace(resource.getNameSpace())) {
            return;
        }

        NIFSection section = new NIFSection();

        Class<?> currentClass = section.getClass();

        for (Field currentField : currentClass.getDeclaredFields()) {
            setValue(resource, model, section, currentField);

        }

        sections.add(section);
    }

    private boolean hasStructureNamespace(String namespace) {
        return structures.stream().filter(p -> namespace.equals(p.getNamespace())).count() > 0;
    }

    private void extractStructure(Resource resource, Model model) {

        if (hasStructureNamespace(resource.getNameSpace())) {
            return;
        }

        NIFStructure structure = new NIFStructure();

        Class<?> currentClass = structure.getClass();

        for (Field currentField : currentClass.getDeclaredFields()) {
            setValue(resource, model, structures, currentField);

        }

        structures.add(structure);
    }

    private boolean hasTitleNamespace(String namespace) {
        return titles.stream().filter(p -> namespace.equals(p.getNamespace())).count() > 0;
    }

    private void extractTitle(Resource resource, Model model) {

        if (hasTitleNamespace(resource.getNameSpace())) {
            return;
        }

        NIFTitle title = new NIFTitle();

        Class<?> currentClass = title.getClass();

        for (Field currentField : currentClass.getDeclaredFields()) {
            setValue(resource, model, title, currentField);

        }

        titles.add(title);
    }

    private boolean hasWordNamespace(String namespace) {
        return words.stream().filter(p -> namespace.equals(p.getNamespace())).count() > 0;
    }

    private void extractNIFWord(Resource resource, Model model) {

        if (hasWordNamespace(resource.getNameSpace())) {
            return;
        }

        NIFWord word = new NIFWord();

        Class<?> currentClass = word.getClass();

        for (Field currentField : currentClass.getDeclaredFields()) {
            setValue(resource, model, word, currentField);

        }

        words.add(word);
    }

    private boolean hasPhaseNamespace(String namespace) {
        return phases.stream().filter(p -> namespace.equals(p.getNamespace())).count() > 0;
    }

    private void extractNIFPhase(Resource resource, Model model) {

        if (hasPhaseNamespace(resource.getNameSpace())) {
            return;
        }

        NIFPhrase phase = new NIFPhrase();

        Class<?> currentClass = phase.getClass();

        for (Field currentField : currentClass.getDeclaredFields()) {
            setValue(resource, model, phase, currentField);

        }

        phases.add(phase);
    }

    private void setValue(Resource resource, Model model, Object obj, Field currentField) {

        if (currentField.isAnnotationPresent(Type.class)) {

            Type currentFieldType = currentField.getAnnotation(Type.class);

            if (NAMESPACE.equals(currentFieldType.name())) {

                currentField.setAccessible(Boolean.TRUE);

                try {
                    currentField.set(obj, resource.getNameSpace());
                } catch (Exception e) {
                }

            } else if (currentField.getType().equals(String.class)) {

                if (resource.getProperty(model.getProperty(currentFieldType.name())) != null) {
                    String value = resource.getProperty(model.getProperty(currentFieldType.name()))
                            .getObject().toString();

                    currentField.setAccessible(Boolean.TRUE);

                    try {
                        currentField.set(obj, value);
                    } catch (Exception e) {
                    }

                }

            } else if (currentField.getType().equals(Integer.class)) {

                Integer value = resource.getProperty(model.getProperty(currentFieldType.name()))
                        .getObject().asLiteral().asLiteral().getInt();

                currentField.setAccessible(Boolean.TRUE);

                try {
                    currentField.set(obj, value);
                } catch (Exception e) {
                }
            } else if (currentField.getType().equals(Set.class)) {

                currentField.setAccessible(Boolean.TRUE);

                try {
                    Set<String> list = (Set<String>) currentField.get(obj);

                    String value = resource.getProperty(model.getProperty(currentFieldType.name()))
                            .getObject().toString();

                    if (list == null) {
                        list = new HashSet<>();
                        list.add(value);
                    } else {
                        list.add(value);
                    }

                    currentField.set(obj, list);

                } catch (Exception e) {

                }

            }
        }
    }

}
