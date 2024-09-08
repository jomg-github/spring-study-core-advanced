package juststudy.springadvanced.trace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogTraceServiceImpl {

    private static final String LOG_START_PREFIX = "--->";
    private static final String LOG_COMPLETE_PREFIX = "<---";
    private static final String LOG_EXCEPTION_PREFIX = "<-X-";

    public LogTraceStatus begin(String message) {
        LogTrace logTrace = new LogTrace();
        Long startTime = System.currentTimeMillis();

        log.info("[{}] {}{}", logTrace.getId(), addSpace(LOG_START_PREFIX, logTrace.getLevel()), message);

        return new LogTraceStatus(logTrace, startTime, message);
    }


    public LogTraceStatus beginSync(LogTrace beforeTrace, String message) {
        LogTrace logTrace = beforeTrace.nextId();
        Long startTime = System.currentTimeMillis();

        log.info("[{}] {}{}", logTrace.getId(), addSpace(LOG_START_PREFIX, logTrace.getLevel()), message);

        return new LogTraceStatus(logTrace, startTime, message);
    }

    public void end(LogTraceStatus status) {
        complete(status, null);
    }

    public void exception(LogTraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(LogTraceStatus status, Exception e) {
        Long completeTime = System.currentTimeMillis() - status.getStartTime();
        LogTrace logTrace = status.getLogTrace();

        if (e == null) {
            log.info("[{}] {}{} time={}ms", logTrace.getId(), addSpace(LOG_COMPLETE_PREFIX, logTrace.getLevel()), status.getMessage(), completeTime);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", logTrace.getId(), addSpace(LOG_EXCEPTION_PREFIX, logTrace.getLevel()), status.getMessage(), completeTime, e.getMessage());
        }

    }

    private static String addSpace(String logPrefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level ; i++) {
            sb.append((i == level - 1) ? "|" + logPrefix : "|\t");
        }
        return sb.toString();
    }

}
