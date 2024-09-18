package juststudy.springadvanced.trace;

public interface LogTraceService {
    LogTraceStatus begin(String message);
    void end(LogTraceStatus status);
    void exception(LogTraceStatus status, Exception e);

    default String addSpace(String logPrefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level ; i++) {
            sb.append((i == level - 1) ? "|" + logPrefix : "|\t");
        }
        return sb.toString();
    }

}
