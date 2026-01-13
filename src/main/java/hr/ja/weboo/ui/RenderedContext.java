package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Widget;

import java.util.Locale;

public interface RenderedContext {
    Locale locale();

    String t(String key, Object... args);

    void qute(String template, Widget dataModel);
}
