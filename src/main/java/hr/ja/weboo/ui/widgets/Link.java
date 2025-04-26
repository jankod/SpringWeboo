package hr.ja.weboo.ui.widgets;

import hr.ja.weboo.utils.WebooUtil;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Map;

import static java.util.Objects.requireNonNull;

@Data
public class Link extends Widget {

    private String label;
    private String url;

    public Link(String label, String url, Object... queryParams) {
        this.label = requireNonNull(label);
        this.url = url;
        if (!ArrayUtils.isEmpty(queryParams)) {
            url += "?";
            StringBuilder urlBuilder = new StringBuilder(url);
            for (int i = 0; i < queryParams.length; i++) {
                if (i % 2 == 0) {
                    urlBuilder.append(queryParams[i]);
                } else {
                    urlBuilder.append("=").append(queryParams[i]).append("&");
                }
            }
            this.url = urlBuilder.toString();
        }
    }

    @Override
    public String toHtml() {
        String template = """
                <a href="${url}" class='btn btn-link'>${label}</a>
                """;

        return WebooUtil.quteThis(template, Map.of(
                "url", url, "label", label
        ));
    }
}
