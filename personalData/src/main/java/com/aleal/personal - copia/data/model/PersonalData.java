package com.aleal.personal.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity //le dice a Hibernate que esta clase es una tabla
@Getter @Setter @ToString //Lombok
@Table(name="personal_data")
public class PersonalData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "person_id")
	private int personId;
	private String name;
	private String email;
	@Column(name = "mobile_number")
	private String mobileNumber;
	private String address;
	
}
