package org.dbpedia.extraction.nif;

import lombok.Getter;
import lombok.Setter;
import org.dbpedia.extraction.nif.annotation.Type;

import java.util.Set;

import static org.dbpedia.extraction.nif.specification.NIFSpec.NAMESPACE;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_BEGININDEX;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_CONTEXT;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_ENDINDEX;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_FIRST_SECTION;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_HAS_SECTION;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_ISSTRING;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_LAST_SECTION;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_PRED_LANG;
import static org.dbpedia.extraction.nif.specification.NIFSpec.NIF_PROPERTY_SOURCE_URL;

@Getter
@Setter
@Type(name = NIF_PROPERTY_CONTEXT)
public class NIFContext {

    @Type(name = NAMESPACE)
    private String namespace;

    @Type(name = NIF_PROPERTY_BEGININDEX)
    private Integer beginIndex;

    @Type(name = NIF_PROPERTY_ENDINDEX)
    private Integer endIndex;

    @Type(name = NIF_PROPERTY_FIRST_SECTION)
    private Set<String> firstSection;

    @Type(name = NIF_PROPERTY_HAS_SECTION)
    private Set<String> hasSection;

    @Type(name = NIF_PROPERTY_ISSTRING)
    private String isString;

    @Type(name = NIF_PROPERTY_LAST_SECTION)
    private Set<String> lastSection;

    @Type(name = NIF_PROPERTY_PRED_LANG)
    private String predLang;

    @Type(name = NIF_PROPERTY_SOURCE_URL)
    private String sourceURL;


}
