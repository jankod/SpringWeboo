package hr.ja.weboo.pages;

import hr.ja.weboo.utils.WebooUtil;
import hr.ja.weboo.utils.WidgetUtil;
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
    private String classes = "";
    private String style = "";
    private final List<Widget> children = new ArrayList<>();
//    private final List<DualSideEvent> dualSideEvents = new ArrayList<>();

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
        return WidgetUtil.widgetToHtml(getChildren());
    }

//    public DualSideEvent on(String eventName) {
//        DualSideEvent clientEvent = new DualSideEvent(eventName, widgetId);
//        dualSideEvents.add(clientEvent);
//        return clientEvent;
//    }

    public void addClass(String classes) {
        this.classes += " " + classes;
    }

    public abstract String toHtml();

    protected String getIdClassStyleAttr() {
        return paramValue("id", getWidgetId())
               + paramValue("class", getClasses())
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
