package juststudy.springadvanced.trace.v5;

import juststudy.springadvanced.trace.LogTraceService;
import juststudy.springadvanced.trace.LogTraceStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractLogTemplate<T> {

    private final LogTraceService logTraceService;

    public T execute(String message) {
        LogTraceStatus status = null;

        try {
            status = logTraceService.begin(message);

            T result = call();

            logTraceService.end(status);
            return result;
        } catch (Exception e) {
            logTraceService.exception(status, e);
            throw e;
        }
    }


    protected abstract T call();
}
