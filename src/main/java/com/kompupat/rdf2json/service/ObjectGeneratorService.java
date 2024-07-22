package com.kompupat.rdf2json.service;

import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@Service
public class ObjectGeneratorService {
    private final Gson gson;

    public ObjectGeneratorService() {
        this.gson = new Gson();
    }

    /**
     * Convert JSON to Object
     *
     * @param string to create object from
     * @return Object
     */
    public static Object getObjectFromJson(String string) {
        try {
            return JsonUtils.fromString(string);
        } catch (Exception e) {
            String message = String.format("Failed to convert JSON to Object %s", string);
            log.error(message, e);

            return null;
        }
    }

    public String getFullObjectFromIri(@Nonnull String iri, @Nonnull String rdf) {
        log.info("getFullObjectFromIri from {}", iri);

        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RDFS_INF, ModelFactory.createOntologyModel());
        model.read(new ByteArrayInputStream(rdf.getBytes()), null);

        ObjectFromRDFGenerator objectGenerator = new ObjectFromRDFGenerator(model, iri, iri, RdfService.getModelFromIri(iri, model));
        objectGenerator.setPathIRI(iri);
        Object mapObject = getObjectFromJson(gson.toJson(objectGenerator.getEmptyMap()));

        String contextString = getContextIRIs(model);
        TreeMap<String, String> context = gson.fromJson(contextString, new TypeToken<TreeMap<String, Object>>() {
        }.getType());

        JsonLdOptions options = new JsonLdOptions();
        // Compact JSON with @context
        Object compactObject = getCompactJson(mapObject, context, options);
        String structureModel = gson.toJson(compactObject);

        @SuppressWarnings("unchecked")
        Map<String, Object> compactedMap = (LinkedHashMap<String, Object>) getObjectFromJson(structureModel);

        log.info("Object for {} was created successfully.", iri);
        return gson.toJson(compactedMap);
    }

    /**
     * Compact JSON by context by JsonLdProcessor.compact
     *
     * @return Object
     */
    private static Object getCompactJson(Object mapObject, Object context, JsonLdOptions options) {
        try {
            return JsonLdProcessor.compact(mapObject, context, options);
        } catch (Exception e) {
            String message = "Failed to compact JSON by context";
            log.error(message, e);
        }

        return null;
    }

    public String getContextIRIs(final OntModel model) {
        final Map<String, String> context = RdfService.getContextIris(model);
        return gson.toJson(context);
    }

}
