package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.HasAttributes;

public interface HasClasses extends HasAttributes {
    default String getClasses() {
        Object aClass = getAttributes().get("class");
        if (aClass == null) {
            return "";
        }
        return aClass.toString();
    }

    default void addClass(String classes) {
        Object classAttribute = getAttributes().get(classes);
        if (classAttribute != null) {
            classes = classAttribute + " " + classes;
        }
        getAttributes().put("class", classes);
    }

    default void removeClass(String classes) {
        String classAttribute = getAttributes().get(classes).toString();
        if (classAttribute != null) {
            classAttribute = classAttribute.replace(classes, "").trim();
            getAttributes().put("class", classAttribute);
        }
    }

}
