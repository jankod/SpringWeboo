package hr.ja.weboo.ui.widgets;


import hr.ja.weboo.ui.HasAttributes;

public interface BaseAttributes extends HasAttributes {

    default String getId() {
        return getAttributes().get("id");
    }

    default String getClassName() {
        return getAttributes().get("class");
    }

    String getStyle();

    String getTitle();


}
