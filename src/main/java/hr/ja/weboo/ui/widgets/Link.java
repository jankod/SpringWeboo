package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.utils.QuteUtil;
import hr.ja.weboo.utils.WebooUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.intellij.lang.annotations.Language;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Getter
@Setter
public class Link extends Widget implements HasClasses {

    private String label;
    private String url;
    private Map<String, Object> attributes = new LinkedHashMap<>();

    public Link(String label, String url, Object... queryParams) {
        this.label = requireNonNull(label, "Label cannot be null");
        this.url = buildUrlWithQuery(requireNonNull(url), queryParams);
    }

    @Override
    public String toHtml() {
        @Language("InjectedFreeMarker")
        String template = """
                <a href="${url}" class='btn btn-link ${classes}' id='${widgetId}'>${label}</a>
                """;

        return QuteUtil.quteThis(template, this);
    }

    /**
     * Gradi potpuni URL dodavanjem URL-kodiranih parametara upita osnovnom URL-u.
     *
     * @param baseUrl     Osnovni URL.
     * @param queryParams Niz objekata koji predstavljaju parove ključ-vrijednost.
     * @return Potpuni URL s dodanim parametrima upita ili osnovni URL ako nema parametara.
     * @throws IllegalArgumentException ako queryParams ima neparan broj elemenata.
     */
    private String buildUrlWithQuery(String baseUrl, Object... queryParams) {
        // Nema parametara, vrati osnovni URL
        if (queryParams == null || queryParams.length == 0) {
            return baseUrl;
        }

        // Parametri moraju biti u parovima ključ-vrijednost
        if (queryParams.length % 2 != 0) {
            throw new IllegalArgumentException("Query parameters must be provided in key-value pairs.");
        }

        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("?");

        for (int i = 0; i < queryParams.length; i += 2) {
            // Preskoči null ključeve ili vrijednosti (ili ih obradi drugačije po potrebi)
            if (queryParams[i] == null || queryParams[i + 1] == null) {
                // Možete odlučiti preskočiti ovaj par, baciti iznimku ili dodati prazan string
                continue;
            }

            String key = String.valueOf(queryParams[i]);
            String value = String.valueOf(queryParams[i + 1]);

            // URL kodiraj ključ i vrijednost
            String encodedKey = URLEncoder.encode(key, StandardCharsets.UTF_8);
            String encodedValue = URLEncoder.encode(value, StandardCharsets.UTF_8);

            // Dodaj par ključ=vrijednost
            urlBuilder.append(encodedKey).append("=").append(encodedValue);

            // Dodaj '&' ako ovo nije zadnji par
            if (i < queryParams.length - 2) {
                // Provjeri postoji li sljedeći valjani par prije dodavanja '&'
                if (i + 2 < queryParams.length && queryParams[i + 2] != null && queryParams[i + 3] != null) {
                    urlBuilder.append("&");
                }
            }
        }
        // Ukloni zadnji znak ako je '?' (slučaj gdje su svi parametri bili null)
        if (urlBuilder.charAt(urlBuilder.length() - 1) == '?') {
            urlBuilder.setLength(urlBuilder.length() - 1);
        }

        return urlBuilder.toString();
    }

}
