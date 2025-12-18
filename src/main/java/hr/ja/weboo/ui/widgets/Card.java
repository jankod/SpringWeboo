package hr.ja.weboo.ui.widgets;


import hr.ja.weboo.utils.WebooUtil;
import org.intellij.lang.annotations.Language;

public class Card extends Widget {
    private final String title;

    public Card(String title) {
        this.title = title;
    }

    @Override
    public String toHtml() {
        String te = """
              <div  class="card {classes}" id='{id}'>
                   <script\s
                  <div class='card-header'>
                      <h5 class="card-title">{title}</h5>
                  </div>
                  <div class="card-body">
                     {children.raw}
                  </div>
                </div>
              """;
        return WebooUtil.quteKeyValue(te,
//                "classes", getClasses(),
              "id", widgetId(),
              "title", title
              //              "children", toChildrenHtml()
        );
    }

    public static void main(String[] args) {
        Card card = new Card("My Card");
        System.out.println(card.toHtml());
    }
}
