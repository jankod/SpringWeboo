package hr.ja.weboo.ui.widgets;

import j2html.tags.ContainerTag;
import j2html.tags.DomContent;
import j2html.tags.specialized.DivTag;

public class J4HtmlWidget extends Widget {
    private final ContainerTag<?> content;

    public J4HtmlWidget(ContainerTag<?> content) {
        this.content = content;
        content.withId(widgetId());
    }

    @Override
    public String toHtml() {
        return content.render();
    }


    static void main(String[] args) {
        DivTag div = new DivTag();
        div.withClass("my-class");
        div.attr("data-attribute", "value");

        J4HtmlWidget widget = new J4HtmlWidget(div);
        System.out.println(widget.toHtml());
    }
}
