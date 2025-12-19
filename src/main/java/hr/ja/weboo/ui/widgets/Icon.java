package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.ui.PageContext;

public class Icon extends Widget {

    private final String paths;
    private int size = 24;
    private String cssClass = "icon";
    private boolean ariaHidden = true;

    public Icon(String paths) {
        this.paths = paths;
    }

    public Icon size(int px) {
        this.size = px;
        return this;
    }

    public Icon css(String css) {
        this.cssClass += " " + css;
        return this;
    }

    @Override
    public String toHtml(PageContext context) {
        return """
                  <svg xmlns="http://www.w3.org/2000/svg"
                       width="%d" height="%d"
                       viewBox="0 0 24 24"
                       fill="none"
                       stroke="currentColor"
                       stroke-width="2"
                       stroke-linecap="round"
                       stroke-linejoin="round"
                       class="%s"
                       %s>
                      %s
                  </svg>
              """.formatted(
              size, size,
              cssClass,
              ariaHidden ? "aria-hidden=\"true\"" : "",
              paths
        );
    }


}
