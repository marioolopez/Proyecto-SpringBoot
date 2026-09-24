package com.aleal.personal.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.aleal.personal.data.model.PersonalData;
import com.aleal.personal.data.repository.PersonalDataRepository;

@RestController
public class PersonalDataController {
	
	@Autowired
	private PersonalDataRepository personalDataRepository;

	@GetMapping("/myPersonalData/{id}")
	public PersonalData getPersonalDataDetails(@PathVariable int id) {

		PersonalData data = personalDataRepository.findByPersonId(id);
		if (data != null) {
			return data;
		} else {
			return null;
		}

	}

}
