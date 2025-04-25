package hr.ja.weboo.model;

import hr.ja.weboo.pages.Page;
import hr.ja.weboo.pages.Widget;
import hr.ja.weboo.utils.WebooUtil;
import io.quarkus.qute.Qute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.util.Map;

@Data
public class Layout {

    private Map<String, ?> model;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public String toHtml(Page page) {
        String html = """
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>{title}</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
                </head>
                </head>
                <body>
                <h1>Layout</h1>
                <div>{page}</div>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
                </body>
                </html>""";
        return WebooUtil.qute(html, Map.of("page", page, "title", page.getTitle()));
    }

}
