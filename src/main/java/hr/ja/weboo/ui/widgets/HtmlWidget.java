package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.PageContext;

public class HtmlWidget extends Widget {
    private final String html;

    public HtmlWidget(String html) {

        this.html = html;
    }

    @Override
    public String toHtml(PageContext context) {
        return html;
    }
}
