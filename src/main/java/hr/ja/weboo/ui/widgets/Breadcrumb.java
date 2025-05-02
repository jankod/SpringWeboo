package hr.ja.weboo.ui.widgets;


import hr.ja.weboo.ui.DefaultWidget;
import hr.ja.weboo.utils.QuteUtil;
import hr.ja.weboo.utils.WebooUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.intellij.lang.annotations.Language;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Last link is active.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Breadcrumb extends DefaultWidget implements HasClasses {

    private Map<String, String> navLinks = new LinkedHashMap<>();

    public void addLink(String name, String url) {
        navLinks.put(name, url);
    }

    @Override
    public String toHtml() {

        // Ispravljen predložak za pristup svojstvima preko 'this'
        @Language("InjectedFreeMarker") String template = """
                <nav aria-label="breadcrumb" id="${widgetId}" class="${classes}">
                  <ol class="breadcrumb">
                    {#each navLink in navLinks}
                      {#if navLink_count == navLinks.size()}
                        <li class="breadcrumb-item active" aria-current="page">{navLink.key}</li>
                      {#else}
                        <li class="breadcrumb-item"><a href="{navLink.value}">{navLink.key}</a></li>
                      {/if}
                    {/each}
                  </ol>
                </nav>
                """;

        return QuteUtil.quteThis(template, this);
    }
}