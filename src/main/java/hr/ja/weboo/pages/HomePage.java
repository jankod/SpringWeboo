package hr.ja.weboo.pages;

import hr.ja.weboo.ui.*;
import hr.ja.weboo.ui.widgets.Div;
import hr.ja.weboo.ui.widgets.H3;
import hr.ja.weboo.ui.widgets.Link;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import static j2html.TagCreator.h1;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class HomePage extends BasePageLayout {

    public static final String URL = "/";

    @Override
    protected HeadComponent createHead() {
        HeadComponent h = super.createHead();
        return h;
    }

    @Override
    protected void renderContent(WebPageContext web) {
        title = "Home Page";
        addTop(web, "Ovo je header iz home page");

        addBody(web, "Ovo je body iz home page");

        web.add(new Link("User Page", UserPage.URL));

    }

    private void addBody(WebPageContext web, String text) {
        web.add(new Div(text));
    }

    private void addTop(WebPageContext web, String text) {
        H3 header = new H3(text);
        header.addClass("header");
        web.add(header);
    }

}
