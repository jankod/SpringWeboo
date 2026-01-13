package hr.ja.weboo.utils;

import hr.ja.weboo.ui.RenderedContext;
import hr.ja.weboo.ui.WidgetRenderer;
import hr.ja.weboo.ui.widgets.Widget;
import io.quarkus.qute.Expression;
import io.quarkus.qute.ResultMapper;
import io.quarkus.qute.TemplateNode;

import java.util.Locale;

public abstract class QuteWidgetResultMapper implements ResultMapper {


    @Override
    public boolean appliesTo(TemplateNode.Origin origin, Object result) {
        return result instanceof Widget;
    }

    public abstract String map(Object result, Expression expression);
}
