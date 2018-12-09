import cn.tommyyang.slf4j4json.LoggerFactory;
import cn.tommyyang.slf4j4json.logger.JsonLogger;
import cn.tommyyang.slf4j4json.logger.Logger;

/**
 * @author TommyYang on 2018/12/9
 */
public class Log4jTest {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("file");
        JsonLogger vinciLog = logger.info().strField("app", "vinci").getLogger();
        vinciLog.strField("index","server-end-log").strField("event", "vinci stream").strField("info", "com.jianshu.logger1111").log();
    }

}
