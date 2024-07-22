package com.kompupat.rdf2json.service;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.shared.PrefixMapping;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

@Slf4j
public class RdfService {

    public static Map<String, String> getContextIris(@Nonnull OntModel model) {
        final Query query = createContextIRIsQuery();
        return processSelectQuery(model, query, RdfService::mapIRIsToContext);
    }

    public static String getModelFromIri(@Nonnull final String iri, @Nonnull final OntModel model) {
        final Query query = createModelQuery(iri);
        final QueryExecution qexec = QueryExecutionFactory.create(query, model);
        // The models returned by execConstruct and execDescribe are valid after the
        // QueryExecution is closed.
        final Model resultModel = qexec.execConstruct();
        qexec.close();

        StringWriter sw = new StringWriter();
        resultModel.write(sw, "RDF/XML");

        return sw.toString();
    }

    /**
     * Common list of prefixes
     *
     * @return pm
     */
    private static PrefixMapping getDefaultPrefixMapping() {
        final PrefixMapping pm = PrefixMapping.Factory.create();
        pm.setNsPrefix("owl", "http://www.w3.org/2002/07/owl#");
        pm.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
        pm.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        pm.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        pm.setNsPrefix("skos", "http://www.w3.org/2004/02/skos/core#");
        pm.setNsPrefix("dcelements", "http://purl.org/dc/elements/1.1/");
        pm.setNsPrefix("mapping", "http://www.example.org/mapping/");
        return pm;
    }

    /** SPARQL query returns prefixes */
    private static Query createContextIRIsQuery() {
        final ParameterizedSparqlString pss = new ParameterizedSparqlString(getDefaultPrefixMapping());
        pss.append("SELECT DISTINCT ?defined ");
        pss.append("WHERE { ");
        pss.append(" ?iri rdf:type ?concept ");
        pss.append(" FILTER ( ?concept IN (owl:Class, owl:ObjectProperty, owl:DatatypeProperty) ) . ");
        pss.append(" ?iri rdfs:isDefinedBy ?defined . ");
        pss.append(" FILTER ( isIRI(?defined) ) . ");
        pss.append("} ");
        pss.append("ORDER BY ?iri ");

        return pss.asQuery();
    }

    private static Query createModelQuery(@Nonnull final String path) {
        log.debug("Create model query with for path: {}", path);

        List<String> iris = Arrays.asList(path.split("\\|"));
        final ParameterizedSparqlString pss = new ParameterizedSparqlString(getDefaultPrefixMapping());

        int iCounter = iris.size() / 2;

        pss.append("CONSTRUCT { ");
        pss.append("    ?x" + iCounter + " a owl:Class . ");
        pss.append("    ?dataProperty a owl:DatatypeProperty . ");
        pss.append("    ?dataProperty rdfs:domain ?x" + iCounter + " . ");
        pss.append("    ?x" + iCounter + " ?dataProperty ?mapping . ");
        pss.append("    ?objectProperty a owl:ObjectProperty . ");
        pss.append("    ?objectProperty rdfs:domain ?x" + iCounter + " . ");
        pss.append("    ?objectProperty rdfs:range ?rangeType . ");
        pss.append("} ");
        pss.append("WHERE { ");

        for (int i = 0; i < iris.size() / 2; i++) {
            pss.append("?di" + i + " rdf:type owl:NamedIndividual ; ");
            pss.append("             rdf:type mapping:MappingRule ; ");
            pss.append("             rdf:type ?x" + i + " . ");
            if (i == 0) {
                pss.append("FILTER NOT EXISTS {?aProperty a owl:ObjectProperty . ?anObject ?p ?di" + i + " } .");
            }
            pss.append("?di" + i + " ?a" + i + " ?di" + (i + 1) + " . ");
        }

        pss.append("?di" + iCounter + " rdf:type owl:NamedIndividual ; ");
        pss.append("             rdf:type mapping:MappingRule ; ");
        pss.append("             rdf:type ?x" + iCounter + " . ");
        if (iris.size() == 1) {
            pss.append("FILTER NOT EXISTS {?aProperty a owl:ObjectProperty . ?anObject ?p ?di" + iCounter + " } .");
        }
        pss.append("OPTIONAL { ");
        pss.append("    ?dataProperty a owl:DatatypeProperty . ");
        pss.append("    ?di" + iCounter + " ?dataProperty ?mapping . ");
        pss.append("} ");
        pss.append("OPTIONAL { ");
        pss.append("    ?objectProperty a owl:ObjectProperty . ");
        pss.append("    ?di" + iCounter + " ?objectProperty ?range . ");
        pss.append("    ?range a? ?rangeType ");
        pss.append("    FILTER (?rangeType NOT IN (?range, owl:NamedIndividual, mapping:MappingRule) && ?rangeType != owl:NamedIndividual ) ");
        pss.append("} ");
        pss.append("} ");
        pss.append("VALUES ( ");

        for (int i = 0; i < iris.size() / 2; i++) {
            pss.append("?x" + i + " ");
            pss.append("?a" + i + " ");
        }

        pss.append("?x" + iCounter + " ");

        pss.append(") { ( ");

        for (String s : iris) {
            pss.append("<" + s + "> ");
        }
        pss.append(" ) }");

        log.debug("Query: {}", pss);

        return pss.asQuery();
    }

    private static <T> T processSelectQuery(final OntModel model, @Nonnull final Query query,
                                            @Nonnull final Function<ResultSet, T> mapper) {
        // QueryExecution objects are java.lang.AutoCloseable and result are
        // handled in a loop and finally the query execution is closed.
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            return mapper.apply(qexec.execSelect());
        }
    }

    private static Map<String, String> mapIRIsToContext(@Nonnull final ResultSet rs) {
        Map<String, String> context = new TreeMap<>();
        while (rs.hasNext()) {
            String contextIRI = rs.next().get("defined").toString();
            context.put(shortenIRI(contextIRI), contextIRI + "/");
        }

        return context;
    }

    private static String shortenIRI(@Nonnull final String iri) {
        // First remove the http://***/ part of the iri
        String shorter = iri.replaceFirst("(http[s]?://)(.*?/)", "");

        // just get 3 chars of the first part of the iri
        String[] items = shorter.split("/");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            if (i == 0) {
                builder.append(items[i].length() > 3 ? items[i].substring(0, 3) : items[i]);
            } else {
                builder.append("/");
                builder.append(items[i]);
            }
        }
        return builder.toString();
    }
}
