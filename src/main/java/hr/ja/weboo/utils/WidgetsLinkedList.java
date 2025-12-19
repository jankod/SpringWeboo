package hr.ja.weboo.utils;

import hr.ja.weboo.ui.HasHtml;
import hr.ja.weboo.ui.PageContext;
import hr.ja.weboo.ui.widgets.Widget;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class WidgetsLinkedList extends LinkedList<Widget> implements HasHtml {

    @Override
    public String toString() {
        PageContext context;
        try {
            context = PageContext.getCurrentContext();
        } catch (IllegalStateException ex) {
            context = new PageContext();
        }
        return toHtml(context);
    }


    @Override
    public String toHtml(PageContext context) {
        return stream()
              .map(widget -> widget.toHtml(context))
              .collect(Collectors.joining("\n"));
    }


}
