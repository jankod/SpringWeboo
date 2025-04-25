package hr.ja.weboo.widgets;

import hr.ja.weboo.pages.Widget;
import hr.ja.weboo.utils.WebooUtil;

public class Button extends Widget {
    private final String label;

    public Button(String label) {
        this.label = label;
    }

    @Override
    public String toHtml() {
        String template = """
                <button type="button" class="btn btn-primary">
                    ${label}
                </button>
                """;
        return WebooUtil.qute(template, "label", label);
    }
}
