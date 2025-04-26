package hr.ja.weboo.pages;

import hr.ja.weboo.ui.AbstractPage;
import hr.ja.weboo.ui.BasePage;
import hr.ja.weboo.ui.Layout;
import hr.ja.weboo.ui.widgets.Breadcrumb;
import hr.ja.weboo.ui.widgets.Button;
import hr.ja.weboo.ui.widgets.Color;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)

public class HomePage extends BasePage {


    @Override
    protected void render(Layout layout) {
        log.debug("debug");
        layout.setTitle("home page");

        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.addLink("Home", "/");
        breadcrumb.addLink("About", "/about");
        breadcrumb.addLink("Contact", "/contact");
        add(breadcrumb);

        add(new Button("Click Me").color(Color.INFO));
    }

}
