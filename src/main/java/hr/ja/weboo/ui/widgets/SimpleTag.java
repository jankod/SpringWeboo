package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.CompositeWidget;
import hr.ja.weboo.utils.WebooUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Setter
@Getter
public class SimpleTag extends CompositeWidget  {

    private final String tag;
    private String text = "";

    private final Map<String, String> attributes = new HashMap<>();

    public SimpleTag(String tag, String text) {
        this.tag = tag;
        this.text = text;
    }

    public SimpleTag(String tag, Widget... widgets) {
      addAll(widgets);
        this.tag = tag;
    }



    public SimpleTag attr(String name, Object value) {
        attributes.put(name, Objects.toString(value, ""));
        return this;
    }

    @Override
    public String toHtml() {

        String template = """
                  <{tag} {attr.raw} {attributes.raw} >
                        {text}
                        {children.raw}
                      </{tag}>
                """;

        return WebooUtil.quteMap(template, Map.of(
                "tag", tag,
                "text", text,
                "children", super.toHtml(),
                "attributes", prepareAttributes()

        ));
    }



    private String prepareAttributes() {
        final StringBuilder att = new StringBuilder();
        attributes.forEach((name, o) -> {
            //MessageFormat.format("sadas {1}", "");
            String value = WebooUtil.escape(o.toString());
            att.append("""
                    %s="%s" """.formatted(name, value));
        });
        return att.toString();
    }
}
