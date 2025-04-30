package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.utils.CallerInfo;
import hr.ja.weboo.utils.WebooUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class Widget {

    private String widgetId = null;

    @Getter
    @Setter
    private CallerInfo debugCallerInfo;

    public String widgetId() {
        if (widgetId == null) {
            widgetId = WebooUtil.wigetNewId(this.getClass());
        }
        return widgetId;
    }

    public String getWidgetId() {
        return widgetId();
    }

    public abstract String toHtml();


}
