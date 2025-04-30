package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.CompositeWidget;
import hr.ja.weboo.ui.DefaultWidget;
import hr.ja.weboo.utils.WebooUtil;
import j2html.tags.DomContent;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

import static j2html.TagCreator.div;

@Getter
public class AlertWidget extends CompositeWidget {

    private final String message;

    @Setter
    private Color color;

    public AlertWidget(String message) {
        this.message = message;
        this.color = Color.INFO;

    }

    public AlertWidget(String message, Color color) {
        this.message = message;
        this.color = color;
        addClass("alert alert-dismissible fade show alert-" + color.toName());
        add(new HtmlWidget(message));

    }

    @Override
    public String toHtml() {

        // language=HTML
        String html = """
                <div class="alert alert-danger alert-dismissible" role="alert">
                    <div class="alert-icon">
                        <!-- Download SVG icon from http://tabler.io/icons/icon/alert-circle -->
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                             stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                             class="icon alert-icon icon-2">
                            <path d="M3 12a9 9 0 1 0 18 0a9 9 0 0 0 -18 0"></path>
                            <path d="M12 8v4"></path>
                            <path d="M12 16h.01"></path>
                        </svg>
                    </div>
                    <div>
                        {content}
                    </div>
                    <a class="btn-close" data-bs-dismiss="alert" aria-label="close"></a>
                </div>
                """;

        return WebooUtil.quteKeyValue(html,
                "content", getChildren().toString());

    }


    private Iterable<? extends DomContent> getChildren() {
        return null;
    }

//    public JavaScriptFunction callShowMessage(String message) {
//        return new CustomJavaScript("""
//                  $("#message_"+this.id).html(this.message);
//              """, "message", message, "id", getWidgetId());
//    }
}
