package hr.ja.weboo.ui.widgets;


import hr.ja.weboo.utils.WebooUtil;

public class H3 extends Widget {

    private final String text;

    public H3(String text) {
        this.text = text;
    }

    @Override
    public String toHtml() {
        return "<h3>" + WebooUtil.escape(text) + "</h3>";
    }
}
