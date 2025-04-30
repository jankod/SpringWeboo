package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Breadcrumb;
import lombok.Data;

@Data
public abstract class BasePageLayout extends AbstractPage {


    @Override
    protected void render(WebPageContext web) {
        HeadComponent head = createHead();
        web.add(head);
        renderContent(web);
        FooterComponent footer = createFooter();
        web.add(footer);

    }

    protected HeadComponent createHead() {
        HeadComponent head = new HeadComponent();
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.addLink("Home", "/");
        breadcrumb.addLink("About", "/about");
        breadcrumb.addLink("Contact", "/contact");
        head.add(breadcrumb);
        return head;
    }

    protected abstract void renderContent(WebPageContext web);


    protected FooterComponent createFooter() {
        return new FooterComponent();
    }


}
