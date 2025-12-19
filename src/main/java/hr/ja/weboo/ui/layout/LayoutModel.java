package hr.ja.weboo.ui.layout;

import hr.ja.weboo.ui.WebPageContext;
import hr.ja.weboo.ui.widgets.Widget;

public record LayoutModel(
      String title,
      String bodyHtml,
      Widget headSlot,
      Widget bodyTopSlot,
      Widget bodyFooterSlot,
      String scriptCode,
      WebPageContext context
  ) {}
