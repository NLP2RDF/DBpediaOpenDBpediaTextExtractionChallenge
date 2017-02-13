package org.dbpedia.extraction.nif;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NIF {

    NIFContext context;

    List<NIFParagraph> paragraphs;

    List<NIFPhrase> phases;

    List<NIFSection> sections;

    List<NIFTitle> titles;

    List<NIFWord> words;

}
