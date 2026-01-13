package hr.ja.weboo.ui;

import hr.ja.weboo.ui.layout.Layout;
import hr.ja.weboo.ui.layout.TablerLayout;
import hr.ja.weboo.ui.widgets.J4HtmlWidget;
import hr.ja.weboo.ui.widgets.Widget;
import hr.ja.weboo.utils.CallerInfo;
import hr.ja.weboo.utils.WebooUtil;
import hr.ja.weboo.utils.WidgetsLinkedList;
import j2html.tags.ContainerTag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.View;

import java.util.Map;

/**
 * Per request web page representation.
 */
@Slf4j
@Data
public abstract class Page implements View {

    private String pageId = WebooUtil.createPageId();

    private String title = "";

    private final WidgetsLinkedList widgets = new WidgetsLinkedList();

    private Layout layout;

    protected void dump(Object object) {
        String json = WebooUtil.toJson(object);
        log.debug("Dumping object: {}", json);
    }

    public <T extends Widget> T add(T widget) {
        widgets.add(widget);
        if (WebooUtil.isDebug()) {
//            if (widget instanceof CompositeWidget) {
//                return widget;
//            }
            CallerInfo callerInfo = WebooUtil.getCallerInfo(3);
            callerInfo.setWidgetId(widget.widgetId());
            callerInfo.setWidgetName(widget.getClass().getSimpleName());
            widget.setDebugCallerInfo(callerInfo);
        }
        return widget;
    }

    public J4HtmlWidget add(ContainerTag<?> content) {
        J4HtmlWidget j4HtmlWidget = new J4HtmlWidget(content);
        return add(j4HtmlWidget);
    }

    @Override
    public @Nullable String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, @NonNull HttpServletRequest request, @NonNull HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PageContext context = new PageContext();

        context.setModel(model);
        context.setRequest(request);
        context.setResponse(response);
        try {
            render(context);

            if (WebooUtil.isDebug()) {
                // add before and after widget html comment with widget id and name
                for (Widget widget : widgets) {
                    String comment = "<!-- " + widget.getClass().getSimpleName() + " id: " + widget.widgetId() + " --> ";
                    //   String html = pageWidgets.stream().map(Widget::toHtml).collect(Collectors.joining(comment));
                }
            }

            if (layout == null) {
                layout = new TablerLayout();
            }

            String html = layout.toHtml(context, this);

            response.getWriter().write(html);
        } finally {
        }
    }

    protected abstract void render(PageContext context);
}
