package juststudy.springadvanced.trace.v3;

import juststudy.springadvanced.trace.LogTrace;
import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("LogTraceServiceV3")
public class LogTraceServiceImpl implements LogTraceService {

    private static final String LOG_START_PREFIX = "--->";
    private static final String LOG_COMPLETE_PREFIX = "<---";
    private static final String LOG_EXCEPTION_PREFIX = "<-X-";

    private static LogTrace logTraceHolder; // log trace 동기화, 동시성 이슈 발생

    @Override
    public LogTraceStatus begin(String message) {
        syncLogTrace();

        LogTrace logTrace = logTraceHolder;
        Long startTime = System.currentTimeMillis();

        log.info("[{}] {}{}", logTrace.getId(), addSpace(LOG_START_PREFIX, logTrace.getLevel()), message);

        return new LogTraceStatus(logTrace, startTime, message);
    }

    private void syncLogTrace() {
        if (logTraceHolder == null) {
            logTraceHolder = new LogTrace();
        } else {
            logTraceHolder = logTraceHolder.nextId();
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
        if (logTraceHolder.isFirstLevel()) {
            logTraceHolder = null;
        } else {
            logTraceHolder = logTraceHolder.previousId();
        }
    }

}
