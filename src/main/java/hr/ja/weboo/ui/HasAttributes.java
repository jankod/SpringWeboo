package hr.ja.weboo.ui;

import hr.ja.weboo.utils.WebooUtil;
import j2html.utils.EscapeUtil;

import java.util.Map;

public interface HasAttributes {
    Map<String, Object> getAttributes();

    default void addClass(String css) {
        Object existing = getAttributes().get("class");
        if (existing != null) {
            String newClasses = existing + " " + css;
            getAttributes().put("class", newClasses);
        } else {
            getAttributes().put("class", css);
        }
    }

    default void data(String key, Object value) {
        getAttributes().put("data-" + key, value);
    }

    default void booleanAttr(String name, boolean enabled) {
        if (enabled) {
            getAttributes().put(name, name);
        } else {
            getAttributes().remove(name);
        }
    }


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

    default String toHtmlAttribute(String attributeName) {
        Object attributeValue = getAttributes().get(attributeName);
        if (attributeValue != null) {
            return attributeName + "=\"" + WebooUtil.escape(attributeValue.toString()) + "\"";
        } else {
            return "";
        }
    }
}
