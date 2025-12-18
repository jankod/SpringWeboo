package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.CompositeWidget;
import hr.ja.weboo.utils.QuteUtil;
import lombok.Getter;
import lombok.Setter;
import org.intellij.lang.annotations.Language;

@Getter
@Setter
public class AlertWidget extends CompositeWidget implements HasClasses {

    private final String message;


    private Color color;

    private Icon icon = Icons.checkbox();

    public AlertWidget(String message, Color color, Icon icon) {
        this(message, color);
        this.icon = icon;
    }

    public AlertWidget(String message, Color color) {
        this.message = message;
        this.color = color;
        addClass("alert-dismissible fade show");
        add(new HtmlWidget(message));

    }

    @Override
    public String toHtml() {

        @Language("InjectedFreeMarker")
        String t = """
                  <div class="alert ${classes} alert-${color} d-flex align-items-center" id="{widgetId}" role="alert">
                  ${icon}
                  <div>${children}</div>
                  </div>
                """;


        return QuteUtil.quteThis(t, this);

    }

    public static AlertWidget warning(String message) {
        return new AlertWidget(message, Color.WARNING, Icons.checkbox());
    }


//    public JavaScriptFunction callShowMessage(String message) {
//        return new CustomJavaScript("""
//                  $("#message_"+this.id).html(this.message);
//              """, "message", message, "id", getWidgetId());
//    }
}
