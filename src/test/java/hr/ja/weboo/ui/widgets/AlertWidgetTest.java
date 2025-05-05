package hr.ja.weboo.ui.widgets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlertWidgetTest {

    @Test
    void testAlertWidget() {
        AlertWidget alert = new AlertWidget("Test message", Color.SUCCESS);
        String html = alert.toHtml();
        System.out.println(html);
        assertTrue(html.contains("alert alert-dismissible fade show alert-success"));
        assertTrue(html.contains("Test message"));
    }

}