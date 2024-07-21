package com.kompupat.rdf2json.service;

import jakarta.annotation.Nonnull;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.shared.PrefixMapping;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

public class RdfService {

    public static Map<String, String> getContextIris(OntModel model) {
        final Query query = createContextIRIsQuery();
        return processSelectQuery(model, query, RdfService::mapIRIsToContext);
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
