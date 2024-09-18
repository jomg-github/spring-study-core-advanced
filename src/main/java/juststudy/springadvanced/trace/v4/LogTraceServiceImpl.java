package juststudy.springadvanced.trace.v4;

import juststudy.springadvanced.trace.LogTrace;
import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("logTraceServiceV4")
public class LogTraceServiceImpl implements LogTraceService {

    private static final String LOG_START_PREFIX = "--->";
    private static final String LOG_COMPLETE_PREFIX = "<---";
    private static final String LOG_EXCEPTION_PREFIX = "<-X-";

    private ThreadLocal<LogTrace> logTraceHolder = new ThreadLocal<>();

    @Override
    public LogTraceStatus begin(String message) {
        syncLogTrace();

        LogTrace logTrace = logTraceHolder.get();
        Long startTime = System.currentTimeMillis();

        log.info("[{}] {}{}", logTrace.getId(), addSpace(LOG_START_PREFIX, logTrace.getLevel()), message);

        return new LogTraceStatus(logTrace, startTime, message);
    }

    private void syncLogTrace() {
        LogTrace logTrace = logTraceHolder.get();
        if (logTrace == null) {
            logTraceHolder.set(new LogTrace());
        } else {
            logTraceHolder.set(logTrace.nextId());
        }
    }

    @Override
    public void end(LogTraceStatus status) {
        complete(status, null);
    }

    @Override
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

        releaseLogTrace();
    }

    private void releaseLogTrace() {
        LogTrace logTrace = logTraceHolder.get();
        if (logTrace.isFirstLevel()) {
            logTraceHolder.remove();
        } else {
            logTraceHolder.set(logTrace.previousId());
        }
    }

}
