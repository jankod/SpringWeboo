package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Widget;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CompositeWidget extends DefaultWidget {


    @Override
    public String toHtml() {
        StringBuilder html = new StringBuilder();
        for (Widget widget : getChildren()) {
            html.append(widget.toHtml()).append("\n");
        }
        return html.toString();
    }
}
