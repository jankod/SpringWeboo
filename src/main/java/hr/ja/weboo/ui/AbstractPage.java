package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Widget;
import hr.ja.weboo.utils.CallerInfo;
import hr.ja.weboo.utils.WebooUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@Data
public abstract class AbstractPage implements View {

    private List<Widget> widgets = new ArrayList<>();

    public <T extends Widget> T add(T widget) {
        widgets.add(widget);
        if (WebooUtil.isDebug()) {
            CallerInfo callerInfo = WebooUtil.getCallerInfo();
            callerInfo.setWidgetId(widget.getWidgetId());
            widget.set_callerInfo(callerInfo);
            log.debug("Caller info: {}", callerInfo);
        }
        return widget;
    }

    public void dump(Object u) {
        String json = WebooUtil.toJson(u);
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        Layout layout = new Layout();
        layout.setModel(model);
        layout.setRequest(request);
        layout.setResponse(response);

        render(layout);
        response.getWriter().write(layout.toHtml(this));
    }

    protected void render(Layout layout) {
        //return "No implemented toHtml() method in " + this.getClass().getName();
        //layout.setTitle("No implemented toHtml() method in " + this.getClass().getName());
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
