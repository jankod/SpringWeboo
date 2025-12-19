package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.PageContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class LinkTest {

    @Test
    void testLink() {
        Link link = new Link("Example", "https://example.com");
        String html = link.toHtml(new PageContext());
        assertTrue(html.contains("""
                href="https://example.com""".trim()));
        assertTrue(html.contains("Example"));
      log.debug(html);

    }
}
