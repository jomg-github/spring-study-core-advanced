package juststudy.springadvanced.trace;

import lombok.Getter;

import java.util.UUID;

@Getter
public class LogTrace {
    private String id;
    private int level;

    public LogTrace() {
        this.id = createId();
        this.level = 0;
    }

    private LogTrace(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public LogTrace nextId() {
        return new LogTrace(id, level + 1);
    }

    public LogTrace previousId() {
        return new LogTrace(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }


}
