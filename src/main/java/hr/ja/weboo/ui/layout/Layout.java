package hr.ja.weboo.ui.layout;

import hr.ja.weboo.ui.Page;
import hr.ja.weboo.ui.PageContext;
import hr.ja.weboo.ui.widgets.Widget;
import hr.ja.weboo.utils.WebooUtil;

import java.util.Objects;

public interface Layout {

     LayoutModel createModel(PageContext ctx, Page page);

      String toHtml(LayoutModel model);

      default String createScriptJsCode(LayoutModel model) {
            return "";
      } // koristi model.bodyHtml ili model.context


    String toHtml(PageContext context, Page page);

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
