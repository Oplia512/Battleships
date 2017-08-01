package com.java_academy.logic.tools;

import static org.testng.Assert.assertEquals;

import java.util.Locale;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class I18NTest {
	
	private String language = new String("en");
    private String country = new String("EN");
    private Locale locale;
    
	@BeforeMethod
	public void createIOResolver() {
		locale = new Locale(language, country);
		
		I18NResolver.createI18NResolverInstance(locale);
	}
    
    
	@Test
	public void testInternationalization() {
        assertEquals("Hello World!", I18NResolver.getMsgByKey("hello.world"));
	}
	
	@Test
	public void testInternationalization1() {
		String language = new String("pl");
	    String country = new String("PL");
        
        Locale defaultLocale = new Locale(language, country);
        I18NResolver.updateLocale(defaultLocale);

        assertEquals("Witaj \u015Bwiecie!", I18NResolver.getMsgByKey("hello.world"));
	}

}
