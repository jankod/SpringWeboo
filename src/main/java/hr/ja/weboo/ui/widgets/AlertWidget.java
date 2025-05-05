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

    private Icon icon = Icon.checkbox();

    public AlertWidget(String message, Color color, Icon icon) {
        this(message, color);
        this.icon = icon;
    }

    public AlertWidget(String message, Color color) {
        this.message = message;
        this.color = color;
        add(new HtmlWidget(message));

    }

    @Override
    public String toHtml() {

        @Language("InjectedFreeMarker")
        String t = """
                  <div class="alert alert-${color} d-flex align-items-center ${classes}" id="{widgetId}" role="alert">
                  ${icon}
                  <div>${children}</div>
                  </div>
                """;


        return QuteUtil.quteThis(t, this);

    }

    public static AlertWidget warning(String message) {
        return new AlertWidget(message, Color.WARNING, Icon.home());
    }


//    public JavaScriptFunction callShowMessage(String message) {
//        return new CustomJavaScript("""
//                  $("#message_"+this.id).html(this.message);
//              """, "message", message, "id", getWidgetId());
//    }
}
