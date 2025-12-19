package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.PageContext;

public class JustText extends Widget {
    private final String text;

    public JustText(String text) {
        this.text = text;
    }

    @Override
    public String toHtml(PageContext context) {
        return text;
    }
}
