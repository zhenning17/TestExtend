package com.util;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

// Log4j
public class LogsInit {
    protected final Logger log = Logger.getLogger(getClass().getName());
    public LogsInit(String str) {
        try {
            PropertyConfigurator.configure(CommonMethord.getRealath()
                    + "log4j.properties");
            log.warn("warn:" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LogsInit() {
    }
}
