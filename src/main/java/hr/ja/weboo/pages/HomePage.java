package hr.ja.weboo.pages;

import hr.ja.weboo.model.AbstractPage;
import hr.ja.weboo.model.Breadcrumb;
import hr.ja.weboo.model.Layout;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HomePage extends AbstractPage {


    public HomePage () {
        setTitle("Home Page");
    }

    @Override
    protected void render(Layout layout) {
        setTitle("sasasd");

        add(new Breadcrumb());
    }

}
