package juststudy.springadvanced.trace;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogTraceStatus {
    private LogTrace logTrace;
    private Long startTime;
    private String message;
}
