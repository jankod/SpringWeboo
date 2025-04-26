package hr.ja.weboo.pages;

import hr.ja.weboo.ui.BasePage;
import hr.ja.weboo.ui.Layout;
import hr.ja.weboo.ui.widgets.Button;
import hr.ja.weboo.ui.widgets.Link;

import java.nio.file.Paths;

public class UserPage extends BasePage {


    @Override
    protected void render(Layout layout) {
        layout.setTitle("User");
        add(new Button("Click Me"));



        Link debugLink = new Link("ddebug","dsads");
        add(debugLink);
    }


}
