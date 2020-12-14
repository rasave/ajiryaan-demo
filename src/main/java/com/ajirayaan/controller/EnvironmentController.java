package com.ajirayaan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ajirayaan.model.Environment;
import com.ajirayaan.service.CommonService;

@RestController
@RequestMapping("/api/environment")
public class EnvironmentController {
	@Autowired
	CommonService commonService;

	@RequestMapping(value = "/configure", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void configureEnvironment(@RequestBody Environment environment) {
		commonService.configureEnv(environment);
	}

	@RequestMapping(value = "/", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateEnv(@RequestBody Environment environment) {
		commonService.updateEnv(environment);
	}
}
