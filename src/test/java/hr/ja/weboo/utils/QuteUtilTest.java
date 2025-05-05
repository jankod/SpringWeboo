package hr.ja.weboo.utils;

import hr.ja.weboo.ui.CompositeWidget;
import hr.ja.weboo.ui.widgets.Widget;
import lombok.Data;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuteUtilTest {


    @Test
    void testBasicTemplate() {
        String html = new TestBasicTemplateWidget().toHtml();
    }

    @Test
    void testChildrenWidget() {
        MyCompositeWidget widget = new MyCompositeWidget();
        widget.add(new DemoWidget2());
        widget.add(new DemoWidget2());
        String html = widget.toHtml();
        System.out.println(html);
    }
}

class MyCompositeWidget extends CompositeWidget {


    @Override
    public String toHtml() {
        String template = """
                <div>MyCompositeWidget</div>
                ${children}
                """;
        return QuteUtil.quteThis(template, this);
    }
}

@Data
class TestBasicTemplateWidget extends Widget {

    String param1 = "value1";
    String param2 = "value2";

    DemoWidget2 widget2 = new DemoWidget2();

    public String generateHtml() {
        return "<div>generatehtml</div>";
    }

    public DemoWidget2 getWidgetfromMe() {
        return widget2;
    }

    @Override
    public String toHtml() {

        @Language("InjectedFreeMarker") String template = """
                <div>
                ${param1}
                {param2}
                ${generateHtml().raw}
                {widget2}
                {getWidgetfromMe()}
                </div>
                """;
        String html = QuteUtil.quteThis(template, this);
        assertEquals(html.trim(), """
                <div>
                value1
                value2
                <div>generatehtml</div>
                <div>DemoWidget2</div>
                <div>DemoWidget2</div>
                </div>
                """.trim());
        return html;
    }
}

class DemoWidget2 extends Widget {

    @Override
    public String toHtml() {
        return "<div>DemoWidget2</div>";
    }
}