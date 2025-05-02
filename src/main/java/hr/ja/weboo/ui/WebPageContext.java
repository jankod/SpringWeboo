package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.J4HtmlWidget;
import hr.ja.weboo.ui.widgets.Widget;
import hr.ja.weboo.utils.CallerInfo;
import hr.ja.weboo.utils.WebooUtil;
import j2html.tags.ContainerTag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Data
@Slf4j
public class WebPageContext {

    //private LinkedList<Widget> widgets = new LinkedList<>();

    private Map<String, ?> model;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private static final ThreadLocal<WebPageContext> currentContext = new ThreadLocal<>();

    // Mapa za spremanje proizvoljnih podataka specifičnih za zahtjev
    // koje možda želite dijeliti između faza renderiranja ili komponenti.
    private final Map<String, Object> attributes = new HashMap<>();

    //private final String pageId;

    public WebPageContext() {
      //  this.pageId = UUID.randomUUID().toString();
        currentContext.set(this);
    }



    public static WebPageContext getCurrentContext() {
        WebPageContext ctx = currentContext.get();
        if (ctx == null) {
            throw new IllegalStateException("WebPageContext not available in current thread.");
        }
        return ctx;
    }

    public static void clearCurrentContext() {
        currentContext.remove();
    }


}