package hr.ja.weboo.ui.widgets;


import hr.ja.weboo.ui.CompositeWidget;
import hr.ja.weboo.utils.WebooUtil;
import lombok.Getter;

@Getter
public class H3 extends CompositeWidget implements HasClasses {

    private final String text;

    public H3(String text) {
        this.text = text;
    }

    @Override
    public String toHtml() {
        return WebooUtil.quteThis(
                """
                        <h3 id="${widgetId}" >
                            {this.text}
                            ${children.raw}
                        </h3>
                        """, this);

    }

    public static void main(String[] args) {
        H3 h3 = new H3("My H3");
        h3.add(new Div("Hello"));
        System.out.println(h3.toHtml());
    }
}
