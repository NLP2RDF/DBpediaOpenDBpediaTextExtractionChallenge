package org.dbpedia.extraction.parser;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.dbpedia.extraction.nif.specification.NIFSpec.NAMESPACE;

public abstract class Parser {


    protected Model getModel(String format, String content) {

        Model model = ModelFactory.createDefaultModel();
        model.read(new ByteArrayInputStream(content.getBytes()), null, format);

        return model;
    }


    protected boolean hasParagraphNamespace(List<NIFParagraph> paragraphs, String namespace) {
        return paragraphs.stream().filter(p -> namespace.equals(p.getNamespace())).count() > 0;
    }

    protected void extractParagraph(List<NIFParagraph> paragraphs, Resource resource, Model model) {

        if (hasParagraphNamespace(paragraphs, resource.getNameSpace())) {
            return;
        }

        NIFParagraph paragraph = new NIFParagraph();

        Class<?> currentClass = paragraph.getClass();

        for (Field currentField : currentClass.getDeclaredFields()) {
            setValue(resource, model, paragraph, currentField);

        }

        paragraphs.add(paragraph);
    }

    protected boolean hasSectionNamespace(List<NIFSection> sections, String namespace) {
        return sections.stream().filter(p -> namespace.equals(p.getNamespace())).count() > 0;
    }

    protected void extractSection(List<NIFSection> sections, Resource resource, Model model) {

        if (hasSectionNamespace(sections, resource.getNameSpace())) {
            return;
        }

        NIFSection section = new NIFSection();

        Class<?> currentClass = section.getClass();

        for (Field currentField : currentClass.getDeclaredFields()) {
            setValue(resource, model, section, currentField);

        }

        sections.add(section);
    }

    protected boolean hasStructureNamespace(List<NIFStructure> structures, String namespace) {
        return structures.stream().filter(p -> namespace.equals(p.getNamespace())).count() > 0;
    }

    protected void extractStructure(List<NIFStructure> structures, Resource resource, Model model) {

        if (hasStructureNamespace(structures, resource.getNameSpace())) {
            return;
        }

        NIFStructure structure = new NIFStructure();

        Class<?> currentClass = structure.getClass();

        for (Field currentField : currentClass.getDeclaredFields()) {
            setValue(resource, model, structures, currentField);

        }

        structures.add(structure);
    }

    protected boolean hasTitleNamespace(List<NIFTitle> titles, String namespace) {
        return titles.stream().filter(p -> namespace.equals(p.getNamespace())).count() > 0;
    }

    protected void extractTitle(List<NIFTitle> titles, Resource resource, Model model) {

        if (hasTitleNamespace(titles, resource.getNameSpace())) {
            return;
        }

        NIFTitle title = new NIFTitle();

        Class<?> currentClass = title.getClass();

        for (Field currentField : currentClass.getDeclaredFields()) {
            setValue(resource, model, title, currentField);

        }

        titles.add(title);
    }

    protected boolean hasWordNamespace(List<NIFWord> words, String namespace) {
        return words.stream().filter(p -> namespace.equals(p.getNamespace())).count() > 0;
    }

    protected void extractNIFWord(List<NIFWord> words, Resource resource, Model model) {

        if (hasWordNamespace(words, resource.getNameSpace())) {
            return;
        }

        NIFWord word = new NIFWord();

        Class<?> currentClass = word.getClass();

        for (Field currentField : currentClass.getDeclaredFields()) {
            setValue(resource, model, word, currentField);

        }

        words.add(word);
    }

    protected boolean hasPhaseNamespace(List<NIFPhrase> phases, String namespace) {
        return phases.stream().filter(p -> namespace.equals(p.getNamespace())).count() > 0;
    }

    protected void extractNIFPhase(List<NIFPhrase> phases, Resource resource, Model model) {

        if (hasPhaseNamespace(phases, resource.getNameSpace())) {
            return;
        }

        NIFPhrase phase = new NIFPhrase();

        Class<?> currentClass = phase.getClass();

        for (Field currentField : currentClass.getDeclaredFields()) {
            setValue(resource, model, phase, currentField);

        }

        phases.add(phase);
    }

    protected void extractContext(NIFContext context, Resource resource, Model model) {

        Class<?> currentClass = context.getClass();

        for (Field currentField : currentClass.getDeclaredFields()) {
            setValue(resource, model, context, currentField);
        }
    }

    protected void setValue(Resource resource, Model model, Object obj, Field currentField) {

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
