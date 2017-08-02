package com.javaAcademy.logic.tools;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NResolver {

    private static I18NResolver instance;
    private Locale locale;

    private I18NResolver(Locale locale) {
        this.locale = locale;
    }

    static void updateLocale(Locale locale) {
        getI18NResolverInstance().locale = locale;
    }

    public static I18NResolver createI18NResolverInstance(Locale locale) {
        if (instance == null){
            instance = new I18NResolver(locale);
        }
        return instance;
    }
    
    public static I18NResolver getI18NResolverInstance() {
    	return instance;
    }

    public static String getMsgByKey(String key) {
        return ResourceBundle.getBundle("Messages", getI18NResolverInstance().locale).getString(key);
    }
}
