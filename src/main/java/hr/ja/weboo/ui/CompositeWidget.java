package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.HasChildren;
import hr.ja.weboo.ui.widgets.Widget;
import hr.ja.weboo.utils.WidgetsLinkedList;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static java.util.stream.Collectors.joining;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompositeWidget extends WidgetWithAtributes implements HasChildren {

    private final WidgetsLinkedList children = new WidgetsLinkedList();

    @Override
    public void render(RenderedContext context) {
        return """
                  <div id="%s" %s>
                      %s
                  </div>
              """.formatted(
              getWidgetId(),
              toHtmlAttributes(),
              renderChildren(context)
        );
    }

    protected void renderChildren(RenderedContext context) {
        for (Widget child : getChildren()) {
            child.render(context);
        }
    }


}
