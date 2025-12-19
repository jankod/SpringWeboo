package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.PageContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static hr.ja.weboo.ui.widgets.Color.PRIMARY;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ButtonTest {


    @Test
    void test1() {
        Button button = new Button("Test");
        assertNotNull(button.widgetId());
        button.setColor(PRIMARY);
        assertEquals(PRIMARY, button.getColor());

        String html = button.toHtml(new PageContext());

        assertTrue(html.contains("btn btn-primary"));
        log.debug("html:\n {}", html);

        // result  <button type="button" class="btn btn-primary"  id="Button_1">
        //    Test
        //</button>
        assertTrue(html.contains("Test"));
        assertTrue(html.contains("id=\"Button_1\""));
        assertTrue(html.contains("class=\"btn btn-primary\""));
        assertTrue(html.contains("type=\"button\""));

    }
}
