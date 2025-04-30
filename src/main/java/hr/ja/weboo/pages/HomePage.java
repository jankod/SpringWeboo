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
public class HomePage extends Page {

    public static final String URL = "/";

    @Override
    protected void render(WebPageContext webPageContext) {
        setTitle("Home Page");
        add(new H3("Home Page"));
        add(new Link("User Page", UserPage.URL));
    }
}
