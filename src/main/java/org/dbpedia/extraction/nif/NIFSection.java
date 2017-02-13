package org.dbpedia.extraction.nif;

import lombok.Getter;
import lombok.Setter;
import org.dbpedia.extraction.nif.annotation.Type;

import java.util.List;
import java.util.Set;

import static org.dbpedia.extraction.nif.specification.NIFSpec.NAMESPACE;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_BEGININDEX;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_ENDINDEX;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_HAS_PARAGRAPH;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_LAST_PARAGRAPH;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_NEXT_SECTION;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_PREVIOUS_SECTION;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_REFERENCE_CONTEXT;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_SECTION;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_SUPER_STRING;

@Getter
@Setter
@Type(name = NIF_PROPERTY_SECTION)
public class NIFSection {

    @Type(name = NAMESPACE)
    private String namespace;

    @Type(name = NIF_PROPERTY_BEGININDEX)
    private Integer beginIndex;

    @Type(name = NIF_PROPERTY_ENDINDEX)
    private Integer endIndex;

    @Type(name = NIF_PROPERTY_HAS_PARAGRAPH)
    private Set<String> hasParagraph;

    @Type(name = NIF_PROPERTY_LAST_PARAGRAPH)
    private String lastParagraph;

    @Type(name = NIF_PROPERTY_NEXT_SECTION)
    private String nextSection;

    @Type(name = NIF_PROPERTY_PREVIOUS_SECTION)
    private String previousSection;

    @Type(name = NIF_PROPERTY_REFERENCE_CONTEXT)
    private String referenceContext;

    @Type(name = NIF_PROPERTY_SUPER_STRING)
    private String superString;

}
