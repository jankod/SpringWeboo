package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Widget;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class WidgetWithAtributes extends Widget implements HasAttributes {

    private final Map<String, Object> attributes = new HashMap<>();

    @Override
    public String toHtml(PageContext context) {
        return "NOT IMPLEMENTED " + getClass().getName();
    }
}
