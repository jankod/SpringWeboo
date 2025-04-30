package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.DefaultWidget;
import hr.ja.weboo.utils.WebooUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
//@Accessors()
public class Button extends DefaultWidget implements HasClasses {
    private final String label;

    private Color color = Color.PRIMARY;

    public Button(String label) {
        this.label = label;
    }

    @Override
    public String toHtml() {
        addClass("btn btn-" + color.toCssClass());

        String html = """
                <button type="button" class="${classes}"  id="${widgetId}">
                    ${label}
                </button>
                """;
        return WebooUtil.quteThis(html, this);
    }

    public static void main(String[] args) {
        Button button = new Button("Click Me");
        button.addClass("btn-lg");
        System.out.println(button.toHtml());
    }
}
