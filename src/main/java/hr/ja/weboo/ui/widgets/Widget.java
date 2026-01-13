package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.RenderedContext;
import hr.ja.weboo.utils.CallerInfo;
import hr.ja.weboo.utils.WebooUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j

public abstract class Widget {

    @Getter
    @Setter
    private String widgetId = null;

    @Getter
    @Setter
    private CallerInfo debugCallerInfo;


    public abstract void render(RenderedContext context);

    public void qute(RenderedContext context) {

    }

}

