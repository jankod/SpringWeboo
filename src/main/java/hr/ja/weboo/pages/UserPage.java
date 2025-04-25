package hr.ja.weboo.pages;

import hr.ja.weboo.model.AbstractPage;
import hr.ja.weboo.model.Layout;
import hr.ja.weboo.widgets.Button;

public class UserPage extends AbstractPage {

    public UserPage() {
        setTitle("User Page");
    }

    @Override
    protected void render(Layout layout) {
        add(new Button("Click Me"));
    }
}
