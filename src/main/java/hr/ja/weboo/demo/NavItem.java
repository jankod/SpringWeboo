package hr.ja.weboo.demo;

import hr.ja.weboo.ui.widgets.Icon;
import hr.ja.weboo.ui.widgets.Icons;
import hr.ja.weboo.ui.widgets.Widget;
import hr.ja.weboo.utils.QuteUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.intellij.lang.annotations.Language;

@Data
@EqualsAndHashCode(callSuper = true)
public class NavItem extends Widget {

    private final String linkName;
    private final String linkUrl;
    private Icon icon = Icons.checkbox();

    public NavItem(String linkName, String linkUrl, Icon icon) {
        this.linkName = linkName;
        this.linkUrl = linkUrl;
        this.icon = icon;
    }

    @Override
    public String toHtml() {

        @Language("InjectedFreeMarker")
        String template = """
                <li class="nav-item" id='{widgetId}'>
                        <a class="nav-link" href="{linkUrl}">
                          <span class="nav-link-icon"><!-- Download SVG icon from http://tabler.io/icons/icon/checkbox -->
                            ${icon}
                          </span>
                          <span class="nav-link-title"> {linkName} </span>
                        </a>
                      </li>
                """;
        return QuteUtil.quteThis(template, this);
    }

    public static void main(String[] args) {
        NavItem item = new NavItem("link1", "some-link.com",Icons.checkbox());
        System.out.println(item.toHtml());
    }
}
