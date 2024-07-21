# RDF2JSON service
Creates JSON tree structure for provided IRI within the scope of provided RDF.

## Installation
Clone the repo, which represents a Spring Boot Web Service with gradle settings.

## Running the service
After cloning, you can run the service locally with `bootRun` gradle task.

## Generating the JSON from RDF
Returns the JSON tree with context.
GET `http://localhost:8080/full-object`

Params
`iri=http://www.example.org/person/Person`

Body (Raw text)
```<?xml version="1.0"?>
<rdf:RDF xmlns="http://www.example.org/person#"
xml:base="http://www.example.org/person"
xmlns:dc="http://purl.org/dc/elements/1.1/"
xmlns:owl="http://www.w3.org/2002/07/owl#"
xmlns:per="http://www.example.org/person/"
xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
xmlns:xml="http://www.w3.org/XML/1998/namespace"
xmlns:xsd="http://www.w3.org/2001/XMLSchema#"
xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#">
<owl:Ontology rdf:about="http://www.example.org/person"/>



    <!-- 
    ///////////////////////////////////////////////////////////////////////////////////////
    //
    // Annotation properties
    //
    ///////////////////////////////////////////////////////////////////////////////////////
     -->

    


    <!-- http://purl.org/dc/elements/1.1/creator -->

    <owl:AnnotationProperty rdf:about="http://purl.org/dc/elements/1.1/creator"/>
    


    <!-- http://purl.org/dc/elements/1.1/dateTime -->

    <owl:AnnotationProperty rdf:about="http://purl.org/dc/elements/1.1/dateTime"/>
    


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
        <rdfs:subPropertyOf rdf:resource="http://www.example.org/person/hasValue"/>
        <rdfs:domain rdf:resource="http://www.example.org/person/Person"/>
        <rdfs:range rdf:resource="http://www.w3.org/2001/XMLSchema#int"/>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-20T09:50:49Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>has age</rdfs:label>
    </owl:DatatypeProperty>
    


    <!-- http://www.example.org/person/hasIdentifierValue -->

    <owl:DatatypeProperty rdf:about="http://www.example.org/person/hasIdentifierValue">
        <rdfs:subPropertyOf rdf:resource="http://www.example.org/person/hasValue"/>
        <rdfs:domain rdf:resource="http://www.example.org/person/SSN"/>
        <rdfs:range rdf:resource="http://www.w3.org/2001/XMLSchema#string"/>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-20T09:50:18Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>has identifier value</rdfs:label>
    </owl:DatatypeProperty>
    


    <!-- http://www.example.org/person/hasNameValue -->

    <owl:DatatypeProperty rdf:about="http://www.example.org/person/hasNameValue">
        <rdfs:subPropertyOf rdf:resource="http://www.example.org/person/hasValue"/>
        <rdfs:domain rdf:resource="http://www.example.org/person/Name"/>
        <rdfs:range rdf:resource="http://www.w3.org/2001/XMLSchema#string"/>
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-20T09:49:34Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>has name value</rdfs:label>
    </owl:DatatypeProperty>
    


    <!-- http://www.example.org/person/hasValue -->

    <owl:DatatypeProperty rdf:about="http://www.example.org/person/hasValue">
        <dc:creator>patkom</dc:creator>
        <dc:dateTime rdf:datatype="http://www.w3.org/2001/XMLSchema#dateTime">2024-07-20T09:59:06Z</dc:dateTime>
        <rdfs:isDefinedBy rdf:resource="http://www.example.org/person"/>
        <rdfs:label>has value</rdfs:label>
    </owl:DatatypeProperty>
    


    <!-- 
    ///////////////////////////////////////////////////////////////////////////////////////
    //
    // Classes
    //
    ///////////////////////////////////////////////////////////////////////////////////////
     -->

    


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
</rdf:RDF>



<!-- Generated by the OWL API (version 4.5.29.2024-05-13T12:11:03Z) https://github.com/owlcs/owlapi -->
```