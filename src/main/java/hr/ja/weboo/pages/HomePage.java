package hr.ja.weboo.pages;

import hr.ja.weboo.ui.*;
import hr.ja.weboo.ui.widgets.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import static j2html.TagCreator.h1;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class HomePage extends Page {

    public static final String URL = "/";

    MyLayout myLayout = new MyLayout();

    @Override
    protected void render(PageContext context) {
        setTitle("Home Page");
        setUpLayout();


        add(new H3("Home Page"));
        add(new Link("User Page", UserPage.URL));

        add( AlertWidget.warning("Ovo je alter widget"));
    }

    private void setUpLayout() {
        setLayout(myLayout);
    }
}
