package hr.ja.weboo.utils;

import hr.ja.weboo.ui.HasHtml;
import hr.ja.weboo.ui.WebPageContext;
import hr.ja.weboo.ui.widgets.Widget;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class WidgetsLinkedList extends LinkedList<Widget> implements HasHtml {

    @Override
    public String toString() {
        return toHtml();
    }


    @Override
    public String toHtml() {
        return stream()
              .map(Widget::toHtml)
              .collect(Collectors.joining("\n"));
    }


}
