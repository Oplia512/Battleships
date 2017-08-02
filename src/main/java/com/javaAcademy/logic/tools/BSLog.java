package com.javaAcademy.logic.tools;

import org.apache.log4j.Logger;

public class BSLog {

    final static Logger logger = Logger.getLogger("BattleShips Logger");

    public static void debug(String logMessage) {
        if (logger.isDebugEnabled()) {
            logger.debug(logMessage);
        }
    }

    public static void info(String logMessage) {
        if (logger.isInfoEnabled()) {
            logger.info(logMessage);
        }
    }

    public static void error(String logMessage) {
        logger.error(logMessage);
    }

    public static void warn(String logMessage) {
        logger.warn(logMessage);
    }
}
