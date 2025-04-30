package hr.ja.weboo.ui.widgets;


import hr.ja.weboo.ui.CompositeWidget;
import hr.ja.weboo.utils.WebooUtil;

public class H3 extends CompositeWidget {

    private final String text;

    public H3(String text) {
        this.text = text;
    }

    @Override
    public String toHtml() {
        return "<h3>" + WebooUtil.escape(text) + "</h3>";
    }
}
