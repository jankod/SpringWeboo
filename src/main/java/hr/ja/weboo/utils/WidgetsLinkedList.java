package hr.ja.weboo.utils;

import hr.ja.weboo.ui.HasHtml;
import hr.ja.weboo.ui.WebPageContext;
import hr.ja.weboo.ui.widgets.Widget;

import java.util.Iterator;
import java.util.LinkedList;

public class WidgetsLinkedList extends LinkedList<Widget> implements HasHtml {

    @Override
    public String toString() {
        return toHtml();
    }

    @Override
    public String toHtml() {
        Iterator<Widget> it = iterator();
        if (!it.hasNext())
            return "";

        StringBuilder sb = new StringBuilder();
        for (; ; ) {
            Widget widget = it.next();
            sb.append(widget.toHtml());
            if (!it.hasNext())
                return sb.toString();
            sb.append('\n').append(' ');
        }
    }
}
