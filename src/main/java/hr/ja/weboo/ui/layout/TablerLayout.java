package hr.ja.weboo.ui.layout;

import hr.ja.weboo.ui.Page;
import hr.ja.weboo.ui.PageContext;
import hr.ja.weboo.ui.widgets.Widget;
import hr.ja.weboo.utils.QuteUtil;
import hr.ja.weboo.utils.WebooUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.intellij.lang.annotations.Language;

import java.util.Map;

@Data
@Slf4j
public class TablerLayout implements Layout {

    private Widget headSlot;
    private Widget bodyTopSlot;
    private Widget boodyFooterSlot;

    private String bodyHtml;
    private String scriptCode;
    private String title;


    @Override
    public LayoutModel createModel(PageContext context, Page page) {
        String resolvedBodyHtml = WebooUtil.widgetToHtml(context, page.getWidgets());
        String resolvedScriptCode = createScriptJsCode(page);
        return new LayoutModel(
                page.getTitle(),
                resolvedBodyHtml,
                headSlot,
                bodyTopSlot,
                boodyFooterSlot,
                resolvedScriptCode,
                context
        );
    }

    @Override
    public String toHtml(LayoutModel model) {
        return renderTemplate(model);
    }

    @Override
    public String toHtml(PageContext context, Page page) {
        return toHtml(createModel(context, page));
    }

    private String renderTemplate(LayoutModel model) {

        @Language("InjectedFreeMarker") String t = """
                <!doctype html>
                <html lang="en">
                
                <head>
                  <meta charset="utf-8" />
                  <meta name="viewport" content="width=device-width, initial-scale=1" />
                  <title>{title}</title>
                  <link rel="stylesheet" href="tabler.min.css" />
                  {scriptCode.raw}
                  {headSlot or ""}
                </head>
                
                <body>
                    {bodyTopSlot or ""}
                    ${topBody()}
                    <div class="container">
                        {bodyHtml.raw}
                    </div>
                    {boodyFooterSlot or ""}
                  <script src="tabler.min.js"></script>
                </body>
                
                </html>
                """;
        return QuteUtil.quteMap(t, Map.of(
                "title", model.title(),
                "scriptCode", model.scriptCode(),
                "headSlot", model.headSlot(),
                "bodyTopSlot", model.bodyTopSlot(),
                "bodyHtml", model.bodyHtml(),
                "boodyFooterSlot", model.bodyFooterSlot()
        ));
    }

    public Widget topBody( ) {
        return null;
    }
}
