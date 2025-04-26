package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.utils.WebooUtil;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Button extends Widget {
    private final String label;

    private Color color = Color.PRIMARY;

    public Button(String label) {
        this.label = label;
    }

    @Override
    public String toHtml() {
        addClass("btn btn-" + color.toCssClass());
        //language=HTML
        String html = """
                <button type="button" class="{classes}" id='{id}'>
                    {label}
                </button>
                """;
        return WebooUtil.quteKeyValue(html,
                "classes", getClasses(),
                "id", getWidgetId(),
                "label", label);
    }

    public static void main(String[] args) {
        Button button = new Button("Click Me");
        button.addClass("btn-lg");
        System.out.println(button.toHtml());
    }
}
