package hr.ja.weboo.pages;

import hr.ja.weboo.ui.*;
import hr.ja.weboo.ui.widgets.Button;
import hr.ja.weboo.ui.widgets.Link;

public class UserPage extends Page {

    public static final String URL = "/user";


    @Override
    protected void render(WebPageContext web) {
        setTitle("User Page");
        add(new Button("Click Me"));
        add(new Link("Home Page", HomePage.URL));
    }
}
