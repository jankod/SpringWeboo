package hr.ja.weboo.ui.widgets;


public class Row extends SimpleTag {

    public Row(Widget... widgets) {
        super("div", widgets);
        addClass("row");
    }

    public Row col(Widget... widgets) {
        add(new Col(widgets));
        return this;
    }

    public Row col2(Widget... widgets) {
        add(new Col(widgets).addClass("col-2"));
        return this;
    }

    public static void main(String[] args) {
        Row row = new Row();
        row.col2().col2();
        row.attr("title", "Ovo je title");
        System.out.println(row.toHtml());
    }

}
