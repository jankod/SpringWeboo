package hr.ja.weboo.ui.widgets;


import hr.ja.weboo.utils.WebooUtil;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Breadcrumb extends Widget {

    private Map<String, String> navLinks = new LinkedHashMap<>();

    public void addLink(String name, String url) {
        navLinks.put(name, url);
    }

    @Override
    public String toHtml() {

        String template = """
                <nav aria-label="breadcrumb" id="{id}" class="{class}">
                  <ol class="breadcrumb">
                    {#each navLinks.entrySet() navLink}
                      {#if navLink_count == navLinks.size()}
                        <li class="breadcrumb-item active" aria-current="page">{navLink.key}</li>
                      {#else}
                        <li class="breadcrumb-item"><a href="{navLink.value}">{navLink.key}</a></li>
                      {/if}
                    {/each}
                  </ol>
                </nav>
                """;

        return WebooUtil.quteMap(template,
                Map.of("navLinks", navLinks,
                        "id", getWidgetId(),
                        "class", getClasses()));
    }
}