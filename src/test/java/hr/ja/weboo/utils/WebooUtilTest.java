package hr.ja.weboo.utils;

import hr.ja.weboo.ui.PageContext;
import hr.ja.weboo.ui.widgets.Widget;
import io.quarkus.qute.TemplateException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class WebooUtilTest {


    @Test
    void testQuteKeyValue() {
        String result = WebooUtil.quteKeyValue("Hello {name}", "name", "World");
        assertEquals("Hello World", result);

        String result2 = WebooUtil.quteKeyValue("Hello {name}", "name", "World", "name2", "World2");
        assertEquals("Hello World", result2);

        String result3 = WebooUtil.quteKeyValue("Hello {name} {name2}", "name", "World", "name2", "World2");
        assertEquals("Hello World World2", result3);
    }

    @Test
    void testQuteKeyValueThis() {
        String result = WebooUtil.quteMap("Hello {name}", Map.of("name", "World"));
        assertEquals("Hello World", result);
        String result2 = WebooUtil.quteMap("Hello {name}", Map.of("name", "World", "name2", "World2"));
        assertEquals("Hello World", result2);
        String result3 = WebooUtil.quteMap("Hello {name} {name2}", Map.of("name", "World", "name2", "World2"));
        assertEquals("Hello World World2", result3);
    }


//    @Test
//    void testWidgets() {
//        SampleWidget w = new SampleWidget("Hello", "World");
//        String result = WebooUtil.quteWidget("widget {1}", w);
//    }

    public static class SampleWidget extends Widget {
        private String name;
        private String name2;

        public SampleWidget(String name, String name2) {
            this.name = name;
            this.name2 = name2;
        }

        @Override
        public String toHtml(PageContext context) {
            return "<div>" + name + " " + name2 + "</div>";
        }
    }

    @Test
    void testQuteThis() {
        SampleObject sampleObject = new SampleObject("World", "World2");
        String result = QuteUtil.quteThis("Hello {this.name} {this.name2}", sampleObject);
        assertEquals("Hello World World2", result);


        // test not existing field
        try {
            String result2 = QuteUtil.quteThis("Hello {this.name} {this.name3}", sampleObject);
            fail("Should throw exception, got: " + result2);
        } catch (Exception e) {
            log.debug("Expected exception: " + e.getMessage());

            assertInstanceOf(TemplateException.class, e);

            TemplateException te = (TemplateException) e;
            log.debug("TemplateException: " + te.getMessage());
            log.debug("Message template:  " + te.getMessageTemplate());
            log.debug("Arguments: " + te.getArguments());
        }
    }

    @Data
    public static class SampleObject {
        String name;
        String name2;

        public SampleObject(String name, String name2) {
            this.name = name;
            this.name2 = name2;
        }
    }
}
