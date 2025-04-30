package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Widget;

import java.util.ArrayList;
import java.util.List;

public class CompositeWidget extends DefaultWidget {

    private final List<Widget> children = new ArrayList<>();

    public CompositeWidget add(Widget widget) {
        children.add(widget);
        return this;
    }

    public CompositeWidget addFirst(Widget c) {
        children.addFirst(c);
        return this;
    }

    public void addAll(Widget[] widgets) {
        for (Widget widget : widgets) {
            add(widget);
        }
    }

    @Override
    public String toHtml() {
        StringBuilder html = new StringBuilder();
        for (Widget widget : children) {
            html.append(widget.toHtml()).append("\n");
        }
        return html.toString();
    }
}
