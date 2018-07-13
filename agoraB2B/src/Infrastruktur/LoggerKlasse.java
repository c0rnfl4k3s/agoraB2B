package Infrastruktur;

import java.io.IOException;
import java.util.logging.*;

public class LoggerKlasse {


    private static final Logger log = java.util.logging.Logger.getLogger(LoggerKlasse.class.getName());
    private static LoggerKlasse meinLogger = null;

    private LoggerKlasse() {
        super();
    }

    public static LoggerKlasse getInstance() {
        if (meinLogger == null) {
            meinLogger = new LoggerKlasse();
        }
        return meinLogger;
    }

    public void addHandler(Logger log) throws SecurityException, IOException {
        Handler handler = new FileHandler("resource\\meinlog.txt", true);
        handler.setLevel(Level.FINEST);
        log.setLevel(Level.FINEST);
        log.addHandler(handler);
    }

    public Logger getLog() {
        return log;
    }

}
