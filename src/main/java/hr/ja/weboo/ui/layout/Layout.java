package hr.ja.weboo.ui.layout;

import hr.ja.weboo.ui.Page;
import hr.ja.weboo.ui.WebPageContext;
import hr.ja.weboo.ui.widgets.Widget;
import hr.ja.weboo.utils.WebooUtil;

import java.util.Objects;

public interface Layout {

    String toHtml(WebPageContext context, Page page);


    default String createScriptJsCode(Page page) {
        String scriptCode = "";
        if (WebooUtil.isDebug()) {
            String callerInfoJson = "";
            callerInfoJson = "const WEBOO_WIDGETS_INFO = " + WebooUtil.toJson(page.getWidgets().stream()
                    .map(Widget::getDebugCallerInfo)
                    .filter(Objects::nonNull)
                    .toList()) + ";";
            scriptCode = """
                    <script>
                        %s
                    </script>
                    """.formatted(callerInfoJson);
        }
        return scriptCode;
    }
}
