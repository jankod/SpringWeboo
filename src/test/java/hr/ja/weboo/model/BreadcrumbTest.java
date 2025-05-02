package hr.ja.weboo.model;

import hr.ja.weboo.ui.widgets.Breadcrumb;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BreadcrumbTest {

    @Test
    void toHtml() {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.addLink("Home", "/");
        breadcrumb.addLink("About", "/about");
        breadcrumb.addLink("Contact", "/contact");

        String expectedHtml = """
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/">Home</a></li>
                        <li class="breadcrumb-item"><a href="/about">About</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Contact</li>
                    </ol>
                </nav>
                """;
        String actualHtml = breadcrumb.toHtml();
        log.debug("actualHtml: {}", actualHtml);
        log.debug("Expected HTML: {}", breadcrumb.getNavLinks());


    }
}