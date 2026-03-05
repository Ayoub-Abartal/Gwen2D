package main.engine.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Facade over java.util.logging for simplified logging.
 * 
 * usage: 
 *   Log.info(message)
 *   Log.warning(message)
 *   Log.error(message, exception)
 */
public class Log {
    
    private static final Logger logger = Logger.getLogger("Game Engine");

    /** Prevents instantiation of utility class. */
    private Log(){
        throw new IllegalStateException("Utility class");
    }


    /** Logs informational message. */
    public static void info(String message){
        logger.log(Level.INFO, message);
    }


    /** Logs warning message. */
    public static void warning(String message){
        logger.log(Level.WARNING, message);
    }


    /** Logs error message. */
    public static void error(String message){
        logger.log(Level.SEVERE, message);
    }


    /** Logs error message with exception stack trace. */
    public static void error(String message, Throwable exception){
        logger.log(Level.SEVERE, message, exception);
    }

    /** Logs debug message (disabled by default). */
    public static void debug(String message){
        logger.log(Level.FINE, message);
    }
}
