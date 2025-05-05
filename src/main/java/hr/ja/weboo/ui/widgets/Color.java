package hr.ja.weboo.ui.widgets;

public enum Color {
    PRIMARY,
    SECONDARY,
    SUCCESS,
    DANGER,
    WARNING,
    INFO,
    LIGHT,
    DARK;

    public String toCssClass() {
        return this.name().toLowerCase();
    }

    public String toName() {
        return this.name().toLowerCase();
    }

    @Override
    public String toString() {
        return toName();
    }
}
