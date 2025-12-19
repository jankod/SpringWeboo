package hr.ja.weboo.ui.widgets;


import hr.ja.weboo.ui.PageContext;

public class Row extends SimpleTag {

    public Row(Widget... widgets) {
        super("div", widgets);
        addClass("row");
    }

    public Row col(Widget... widgets) {
        add(new Col(widgets));
        return this;
    }


    public static void main(String[] args) {
        Row row = new Row();
        row.col(new Div("col 1"));
        row.col(new Div("col 2"));
        row.setAttribute("title", "Ovo je title");
        System.out.println(row.toHtml(new PageContext()));
    }

}
