package com.kompupat.rdf2json.controller;

import com.kompupat.rdf2json.service.ObjectGeneratorService;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rdf2JsonController {

	private final ObjectGeneratorService objectGeneratorService;

	public Rdf2JsonController(@Nonnull final ObjectGeneratorService objectGeneratorService) {
		this.objectGeneratorService = objectGeneratorService;
	}

	@PostMapping(path = "/full-object", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getFullObject(
			@RequestParam("iri") final String iri,
			@RequestBody final String rdf) {
		String model = objectGeneratorService.getFullObjectFromIri(iri, rdf);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
}
