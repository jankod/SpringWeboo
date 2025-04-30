package hr.ja.weboo.ui;

import hr.ja.weboo.utils.WebooUtil;
import j2html.utils.EscapeUtil;

import java.util.Map;

public interface HasAttributes {
    Map<String, Object> getAttributes();

    default Object getAttribute(String name) {
        return getAttributes().get(name);
    }

    default void setAttribute(String name, Object value) {
        getAttributes().put(name, value);
    }

    default String toHtmlAttributes() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : getAttributes().entrySet()) {
            sb.append(entry.getKey()).append("=\"").append(WebooUtil.htmlEscape(entry.getValue().toString())).append("\" ");
        }
        return sb.toString().trim();
    }
}
