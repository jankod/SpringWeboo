package hr.ja.weboo.model;


import hr.ja.weboo.pages.Widget;
import hr.ja.weboo.utils.WebooUtil;
import io.quarkus.qute.Qute;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Data
public class Breadcrumb extends Widget {

    // ocu mapu koja pamtri redosljed
    // navigacijskih linkova
    // i njihovih URL-ova
    // npr. /home -> Home
    // /home/novi -> Novi

    private Map<String, String> navLinks = new LinkedHashMap<>();

    public void addLink(String name, String url) {
        navLinks.put(name, url);
    }

    @Override
    public String toHtml() {

        String template = """
                <nav aria-label="breadcrumb">
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

        return WebooUtil.qute(template, Map.of("navLinks", navLinks));
    }
}