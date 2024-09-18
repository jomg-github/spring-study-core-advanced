package juststudy.springadvanced.trace.v6;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogTemplate {

    private final LogTraceService logTraceService;

    public <T> T execute(String message, LogCallback<T> callback) {
        LogTraceStatus status = null;

        try {
            status = logTraceService.begin(message);

            T result = callback.call();

            logTraceService.end(status);
            return result;
        } catch (Exception e) {
            logTraceService.exception(status, e);
            throw e;
        }
    }

}
