package hr.ja.weboo.ui.widgets;

public interface HasChildren {
    Widget add(Widget widget);

    void removeChild(Widget widget);

    Widget getChild(int index);

    int getChildCount();

    void clearChildren();

    boolean hasChildren();
}
