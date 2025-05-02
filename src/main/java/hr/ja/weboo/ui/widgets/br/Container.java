package hr.ja.weboo.ui.widgets.br;

import hr.ja.weboo.ui.CompositeWidget;
import hr.ja.weboo.ui.widgets.Widget;
import hr.ja.weboo.utils.WebooUtil;

import java.util.Map;

public class Container extends CompositeWidget {

    @Override
    public String toHtml() {
        String html = """
                <div class="container-fluid" id='{widgetId}'>
                {children}
                </div>
                """;
        return WebooUtil.quteMap(html, Map.of(
        ));

    }
}
