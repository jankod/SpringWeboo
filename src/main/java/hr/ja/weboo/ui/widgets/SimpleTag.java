package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.CompositeWidget;
import hr.ja.weboo.ui.PageContext;
import hr.ja.weboo.utils.WebooUtil;
import lombok.Getter;
import lombok.Setter;
import org.intellij.lang.annotations.Language;

import java.util.Map;

@Setter
@Getter
public class SimpleTag extends CompositeWidget implements HasClasses {

    private final String tag;
    private String text = "";

    public SimpleTag(String tag, String text) {
        this.tag = tag;
        this.text = text;
    }

    public SimpleTag(String tag, Widget... widgets) {
        for (Widget w : widgets) {
            add(w);
        }
        this.tag = tag;
    }



    protected String renderChildren(PageContext context) {
        StringBuilder sb = new StringBuilder();
        for (Widget child : getChildren()) {
            sb.append(child.toHtml(context)).append("\n");
        }
        return sb.toString();
    }


    private String prepareAttributes() {
        final StringBuilder att = new StringBuilder();
        getAttributes().forEach((name, o) -> {
            //MessageFormat.format("sadas {1}", "");
            String value = WebooUtil.escape(o.toString());
            att.append("""
                    %s="%s" """.formatted(name, value));
        });
        return att.toString();
    }

    @Override
    public String toHtml(PageContext context) {

        @Language("HTML")
        String html = """
                  <${tag} ${renderAttributes().raw} id="${widgetId}">
                        ${text}
                         {children.raw}
                   </${tag}>
                """;

        return WebooUtil.quteMap(html, Map.of(
                "this", this,
                "children", renderChildren(context)
        ));
    }

    public static void main(String[] args) {
        SimpleTag tag = new SimpleTag("div", "Hello");
        tag.setAttribute("class", "my-class");
        tag.setAttribute("data-attr", 123);
        tag.add(new H3("My H3"));
        tag.add(new H3("My H3 2"));
        System.out.println(tag.toHtml(new PageContext()));
    }
}
