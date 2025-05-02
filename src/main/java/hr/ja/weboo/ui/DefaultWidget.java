package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Widget;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultWidget extends Widget implements HasAttributes {

    private final Map<String, Object> attributes = new HashMap<>();

    @Override
    public String toHtml() {
        return "NOT IMPLEMENTED " + getClass().getName();
    }
}
