package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Widget;

public interface WidgetRenderer {

    void render(Widget widget, HtmlWriter out);

    static String toString(Widget w, WidgetRenderer r) {
        HtmlWriter out = new HtmlWriter(128);
        r.render(w, out);
        return out.toString();
    }
}
