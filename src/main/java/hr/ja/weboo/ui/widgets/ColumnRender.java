package hr.ja.weboo.ui.widgets;

@FunctionalInterface
public interface ColumnRender<M, R> {
    R render(M model);
}
