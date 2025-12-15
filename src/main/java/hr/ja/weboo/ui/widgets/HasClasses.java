package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.HasAttributes;

import java.util.LinkedHashSet;
import java.util.Set;

public interface HasClasses extends HasAttributes {
    default String getClasses() {
        Object aClass = getAttributes().get("class");
        if (aClass == null) {
            return "";
        }
        return aClass.toString();
    }

    default void addClass(String classes) {
        if (classes == null || classes.isBlank()) {
            return;
        }
        Set<String> merged = toClassSet(getAttributes().get("class"));
        for (String cls : classes.trim().split("\\s+")) {
            if (!cls.isEmpty()) {
                merged.add(cls);
            }
        }
        if (!merged.isEmpty()) {
            getAttributes().put("class", String.join(" ", merged));
        }
    }

    default void removeClass(String classes) {
        if (classes == null || classes.isBlank()) {
            return;
        }
        Set<String> current = toClassSet(getAttributes().get("class"));
        if (current.isEmpty()) {
            return;
        }
        for (String cls : classes.trim().split("\\s+")) {
            if (!cls.isEmpty()) {
                current.remove(cls);
            }
        }
        if (current.isEmpty()) {
            getAttributes().remove("class");
        } else {
            getAttributes().put("class", String.join(" ", current));
        }
    }

    private static Set<String> toClassSet(Object attribute) {
        LinkedHashSet<String> result = new LinkedHashSet<>();
        if (attribute == null) {
            return result;
        }
        String value = attribute.toString().trim();
        if (value.isEmpty()) {
            return result;
        }
        for (String cls : value.split("\\s+")) {
            if (!cls.isEmpty()) {
                result.add(cls);
            }
        }
        return result;
    }
}
