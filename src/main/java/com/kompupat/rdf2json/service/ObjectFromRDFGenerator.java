package com.kompupat.rdf2json.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
public class ObjectFromRDFGenerator {

    private final OntModel mainModel;
    private final String rootIRI;
    private final String sourceIRI;
    private final OntModel ontologyModel;
    private OntClass root;
    private String pathIRI;
    private final Gson gson;
    private int currentDepth;

    public ObjectFromRDFGenerator(OntModel mainModel, String rootIRI, String sourceIRI, String modelRDF) {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RDFS_INF, ModelFactory.createOntologyModel());
        model.read(new ByteArrayInputStream(modelRDF.getBytes()), null);
        this.mainModel = mainModel;
        this.ontologyModel = model;
        this.rootIRI = rootIRI;
        this.sourceIRI = sourceIRI;
        this.root = this.ontologyModel.getOntClass(sourceIRI);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Get empty map from RDF
     *
     * @return map with model
     */
    public Map<String, Object> getEmptyMap() {
        Map<String, Object> map = new TreeMap<>();
        // get root of the RDF, this is where the recursion starts
        if (getRoot() != null) {
            // recursion on generateSubObject
            Object subObject = generateSubObject(getRoot());
            map.put(getRoot().getURI(), subObject);
        }
        return map;
    }

    public Object generateSubObject(OntClass root) {
        Map<String, Object> ret = new TreeMap<>();
        root.listDeclaredProperties(false).toList().forEach(property -> {
            Object propertyValue = getRanges(root, property);
            if (propertyValue != null) {
                ret.put(property.getURI(), propertyValue);
            }
        });

        return ret;
    }

    private Object getRanges(OntClass root, OntProperty nextProperty) {
        if (nextProperty.isDatatypeProperty()) {
            //return nextProperty.getRange().toString();
            return root.getPropertyValue(nextProperty) != null
                    ? root.getPropertyValue(nextProperty).asLiteral().getLexicalForm()
                    : null;
        } else if (nextProperty.getRange() != null) {
            return populateRanges(nextProperty);
        } else return null;
    }

    public Object populateRanges(OntProperty nextProperty) {
        String propertyIRI = nextProperty.getURI();
        TreeMap<String, Object> rangeMap = new TreeMap<>();
        nextProperty.listRange().toList().forEach(nextRange -> {
            if (nextRange != null && !"http://www.w3.org/2000/01/rdf-schema#Resource".equals(nextRange.getURI())) {
                OntClass range = (OntClass) nextRange;
                Object rangeValue = continuePopulateRanges(range, propertyIRI);
                if (rangeValue != null) {
                    rangeMap.put(range.getURI(), rangeValue);
                }
            }
        });
        return rangeMap;
    }

    public Object continuePopulateRanges(OntClass nextRange, String propertyIRI) {
        String rangeIRI = nextRange.getURI();

        String newPathIRI = this.pathIRI + "|" + propertyIRI + "|" + rangeIRI;
        ObjectFromRDFGenerator objectGenerator = new ObjectFromRDFGenerator(this.mainModel, this.rootIRI, rangeIRI, RdfService.getModelFromIri(newPathIRI, this.mainModel));
        objectGenerator.setPathIRI(newPathIRI);
        Object mapObject = ObjectGeneratorService.getObjectFromJson(gson.toJson(objectGenerator.getEmptyMap().get(rangeIRI)));
        if (mapObject != null && !"{}".equals(mapObject.toString())) {
            return mapObject;
        }

        return null;
    }
}
