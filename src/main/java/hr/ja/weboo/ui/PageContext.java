package hr.ja.weboo.ui;

import hr.ja.weboo.ui.widgets.Widget;
import hr.ja.weboo.ui.widgets.WidgetIdGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Data
@Slf4j
public class PageContext {

    protected Map<String, ?> model;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    protected final Map<String, Object> attributes = new HashMap<>();

    public PageContext() {
    }



}
