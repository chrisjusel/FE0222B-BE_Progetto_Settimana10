/**
 * Sugli attributi di questa classe sono presenti i vincoli
 * e i messaggi di errore che saranno visualizzati se tali vincoli
 * non vengono soddisfatti dall'utente nel front-end
 */
package com.secretary.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {

	@NotEmpty(message = "You must enter the id")
	private String studentId;
	@NotEmpty(message = "You must enter the name")
	private String name;
	@NotEmpty(message = "You must enter the surname")
	private String surname;
	@NotEmpty(message = "You must enter a born date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String bornDate;
	@NotEmpty(message = "You must enter valid email")
	@Email(message = "You must enter a valid email")
	private String email;
	@NotEmpty(message = "You must enter the address")
	private String address;
	@NotEmpty(message = "You must enter the city")
	private String city;
	@NotEmpty(message = "You must provide a valid degree course")
	private String degreeCourse;
}
