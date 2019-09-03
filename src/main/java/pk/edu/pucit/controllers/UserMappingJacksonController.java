/**
 * 
 */
package pk.edu.pucit.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.aspectj.lang.JoinPoint.StaticPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import pk.edu.pucit.entities.User;
import pk.edu.pucit.exceptions.UserNotFoundException;
import pk.edu.pucit.services.IUserService;

/**
 * @author Razi Ahmad
 *
 */
@Validated
@RestController
@RequestMapping(value = "v1/jacksonfilter/users")
public class UserMappingJacksonController {

	private IUserService service;

	@Autowired
	public UserMappingJacksonController(IUserService service) {
		this.service = service;
	}

	@GetMapping(value = "/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional = service.getUserById(id);
			User user = userOptional.get();

			Set<String> fields = new HashSet<>();
			fields.add("userid");
			fields.add("username");
			fields.add("ssn");
			fields.add("orders");
			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", filter);
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			return mapper;
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	@GetMapping(value = "/params/{id}")
	public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id, @RequestParam Set<String> fields) {
		try {
			Optional<User> userOptional = service.getUserById(id);
			User user = userOptional.get();

			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", filter);
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvider);
			return mapper;
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
}
