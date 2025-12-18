package hr.ja.weboo.ui.widgets;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class WidgetIdGenerator {

    private final Map<Class<?>, AtomicLong> counters = new HashMap<>();

    public String next(Class<? extends Widget> type) {
        return type.getSimpleName() + "_" +
               counters.computeIfAbsent(type, k -> new AtomicLong()).incrementAndGet();
    }
}
