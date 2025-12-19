package hr.ja.weboo.ui.layout;

import hr.ja.weboo.ui.Page;
import hr.ja.weboo.ui.PageContext;
import hr.ja.weboo.utils.WebooUtil;

import java.util.Map;

public class BootstrapLayout implements Layout {

    @Override
    public LayoutModel createModel(PageContext context, Page page) {
        String body = WebooUtil.widgetToHtml(context, page.getWidgets());
        String scriptCode = createScriptJsCode(page);
        return new LayoutModel(page.getTitle(), body, null, null, null, scriptCode, context);
    }

    @Override
    public String toHtml(LayoutModel model) {
        return renderHtml(model.bodyHtml(), model.scriptCode(), model.title());
    }

    @Override
    public String toHtml(PageContext context, Page page) {
        return toHtml(createModel(context, page));
    }

    private String renderHtml(String body, String scriptCode, String title) {

        //language=HTML
        String html = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>{title}</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
                        <!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"> -->
                    <link rel="stylesheet" href="all.css">
                    {scriptCode.raw}
                </head>
                </head>
                <body>
                <div class='container'>
                {body.raw}
                </div>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
                    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
                    <script src="all.js"></script>
                </body>
                </html>""";
        return WebooUtil.quteMap(html, Map.of("body", body, "title", title, "scriptCode", scriptCode));
    }


}
