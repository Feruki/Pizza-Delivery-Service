import org.slf4j.*;

public class SingleLogger {
    private static final Logger log = LoggerFactory.getLogger(SingleLogger.class);

    private SingleLogger() {}

    public static Logger getLogger() {
        return log;
    }
}