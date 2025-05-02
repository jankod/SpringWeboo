package hr.ja.weboo.utils;

import hr.ja.weboo.ui.widgets.Button;
import hr.ja.weboo.ui.widgets.HtmlWidget;
import hr.ja.weboo.ui.widgets.JustText;
import hr.ja.weboo.ui.widgets.Widget;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class WidgetsLinkedListTest {


    @Test
    void tesr1() {
        WidgetsLinkedList list = new WidgetsLinkedList();
        JustText w1 = new JustText("Hello");
        list.add(w1);
        HtmlWidget w2 = new HtmlWidget("<b>World</b>");
        list.add(w2);
        JustText w3 = new JustText("!");
        list.add(w3);
        Button w4 = new Button("dsaads");
        list.add(w4);


        log.debug("\n" + list);

        assertEquals(4, list.size());
        assertEquals(w1.getWidgetId(), list.get(0).getWidgetId());
        assertEquals(w4.getWidgetId(), list.get(3).getWidgetId());

    }

}