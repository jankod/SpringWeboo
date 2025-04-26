package hr.ja.weboo.ui.widgets;

import lombok.Getter;

@Getter
public class Column<M> {
    private final String name;
    private final ColumnRender<M, Object> columnValue;

    public Column(String name, ColumnRender<M, Object> columnValue) {
        this.name = name;
        this.columnValue = columnValue;

    }
}


