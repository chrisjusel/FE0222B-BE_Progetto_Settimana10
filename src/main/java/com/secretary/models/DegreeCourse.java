/**
 * Sugli attributi di questa classe sono presenti i vincoli
 * e i messaggi di errore che saranno visualizzati se tali vincoli
 * non vengono soddisfatti dall'utente nel front-end
 */
package com.secretary.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DegreeCourse {
	@NotBlank(message = "You must enter the course id")
	private String courseId;
	@NotBlank(message = "You must enter the course name")
	private String name;
	private String address;
	@Min(value = 10, message = "The minimum amount of exams is 10")
	private int examsNumber;

}
