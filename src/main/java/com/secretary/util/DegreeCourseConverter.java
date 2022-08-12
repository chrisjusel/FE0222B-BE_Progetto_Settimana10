package com.secretary.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.secretary.models.DegreeCourse;
import com.secretary.persistance.DummyDb;

@Component
public class DegreeCourseConverter implements Converter<String, DegreeCourse>{

	DummyDb dummyDb = BeanBuilder.getIstance();
	
	@Override
	public DegreeCourse convert(String value) {
		return dummyDb.getDegreeCourseById(value);
	}


		
	
	
}
