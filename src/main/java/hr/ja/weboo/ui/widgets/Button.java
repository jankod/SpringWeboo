package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.WidgetWithAtributes;
import hr.ja.weboo.ui.PageContext;
import hr.ja.weboo.utils.QuteUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
//@Accessors()
public class Button extends WidgetWithAtributes implements HasClasses {
    private final String label;

    private Color color = Color.PRIMARY;

    public Button(String label) {
        this.label = label;
    }

    @Override
    public String toHtml(PageContext context) {
        addClass("btn btn-" + color.toCssClass());

        String html = """
                <button type="button" class="${classes}"  id="${widgetId}">
                    ${label}
                </button>
                """;
        return QuteUtil.quteThis(html, this);
    }

    public static void main(String[] args) {
        Button button = new Button("Click Me");
        button.addClass("btn-lg");
        System.out.println(button.toHtml(new PageContext()));
    }
}
