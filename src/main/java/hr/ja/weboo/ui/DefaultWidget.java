package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Widget;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class DefaultWidget extends Widget  {

//    private Map<String, String> attributes = new HashMap<>();
//
//    public DefaultWidget addAttr(String param, String value) {
//        attributes.merge(param, value, (a, b) -> a + " " + b);
//        return this;
//    }
//
//    public DefaultWidget setAttr(String param, String value) {
//        attributes.put(param, value);
//        return this;
//    }
    private String classes;

    public DefaultWidget addClass(String classes) {
        if (this.classes == null) {
            this.classes = classes;
        }else {
            this.classes += " " + classes;
        }
        return this;
    }

    public DefaultWidget setClass(String classes) {
        this.classes = classes;
        return this;
    }

    public String getClasses() {
        return classes;
    }

//    public String getIdClass() {
//        return paramValue("id", getWidgetId())
//               + paramValue("class", getClasses().toString())
//               + paramValue("style", getStyle());
//    }

//    private String paramValue(String param, String value) {
//        if (param == null || param.isEmpty()) {
//            return "";
//        }
//        if (value == null || value.isEmpty()) {
//            return "";
//        }
//        return param + "=\"" + value + "\" ";
//    }


}
