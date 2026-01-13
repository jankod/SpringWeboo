package hr.ja.weboo.utils;

import hr.ja.weboo.ui.RenderedContext;
import hr.ja.weboo.ui.widgets.Widget;

import java.util.LinkedList;

public class WidgetsLinkedList extends LinkedList<Widget> {

    public void render(RenderedContext context) {
        for (Widget widget : this) {
            widget.render(context);
        }
    }

}
