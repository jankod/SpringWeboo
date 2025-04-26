package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Widget;
import hr.ja.weboo.utils.WebooUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Layout {


    private String title;

    private Map<String, ?> model;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public String toHtml(AbstractPage page) {

        String body = WebooUtil.widgetToHtml(page.getWidgets());


        String scriptCode = "";
        if (WebooUtil.isDebug()) {
            String callerInfoJson = "";
            callerInfoJson = "const WEBOO_WIDGETS_INFO = " + WebooUtil.toJson(page.getWidgets().stream()
                    .map(Widget::get_callerInfo)
                    .toList()) + ";";
            scriptCode = """
                    <script>
                        %s
                    </script>
                    """.formatted(callerInfoJson);
        }
        //language=HTML
        String html = """
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>{title}</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
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
