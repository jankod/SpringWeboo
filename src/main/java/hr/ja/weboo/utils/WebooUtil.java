package hr.ja.weboo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.ja.weboo.ui.widgets.Widget;
import io.quarkus.qute.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.util.HtmlUtils;

import java.net.URL;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.*;

@Slf4j
public class WebooUtil {


    public static final Mode mode = Mode.DEV;

    public static CallerInfo getCallerInfo(int depth) {
        // Class<?> source = org.slf4j.helpers.Util.getCallingClass();

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[depth];
        String className = caller.getClassName();
        String methodName = caller.getMethodName();
        int lineNumber = caller.getLineNumber();


//        String fileName = "";
//        try {
//            // idea://open?file=
//            String projectRoot = System.getProperty("user.dir");
//            Path sourceDir = Paths.get(projectRoot, "src", "main", "java");
//            String classPath = caller.getClassName().replace('.', '/') + ".java";
//            fileName = Paths.get(sourceDir.toString(), classPath).toAbsolutePath().toString();
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }


        CallerInfo callerInfo = new CallerInfo(className, methodName, lineNumber);
        callerInfo.setDebugLink(createDebugLink());
        return callerInfo;
    }

    public static String createDebugLink() {
        // Capture debug info
        StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
        String className = caller.getClassName();
        int lineNumber = caller.getLineNumber();
        String filePath = Paths.get("src/main/java",
                        className.replace(".", "/") + ".java")
                .toAbsolutePath().toString();

        // Generate IntelliJ link

        return String.format("idea://open?file=%s&line=%d", filePath, lineNumber);
    }


    public enum Mode {
        DEV,
        PROD
    }

    public static final String this_object_name = "this";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static long handlerIdCounter = 1;


    private static long idCounter = 1;

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //objectMapper.configure(DeserializationFeature.FAIL_, false);
        objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
    }

    static {
        Engine engine = Engine.builder()
                .strictRendering(true)
                .addParserHook(p -> p.addContentFilter(s -> {
                    s = StringUtils.replace(s, "${", "{" + this_object_name + ".");
                    return s;
                }))
                //.addSectionHelper(new LoopSectionHelper.Factory())
                .addDefaultSectionHelpers()
//              .addValueResolver(new WidgetResolver())
                .addResultMapper(new QuteWidgetResultMapper())

                .addDefaultValueResolvers()
                .addParserHook(new Qute.IndexedArgumentsParserHook())
                .addResultMapper(new HtmlEscaper(ImmutableList.of("text/html")))
                .addValueResolver(new ReflectionValueResolver())
                .addValueResolver(ValueResolvers.rawResolver())

                // for freemarker style ${}
                .build();

        Qute.setEngine(engine);

        Qute.enableCache();
    }

    public static boolean isDebug() {
        return mode == Mode.DEV;
    }

    public static String eventHandlerNewId(Class aClass) {
        return aClass.getSimpleName() + "_" + handlerIdCounter++;
    }

    public static String escape(String text) {
        //   return StringEscapeUtils.escapeHtml4(text);
        return HtmlUtils.htmlEscape(text);
    }


    public static String quteKeyValue(String template, Object... keyValues) {
        try {
            Qute.Fmt f = Qute.fmt(template);
            f.variant(Variant.forContentType(Variant.TEXT_HTML));

            if (keyValues.length % 2 != 0) {
                throw new IllegalArgumentException("Number of arguments must be even (key-value pairs)");
            }

            // Izgradnja mape ključ-vrijednost
            Map<String, Object> dataMap = new HashMap<>();
            for (int i = 0; i < keyValues.length; i += 2) {
                if (!(keyValues[i] instanceof String key)) {
                    throw new IllegalArgumentException("Ključ mora biti tipa String");
                }
                Object value = keyValues[i + 1];
                dataMap.put(key, value);
            }
            return f.dataMap(dataMap).render();

        } catch (TemplateException e) {
            String templateWithLineNumbers = addLineNumbers(template);
            log.debug("Error " + e.getMessage());
            log.debug(templateWithLineNumbers);
            ExceptionUtils.rethrow(e);
            return "Error: " + e.getMessage();
        }


    }

    public static String quteMap(String template, Map<String, Object> map) {
        Qute.Fmt fmt = Qute.fmt(template);
        fmt.variant(Variant.forContentType(Variant.TEXT_HTML));
        try {
            return fmt.dataMap(map).render();
        } catch (TemplateException e) {
            String templateWithLineNumbers = addLineNumbers(template);
            TemplateException cause = e;
            if (ExceptionUtils.hasCause(e, TemplateException.class)) {
                if (e.getCause() != null)
                    cause = (TemplateException) e.getCause();
            }
            log.debug("Error " + cause.getMessage() + " ");
            log.debug(templateWithLineNumbers);
            ExceptionUtils.rethrow(e);
        }
        return template;
    }

    private static String addLineNumbers(String template) {
        String[] lines = template.split("\n");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            result.append(i + 1).append(". ").append(lines[i]).append("\n");
        }
        return result.toString();
    }

//    /**
//     * @param template
//     * @param widgets  array of widgets
//     * @return html
//     */
//    public static String quteWidget(String template, Widget... widgets) {
//        // add html variant
//        Qute.Fmt fmt = Qute.fmt(template);
//        fmt.variant(Variant.forContentType(Variant.TEXT_HTML));
//        fmt.dataArray((Object[]) widgets);
//        return fmt.render();
//        //return Qute.fmt(template, (Object[]) widgets);
//    }


    public static String htmlEscape(String html) {
        if (html == null) {
            return null;
        }
        StringBuilder escapedText = new StringBuilder(html.length() + 16);
        char currentChar;
        for (int i = 0; i < html.length(); i++) {
            currentChar = html.charAt(i);
            switch (currentChar) {
                case '<':
                    escapedText.append("&lt;");
                    break;
                case '>':
                    escapedText.append("&gt;");
                    break;
                case '&':
                    escapedText.append("&amp;");
                    break;
                case '"':
                    escapedText.append("&quot;");
                    break;
                case '\'':
                    escapedText.append("&#x27;");
                    break;
                default:
                    escapedText.append(currentChar);
            }
        }
        return escapedText.toString();
    }

    /**
     * return Qute.Map.of("this", this)
     *
     * @param template   Use a template like this: Hello {1} {2}!
     * @param thisObject object to be used in template rendering
     * @return html
     */
    public static String quteThis(String template, Object thisObject) {
        Qute.Fmt fmt = Qute.fmt(template);
        fmt.variant(Variant.forContentType(Variant.TEXT_HTML));

        return fmt.dataMap(Map.of(this_object_name, thisObject)).render();
    }


    @SneakyThrows
    public static String toJson(Object o) {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }

    @SneakyThrows
    public static String toJsonRaw(Object o) {
        return objectMapper.writer().writeValueAsString(o);
    }


    public static <T> T fromJson(String json, Class<T> aClass) throws JsonProcessingException {
        return objectMapper.readValue(json, aClass);
    }


    /**
     * Template {0} {1} ...
     *
     * @param template
     * @param params
     * @return
     */
    public static String format(String template, Object... params) {
        MessageFormat f = new MessageFormat(template);
        return f.format(params);
    }


    public static String wigetNewId(Class<? extends Widget> aClass) {
        // TODO : use UUID maybe
        return aClass.getSimpleName() + "_" + idCounter++;
    }

//    public static String pageNewId(Class<? extends Page> aClass) {
//        return aClass.getSimpleName() + "_" + RandomStringUtils.randomNumeric(10);
//    }


    public static void printStackTraceError(Exception e) {

        StackTraceElement element = e.getStackTrace()[0];
        String fileName = element.getFileName();
        int lineNumber = element.getLineNumber();
        log.debug("Datoteka: " + fileName);
        log.debug("Linija: " + lineNumber);

        String cn = element.getClassName().replace('.', '/') + ".class";
        URL u = WebooUtil.class.getClassLoader().getResource(cn);
        log.debug(u.toString().replace(".class", ".java") + ":" + element.getLineNumber());


    }


    public static String createPageId() {
        return UUID.randomUUID().toString();
    }


    public static String widgetToHtml(List<Widget> widgets) {
        if (widgets == null) {
            return "";
        }

        if (widgets.isEmpty()) {
            return "";
        }
        StringBuilder html = new StringBuilder();
        for (Widget widget : widgets) {
            if (WebooUtil.isDebug()) {
                String comment = "<!-- " + widget.getClass().getSimpleName() + " id: " + widget.widgetId() + " --> ";
                html.append(comment).append("\n");
            }
            String widgetHtml = widget.toHtml();
            html.append(widgetHtml).append("\n");

        }


        return html.toString();
    }

    private static String wrapWithDebug(String string) {
        // wrap with <div class="debug">...</div>
        return """
                <span class="debug">
                %s
                </span>
                """.formatted(string);
    }


//    public static String pageToHtml(Page page) {
//        List<Widget> widgetList = page.getWidgets();
//        return widgetToHtml(widgetList);
//    }

    /**
     * keyValues are key1, value2, key2, value2, ....
     *
     * @param keyValues
     * @return pairs
     */
    public static List<Pair<Object, Object>> toPairs(Object... keyValues) {

        List<Pair<Object, Object>> pairs = new ArrayList<>();
        if (keyValues == null || keyValues.length % 2 != 0) {
            return pairs;
        }
        for (int i = 0; i < keyValues.length; i += 2) {
            pairs.add(new MutablePair<>(keyValues[i], keyValues[i + 1]));
        }
        return pairs;
    }
}
