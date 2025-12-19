package hr.ja.weboo.ui.layout;

import hr.ja.weboo.ui.PageContext;
import hr.ja.weboo.ui.widgets.Widget;

public record LayoutModel(
      String title,
      String bodyHtml,
      Widget headSlot,
      Widget bodyTopSlot,
      Widget bodyFooterSlot,
      String scriptCode,
      PageContext context
  ) {}
