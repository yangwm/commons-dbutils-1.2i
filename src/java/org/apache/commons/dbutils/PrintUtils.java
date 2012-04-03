/**
 * 
 */
package org.apache.commons.dbutils;

import java.io.PrintStream;
import java.util.Properties;
import static java.lang.System.out;

/**
 * print utils 
 * 
 * @author yangwm in May 18, 2009 10:49:26 PM
 */
class PrintUtils {

	/**
	 * if print is true can print to console, other not print. 
	 */
	private static final boolean PRINT_SQL;
	    
    
    // print config file name  
    private static final String PRINT_CONFIG = "print.properties";
    
    /**
     * initialization all static final attribute 
     */
    static {
        Properties env = ConfigUtil.getConfigProperties(PRINT_CONFIG);
        
        PRINT_SQL = Boolean.parseBoolean((String)env.get("PRINT_SQL"));
    }

    
    // Print with a newline:
    public static void println(Object obj) {
		if (PRINT_SQL == true) {
			out.println(obj);
		}
    }
      
    // Print a newline by itself:
    public static void println() {
		if (PRINT_SQL == true) {
			out.println();
		}
    }
      
    // Print with no line break:
    public static void print(Object obj) {
		if (PRINT_SQL == true) {
			out.print(obj);
		}
    }
      
    // The new Java SE5 printf() (from C):
    public static PrintStream printf(String format, Object... args) {
        return out.printf(format, args);
    }
  
} ///:~
