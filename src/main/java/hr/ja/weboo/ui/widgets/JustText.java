package hr.ja.weboo.ui.widgets;


public class JustText extends Widget {
    private final String text;

    public JustText(String text) {
        this.text = text;
    }

    @Override
    public String toHtml() {
        return text;
    }
}
