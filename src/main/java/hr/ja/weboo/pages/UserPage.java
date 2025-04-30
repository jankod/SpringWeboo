package hr.ja.weboo.pages;

import hr.ja.weboo.ui.BasePageLayout;
import hr.ja.weboo.ui.HeadComponent;
import hr.ja.weboo.ui.Layout;
import hr.ja.weboo.ui.WebPageContext;
import hr.ja.weboo.ui.widgets.Button;
import hr.ja.weboo.ui.widgets.Link;

public class UserPage extends BasePageLayout {

    public static final String URL = "/user";

    @Override
    protected HeadComponent createHead() {
        HeadComponent head = super.createHead();
        head.setText("User Page head");
        return head;
    }

    @Override
    protected void renderContent(WebPageContext web) {
        title = "User Page";
        web.add(new Button("Click Me"));
        web.add(new Link("Home Page", HomePage.URL));
    }
}
