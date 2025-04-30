package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.HasChildren;
import hr.ja.weboo.ui.widgets.Widget;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class DefaultWidget extends Widget implements HasAttributes, HasChildren {

    private final Map<String, Object> attributes = new HashMap<>();

    private final LinkedList<Widget> children = new LinkedList<>();


    @Override
    public Widget add(Widget widget) {
        children.add(widget);
        return this;
    }

    @Override
    public void removeChild(Widget widget) {
        children.remove(widget);
    }

    @Override
    public Widget getChild(int index) {
        return children.get(index);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public void clearChildren() {
        children.clear();
    }

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Override
    public String toHtml() {
        return "NOT IMPLEMENTED " + getClass().getName();
    }
}
