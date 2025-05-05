package hr.ja.weboo.pages;

import hr.ja.weboo.demo.NavbarComponent;
import hr.ja.weboo.ui.layout.TablerLayout;

public class MyLayout extends TablerLayout {

    public MyLayout() {
        this.setBodyTopSlot(new NavbarComponent());
    }

}
