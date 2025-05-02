package hr.ja.weboo.demo;

import hr.ja.weboo.ui.CompositeWidget;
import hr.ja.weboo.ui.widgets.Div;

public class FooterComponent extends CompositeWidget {
    public FooterComponent() {
        add(new Div("footer", "container"));
    }
}
