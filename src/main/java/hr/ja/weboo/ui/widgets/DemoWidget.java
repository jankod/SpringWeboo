package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.CompositeWidget;
import hr.ja.weboo.ui.WidgetWithAtributes;
import hr.ja.weboo.ui.PageContext;
import hr.ja.weboo.utils.QuteUtil;
import lombok.Getter;
import org.apache.commons.lang3.time.StopWatch;

@Getter
public class DemoWidget extends CompositeWidget {

    private StringBuilder idClass = new StringBuilder("cxcsas");

    private Button button = new Button("Click me");

    public DemoWidget() {
    }

    @Override
    public String toHtml(PageContext context) {
        // Language=FreeMarker
        String html = """
                <div class="demo-widget" ${idClass}>
                    <i>${button}</i>
                    <h3>Demo Widget</h3>
                    <p>This is a demo widget.</p>
                    <p>It demonstrates how to create a simple widget using the Weboo framework.</p>
                </div>
                """;
        return QuteUtil.quteThis(html, this);
    }

    public static void main(String[] args) {
        String html = run();
        run();
        run();
        runButton();

        runNewWidget();
    }

    private static void runNewWidget() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        PageContext context = new PageContext();
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.addLink("Home", "/");
        breadcrumb.addLink("About", "/about");
        breadcrumb.toHtml(context);

        stopWatch.stop();
        System.out.println("Time brud: " + stopWatch.getTime() + " ms");
    }

    private static void runButton() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        PageContext context = new PageContext();
        Button button = new Button("Click me");
        String html = button.toHtml(context);
        stopWatch.stop();
        System.out.println("Time button: " + stopWatch.getTime() + " ms");

    }

    private static String run() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        PageContext context = new PageContext();
        WidgetWithAtributes demoWidget = new DemoWidget();
        String html = demoWidget.toHtml(context);
        stopWatch.stop();
        System.out.println("Time taken: " + stopWatch.getTime() + " ms");
    //    System.out.println(html);
        return html;
    }
}
