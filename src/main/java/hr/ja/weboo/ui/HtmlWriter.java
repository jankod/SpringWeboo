package hr.ja.weboo.ui;

import hr.ja.weboo.utils.WebooUtil;

public class HtmlWriter {

    private final StringBuilder sb;

    public HtmlWriter(int initialCapacity) {
        this.sb = new StringBuilder(initialCapacity);
    }

    public HtmlWriter() {
        this(256);
    }

    public HtmlWriter text(String text) {
        sb.append(escape(text));
        return this;
    }

    private String escape(String text) {
        return WebooUtil.htmlEscape(text);
    }

    public HtmlWriter html(String html) {
        sb.append(html);
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

