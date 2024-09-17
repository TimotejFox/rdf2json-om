# DEMO
Demo service endpoint is available at: https://rdf2json.onrender.com/full-object
THIS IS NOT A FRONT-END application! Please use Postman or similar client. It is running on a free-tier server therefore the response speed is not optimal.

# RDF2JSON-OM service
Creates JSON tree structure for provided IRI within the scope of provided RDF.

## Installation
Clone the repo, which represents a Spring Boot Web Service with gradle settings.

## Running the service
After cloning, you can run the service locally with `bootRun` gradle task.

## Generating the JSON from RDF
Returns the JSON tree with context.
POST `http://localhost:8080/full-object`

Params
`iri=http://www.example.org/person/Person`

Body (Raw text)
```
<?xml version="1.0"?>
<rdf:RDF xmlns="http://www.example.org/person#"
     xml:base="http://www.example.org/person"
     xmlns:dc="http://purl.org/dc/elements/1.1/"
     xmlns:owl="http://www.w3.org/2002/07/owl#"
     xmlns:per="http://www.example.org/person/"
     xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
     xmlns:xml="http://www.w3.org/XML/1998/namespace"
     xmlns:xsd="http://www.w3.org/2001/XMLSchema#"
     xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
     xmlns:mapping="http://www.example.org/mapping/"
     xmlns:per-ind="http://www.example.org/person#">
    <owl:Ontology rdf:about="http://www.example.org/person">
        <rdfs:label>Person example ontology</rdfs:label>
    </owl:Ontology>
    


    <!-- 
    ///////////////////////////////////////////////////////////////////////////////////////
    //
    // Object Properties
    //
    ///////////////////////////////////////////////////////////////////////////////////////
     -->

    


    <!-- http://www.example.org/person/hasName -->

    <owl:ObjectProperty rdf:about="http://www.example.org/person/hasName">
        <rdfs:domain rdf:resource="http://www.example.org/person/Person"/>
        <rdfs:range rdf:resource="http://www.example.org/person/Name"/>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-19T10:05:50Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>has name</rdfs:label>
    </owl:ObjectProperty>
    


    <!-- http://www.example.org/person/hasSpouse -->

    <owl:ObjectProperty rdf:about="http://www.example.org/person/hasSpouse">
        <rdfs:domain rdf:resource="http://www.example.org/person/Person"/>
        <rdfs:range rdf:resource="http://www.example.org/person/Person"/>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-22T07:31:56Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>has spouse</rdfs:label>
    </owl:ObjectProperty>
    


    <!-- http://www.example.org/person/isIdentifiedBy -->

    <owl:ObjectProperty rdf:about="http://www.example.org/person/isIdentifiedBy">
        <rdfs:domain rdf:resource="http://www.example.org/person/Person"/>
        <rdfs:range rdf:resource="http://www.example.org/person/SSN"/>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-20T09:46:41Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>is identified by</rdfs:label>
    </owl:ObjectProperty>
    


    <!-- 
    ///////////////////////////////////////////////////////////////////////////////////////
    //
    // Data properties
    //
    ///////////////////////////////////////////////////////////////////////////////////////
     -->

    


    <!-- http://www.example.org/person/hasAge -->

    <owl:DatatypeProperty rdf:about="http://www.example.org/person/hasAge">
        <rdfs:subPropertyOf rdf:resource="http://www.w3.org/2002/07/owl#topDataProperty"/>
        <rdfs:domain rdf:resource="http://www.example.org/person/Person"/>
        <rdfs:range rdf:resource="http://www.w3.org/2001/XMLSchema#string"/>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-20T09:50:49Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>has age</rdfs:label>
    </owl:DatatypeProperty>
    


    <!-- http://www.example.org/person/hasFamilyName -->

    <owl:DatatypeProperty rdf:about="http://www.example.org/person/hasFamilyName">
        <rdfs:subPropertyOf rdf:resource="http://www.w3.org/2002/07/owl#topDataProperty"/>
        <rdfs:domain rdf:resource="http://www.example.org/person/Name"/>
        <rdfs:range rdf:resource="http://www.w3.org/2001/XMLSchema#string"/>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-22T08:33:47Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>has family name</rdfs:label>
    </owl:DatatypeProperty>
    


    <!-- http://www.example.org/person/hasGivenName -->

    <owl:DatatypeProperty rdf:about="http://www.example.org/person/hasGivenName">
        <rdfs:subPropertyOf rdf:resource="http://www.w3.org/2002/07/owl#topDataProperty"/>
        <rdfs:domain rdf:resource="http://www.example.org/person/Name"/>
        <rdfs:range rdf:resource="http://www.w3.org/2001/XMLSchema#string"/>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-20T09:49:34Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>has given name</rdfs:label>
    </owl:DatatypeProperty>
    


    <!-- http://www.example.org/person/hasIdentifierValue -->

    <owl:DatatypeProperty rdf:about="http://www.example.org/person/hasIdentifierValue">
        <rdfs:subPropertyOf rdf:resource="http://www.w3.org/2002/07/owl#topDataProperty"/>
        <rdfs:domain rdf:resource="http://www.example.org/person/SSN"/>
        <rdfs:range rdf:resource="http://www.w3.org/2001/XMLSchema#string"/>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-20T09:50:18Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>has identifier value</rdfs:label>
    </owl:DatatypeProperty>
    


    <!-- 
    ///////////////////////////////////////////////////////////////////////////////////////
    //
    // Classes
    //
    ///////////////////////////////////////////////////////////////////////////////////////
     -->

    


    <!-- http://www.example.org/mapping/MappingRule -->

    <owl:Class rdf:about="http://www.example.org/mapping/MappingRule">
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-21T14:36:35Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/mapping"/>
        <rdfs:label>Mapping rule</rdfs:label>
    </owl:Class>
    


    <!-- http://www.example.org/person/Name -->

    <owl:Class rdf:about="http://www.example.org/person/Name">
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-19T10:06:14Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>Name</rdfs:label>
    </owl:Class>
    


    <!-- http://www.example.org/person/Person -->

    <owl:Class rdf:about="http://www.example.org/person/Person">
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-19T10:02:52Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>Person</rdfs:label>
    </owl:Class>
    


    <!-- http://www.example.org/person/SSN -->

    <owl:Class rdf:about="http://www.example.org/person/SSN">
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-20T09:43:22Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>SSN</rdfs:label>
    </owl:Class>
    


    <!-- 
    ///////////////////////////////////////////////////////////////////////////////////////
    //
    // Individuals
    //
    ///////////////////////////////////////////////////////////////////////////////////////
     -->

    


    <!-- http://www.example.org/person#NameMappingIndividual -->

    <owl:NamedIndividual rdf:about="http://www.example.org/person#NameMappingIndividual">
        <rdf:type rdf:resource="http://www.example.org/mapping/MappingRule"/>
        <rdf:type rdf:resource="http://www.example.org/person/Name"/>
        <per:hasFamilyName>http://www.w3.org/2001/XMLSchema#string</per:hasFamilyName>
        <per:hasGivenName>http://www.w3.org/2001/XMLSchema#string</per:hasGivenName>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-21T14:34:09Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
    </owl:NamedIndividual>
    


    <!-- http://www.example.org/person#PersonMappingIndividual -->

    <owl:NamedIndividual rdf:about="http://www.example.org/person#PersonMappingIndividual">
        <rdf:type rdf:resource="http://www.example.org/mapping/MappingRule"/>
        <rdf:type rdf:resource="http://www.example.org/person/Person"/>
        <per:hasName rdf:resource="http://www.example.org/person#NameMappingIndividual"/>
        <per:hasSpouse rdf:resource="http://www.example.org/person#SpouseMappingIndividual"/>
        <per:isIdentifiedBy rdf:resource="http://www.example.org/person#SocialSecurityNumberMappingIndividual"/>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-21T14:45:01Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>Person mapping individual</rdfs:label>
    </owl:NamedIndividual>
    


    <!-- http://www.example.org/person#SocialSecurityNumberMappingIndividual -->

    <owl:NamedIndividual rdf:about="http://www.example.org/person#SocialSecurityNumberMappingIndividual">
        <rdf:type rdf:resource="http://www.example.org/mapping/MappingRule"/>
        <rdf:type rdf:resource="http://www.example.org/person/SSN"/>
        <per:hasIdentifierValue>http://www.w3.org/2001/XMLSchema#string</per:hasIdentifierValue>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-21T14:47:01Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>Social security number mapping individual</rdfs:label>
    </owl:NamedIndividual>
    


    <!-- http://www.example.org/person#SpouseMappingIndividual -->

    <owl:NamedIndividual rdf:about="http://www.example.org/person#SpouseMappingIndividual">
        <rdf:type rdf:resource="http://www.example.org/mapping/MappingRule"/>
        <rdf:type rdf:resource="http://www.example.org/person/Person"/>
        <per:hasName rdf:resource="http://www.example.org/person#SpouseNameMappingIndividual"/>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-22T07:31:20Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>Spouse mapping individual</rdfs:label>
    </owl:NamedIndividual>
    


    <!-- http://www.example.org/person#SpouseNameMappingIndividual -->

    <owl:NamedIndividual rdf:about="http://www.example.org/person#SpouseNameMappingIndividual">
        <rdf:type rdf:resource="http://www.example.org/mapping/MappingRule"/>
        <rdf:type rdf:resource="http://www.example.org/person/Name"/>
        <per:hasFamilyName>http://www.w3.org/2001/XMLSchema#string</per:hasFamilyName>
        <per:hasGivenName>http://www.w3.org/2001/XMLSchema#string</per:hasGivenName>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-22T07:39:31Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>Spouse name mapping individual</rdfs:label>
    </owl:NamedIndividual>
</rdf:RDF>



<!-- Generated by the OWL API (version 4.5.29.2024-05-13T12:11:03Z) https://github.com/owlcs/owlapi -->
```

Output
```
{
    "per:Person": {
        "per:hasName": {
            "per:Name": {
                "per:hasFamilyName": "http://www.w3.org/2001/XMLSchema#string",
                "per:hasGivenName": "http://www.w3.org/2001/XMLSchema#string"
            }
        },
        "per:hasSpouse": {
            "per:Person": {
                "per:hasName": {
                    "per:Name": {
                        "per:hasFamilyName": "http://www.w3.org/2001/XMLSchema#string",
                        "per:hasGivenName": "http://www.w3.org/2001/XMLSchema#string"
                    }
                }
            }
        },
        "per:isIdentifiedBy": {
            "per:SSN": {
                "per:hasIdentifierValue": "http://www.w3.org/2001/XMLSchema#string"
            }
        }
    },
    "@context": {
        "map": "http://www.example.org/mapping/",
        "per": "http://www.example.org/person/"
    }
}
```
