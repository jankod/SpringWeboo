package hr.ja.weboo.demo;

import hr.ja.weboo.ui.CompositeWidget;
import hr.ja.weboo.utils.WebooUtil;
import lombok.Data;

@Data
public class HeadComponent extends CompositeWidget {

    private String text = "HeadComponent";

    @Override
    public String toHtml() {

        // Language=FreeMarker
        String html = """
                <div class="${classes}" id="${widgetId}">
                    <p>${text}</p>
                </div>
                """;
        return WebooUtil.quteThis(html, this);
    }

    public static void main(String[] args) {
        HeadComponent headComponent = new HeadComponent();

        String html = headComponent.toHtml();
        System.out.println(html);
    }
}
