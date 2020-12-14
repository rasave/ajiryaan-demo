package com.ajirayaan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ajirayaan.model.Configuration;
import com.ajirayaan.model.Direction;
import com.ajirayaan.repository.Ajiryaan;
import com.ajirayaan.service.CommonService;

@RestController
@RequestMapping("/api/rover")
public class RoverController {
	@Autowired
	CommonService commonService;

	@RequestMapping(value = "/status", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Ajiryaan getStatus() {
		return commonService.getAjiryaan();
	}

	@RequestMapping(value = "/configure", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Configuration getStatus(@RequestBody Configuration configuration) {
		commonService.configureRover(configuration);
		return configuration;
	}

	@RequestMapping(value = "/move", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> move(@RequestBody Direction direction) {
		ResponseEntity<Object> result = commonService.moveRover(direction);
		return result;
	}

}
