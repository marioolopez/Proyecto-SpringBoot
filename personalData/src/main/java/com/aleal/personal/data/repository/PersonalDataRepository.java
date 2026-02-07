package com.aleal.personal.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aleal.personal.data.model.PersonalData;

@Repository
public interface PersonalDataRepository extends CrudRepository<PersonalData, Integer>{
	
	PersonalData findByPersonId(int personId);

}
