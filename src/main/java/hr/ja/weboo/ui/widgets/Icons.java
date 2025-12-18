package hr.ja.weboo.ui.widgets;

public final class Icons {

    public static Icon user() {
        return new Icon("""
              <path d="M8 7a4 4 0 1 0 8 0a4 4 0 0 0 -8 0" /><path d="M6 21v-2a4 4 0 0 1 4 -4h4a4 4 0 0 1 4 4v2" />
              """);
    }



    private Icons() {
    }

    public static Icon home() {

        return new Icon("""
                  <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                  <path d="M5 12l-2 0l9 -9l9 9l-2 0" />
                  <path d="M5 12v7a2 2 0 0 0 2 2h10a2 2 0 0 0 2 -2v-7" />
                  <path d="M9 21v-6a2 2 0 0 1 2 -2h2a2 2 0 0 1 2 2v6" />
              """);

    }

    public static Icon checkbox() {
        return new Icon("""
                  <path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M5 12l5 5l10 -10" />
              """);
    }
}
