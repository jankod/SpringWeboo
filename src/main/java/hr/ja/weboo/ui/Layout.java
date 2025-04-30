package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Widget;
import hr.ja.weboo.utils.WebooUtil;
import lombok.Data;

import java.util.Map;
import java.util.Objects;

public interface Layout {

    String toHtml(WebPageContext context, Page page);


}
