package hr.ja.weboo.ui.widgets;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public interface HasChildren {

    LinkedList<Widget> getChildren();

    default void add(Widget widget) {
         getChildren().add(widget);
    }

    default void removeChild(Widget widget) {
        getChildren().remove(widget);
    }

    default Widget getChild(int index) {
        return getChildren().get(index);
    }

    default int getChildCount() {
        return getChildren().size();
    }

    default void clearChildren() {
        getChildren().clear();
    }

    default boolean hasChildren() {
        return !getChildren().isEmpty();
    }
}
