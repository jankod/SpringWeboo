package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Div;

public class FooterComponent extends CompositeWidget {
    public FooterComponent() {
        add(new Div("footer", "container"));
    }
}
