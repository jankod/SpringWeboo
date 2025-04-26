package hr.ja.weboo.ui.widgets;


public class Col extends SimpleTag {

    public Col(String text) {
        this(new JustText(text));
    }

    public Col(Widget... widgets) {
        super("div", widgets);
        addClass("col");
    }

}
