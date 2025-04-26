package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.utils.CallerInfo;
import hr.ja.weboo.utils.WebooUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Slf4j
public abstract class Widget {

    private String widgetId = WebooUtil.wigetNewId(this.getClass());
    private StringBuilder classes = new StringBuilder();
    private String style = "";
    private final List<Widget> children = new ArrayList<>();

    private CallerInfo _callerInfo = null;

    public Widget(Widget... widgets) {
        addAll(widgets);
    }

    public Widget add(Widget c) {
        children.add(c);
        return c;
    }

    public void addAll(Widget[] widgets) {
        if (widgets != null) {
            for (Widget w : widgets) {
                add(w);
            }
        }
    }

    public String toChildrenHtml() {
        return WebooUtil.widgetToHtml(getChildren());
    }

    public Widget addClass(String classes) {
        this.classes.append(classes);
        return this;
    }

    public abstract String toHtml();

    protected String getIdClassStyleAttr() {
        return paramValue("id", getWidgetId())
               + paramValue("class", getClasses().toString())
               + paramValue("style", getStyle());
    }

    private String paramValue(String param, String value) {
        if (param == null || param.isEmpty()) {
            return "";
        }
        if (value == null || value.isEmpty()) {
            return "";
        }
        return param + "=\"" + value + "\" ";
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }


}
