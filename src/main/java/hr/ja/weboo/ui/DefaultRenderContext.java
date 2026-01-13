package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Widget;

import java.util.Locale;

public class DefaultRenderContext implements RenderedContext {

    private final Locale locale;
    private int counter = 0;

    public DefaultRenderContext(Locale locale) {
        this.locale = locale;
    }

    @Override
    public Locale locale() {
        return locale;
    }

    public String nextWidgetId(Widget widget) {
        return widget.getClass().getSimpleName() + "-" + (++counter);
    }

    @Override
    public String t(String key, Object... args) {
        return "";
    }

    @Override
    public void qute(String templateName, Object dataModel) {

    }

    public void renderWidget(Widget widget) {
        widget.setWidgetId(nextWidgetId(widget));
        widget.render(this);
    }
}
