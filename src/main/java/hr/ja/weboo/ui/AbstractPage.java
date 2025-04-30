package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Widget;
import hr.ja.weboo.utils.WebooUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.View;

import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Data
public abstract class AbstractPage implements View {

    private String pageId = WebooUtil.createPageId();

    protected String title = "";

    // private List<Widget> widgets = new ArrayList<>();


    public void dump(Object u) {
        String json = WebooUtil.toJson(u);
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        WebPageContext web = new WebPageContext();
        web.setModel(model);
        web.setRequest(request);
        web.setResponse(response);
        render(web);


        Layout layout = new Layout();

        LinkedList<Widget> pageWidgets = web.getWidgets();
        if (WebooUtil.isDebug()) {

            // add before and after widget html comment with widget id and name
            for (Widget widget : pageWidgets) {

                String comment = "<!-- " + widget.getClass().getSimpleName() + " id: " + widget.widgetId() + " --> ";
                String html = pageWidgets.stream().map(Widget::toHtml).collect(Collectors.joining(comment));
            }
        }

        String html = layout.toHtml(web, this);

        response.getWriter().write(html);
    }

    protected void render(WebPageContext webPageContext) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
