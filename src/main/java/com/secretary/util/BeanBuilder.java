/**
 * Questa classe è frutto di un problema di cui non ho trovato la soluzione:
 * pur essendo il bean del DummyDb un singleton, esso è singleton solo relativamente
 * al suo contesto; avendo due controller e dovendo creare quindi due contesti differenti
 * c'erano due istanze del DummyDb nell'applicazione. Con questa classe viene istanziato 1
 * solo DummyDb sul quale vengono effettuate le operazioni.
 * 
 */
package com.secretary.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.secretary.config.DummyDbConfig;
import com.secretary.persistance.DummyDb;

public class BeanBuilder {
	private static ApplicationContext ctx = new AnnotationConfigApplicationContext(DummyDbConfig.class);
	private static DummyDb dummyDb = (DummyDb) ctx.getBean("dummyDbInit");
	
	private BeanBuilder() {}
	
	public static DummyDb getIstance() {
		return dummyDb;
	}
}
