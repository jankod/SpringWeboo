package hr.ja.weboo.ui.widgets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WidgetTest {

    @Test
    void test1() {
        Widget widget = new Widget() {
            @Override
            public String toHtml() {
                return "<div>Test</div>";
            }
        };
        assertNotNull(widget.widgetId());
    }

}