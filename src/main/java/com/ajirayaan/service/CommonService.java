package com.ajirayaan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ajirayaan.exception.ErrorMessage;
import com.ajirayaan.exception.LowBatteryException;
import com.ajirayaan.exception.RoverOutOfBoundsException;
import com.ajirayaan.exception.StormException;
import com.ajirayaan.model.Configuration;
import com.ajirayaan.model.Direction;
import com.ajirayaan.model.Environment;
import com.ajirayaan.repository.Ajiryaan;

@Service
public class CommonService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);
	@Autowired
	private Ajiryaan ajiryaan;

	public Ajiryaan getAjiryaan() {
		return ajiryaan;
	}

	public synchronized void configureEnv(Environment environment) {
		ajiryaan.configureEnv(environment);
	}

	public synchronized void updateEnv(Environment update) {
		ajiryaan.updateEnv(update);
	}

	public synchronized void configureRover(Configuration configuration) {
		ajiryaan.configureRover(configuration);
	}

	public synchronized ResponseEntity<Object> moveRover(Direction direction) {
		try {
			ajiryaan.moveRover(direction);
		} catch (RoverOutOfBoundsException e) {
			LOGGER.error("Issue occurred...", e);
			return new ResponseEntity<Object>(new ErrorMessage(e.getMessage()), HttpStatus.PRECONDITION_REQUIRED);
		} catch (LowBatteryException e) {
			LOGGER.error("Issue occurred...", e);
			return new ResponseEntity<Object>(HttpStatus.PRECONDITION_REQUIRED);
		} catch (StormException e) {
			LOGGER.error("Issue occurred...", e);
			return new ResponseEntity<Object>(new ErrorMessage(e.getMessage()), HttpStatus.PRECONDITION_REQUIRED);
		} catch (NullPointerException e) {
			LOGGER.error("Issue occurred...", e);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (RuntimeException e) {
			LOGGER.error("Issue occurred...", e);
			return new ResponseEntity<Object>(HttpStatus.PRECONDITION_REQUIRED);
		}

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
