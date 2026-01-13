package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.RenderedContext;
import hr.ja.weboo.ui.WidgetWithAtributes;
import hr.ja.weboo.ui.PageContext;
import hr.ja.weboo.utils.QuteUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Button extends WidgetWithAtributes implements HasClasses {

    private final String label;

    private Color color = Color.PRIMARY;

    public Button(String label) {
        this.label = label;
    }

    @Override
    public void render(RenderedContext context) {
        addClass("btn btn-" + color.toCssClass());

        String template = """
                <button type="button" class="${classes}"  id="${widgetId}">
                    ${label}
                </button>
                """;
        context.qute(template, this);
    }

}
