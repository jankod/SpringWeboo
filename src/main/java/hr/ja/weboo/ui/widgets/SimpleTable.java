package hr.ja.weboo.ui.widgets;


import hr.ja.weboo.ui.PageContext;
import hr.ja.weboo.utils.WebooUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SimpleTable<M> extends Widget {

    private Collection<M> data;

    private List<Column<M>> columns = new ArrayList<>();

    public SimpleTable(Collection<M> data) {
        this.data = data;
    }

    public Column<M> column(String name, ColumnRender<M, Object> columnValue) {
        Column<M> column = new Column<>(name, columnValue);
        columns.add(column);
        return column;
    }

    @Override
    public String toHtml(PageContext context) {


        String template = """
                <table {attr.raw} class="table">
                   <thead>
                       <tr>
                        {#for c in columns}
                          <th>{c.name}</th>
                        {/for}
                       </tr>
                       <tbody>
                          {#for d in data}
                           <tr>
                           {#for c in columns}
                             <td>{c.columnValue.render(d)}</td>
                             {/for}
                           </tr>
                          {/for}
                     </thead>
                </table>
                """;

        return WebooUtil.quteMap(template, Map.of(
                "columns", columns,
                "data", data
        ));
    }
}
