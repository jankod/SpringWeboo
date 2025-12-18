package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.HasChildren;
import hr.ja.weboo.ui.widgets.Widget;
import hr.ja.weboo.utils.WidgetsLinkedList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompositeWidget extends DefaultWidget implements HasChildren {

    private final WidgetsLinkedList children = new WidgetsLinkedList();

    @Override
    public String toHtml() {
        return """
                  <div id="%s" %s>
                      %s
                  </div>
              """.formatted(
              getWidgetId(),
              toHtmlAttributes(),
              renderChildren()
        );
    }

    protected String renderChildren() {
        return children.stream()
              .map(Widget::toHtml)
              .collect(joining("\n"));
    }
}
