package hr.ja.weboo.utils;

import hr.ja.weboo.ui.widgets.Widget;
import io.quarkus.qute.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Slf4j
public class QuteUtil {

    private static final Engine engine;

    static {
        ReflectionValueResolver reflectionValueResolver = new ReflectionValueResolver();
        EngineBuilder builder = Engine.builder();

        // --- Dodavanje ParserHook-a za ${} sintaksu ---
        builder.addParserHook(parserHelper -> {
            parserHelper.addContentFilter(templateContent -> {
                // Koristi regularni izraz za zamjenu ${expression} s {expression}
                // Regex objašnjenje:
                // \\$\\{   - Traži literalne znakove ${ (treba escapeati $ i {)
                // (.+?)    - Hvata bilo koje znakove (.) jednom ili više puta (+) ali ne-pohlepno (?)
                //           Ovo je grupa za hvatanje (capture group 1)
                // \\}     - Traži literalni znak } (treba escapeati)
                // Zamjena: "{$1}" - Zamjenjuje cijeli pronađeni niz s { + sadržaj grupe 1 + }
                return templateContent.replaceAll("\\$\\{(.+?)}", "{$1}");
            });
        });
        // --- Kraj ParserHook-a ---


        engine = builder
                .strictRendering(true)
                .addDefaultSectionHelpers()
                .addResultMapper(new ResultMapper() {

                    @Override
                    public boolean appliesTo(TemplateNode.Origin origin, Object result) {
                        return result instanceof Widget;
                    }

                    @Override
                    public String map(Object result, Expression expression) {
                        Widget widget = (Widget) result;

                    }
                }) // Pretpostavljam da ova klasa postoji
                .addDefaultValueResolvers()
                .addParserHook(new Qute.IndexedArgumentsParserHook())
                .addResultMapper(new HtmlEscaper(ImmutableList.of("text/html"))) // Koristimo importan ImmutableList
                .addValueResolver(reflectionValueResolver)
                .addValueResolver(ValueResolvers.rawResolver())
                .build();
    }

    public static String quteKeyValue(String template, Object... keyValues) {
        Map<String, Object> dataMap = toMap(keyValues);
        return quteMap(template, dataMap);
    }

    private static Map<String, Object> toMap(Object[] keyValues) {
        if (keyValues.length % 2 != 0) {
            throw new IllegalArgumentException("Number of arguments must be even (key-value pairs)");
        }

        Map<String, Object> dataMap = new HashMap<>();
        for (int i = 0; i < keyValues.length; i += 2) {
            if (!(keyValues[i] instanceof String key)) {
                throw new IllegalArgumentException("Key must be a String");
            }
            Object value = keyValues[i + 1];
            dataMap.put(key, value);
        }
        return dataMap;
    }

    /**
     * Renders a given Qute template using the provided key-value pairs from the map.
     * Each key in the map is made directly accessible in the template as a template parameter.
     * Also ensures proper HTML escaping to prevent injection issues.
     *
     * @param template The Qute template string to be rendered.
     * @param map      A map where keys represent template variables and values are their corresponding substitutions.
     * @return The rendered template as a string after processing with the provided data.
     */
    public static String quteMap(String template, Map<String, Object> map) {
        if (engine == null) {
            log.error("Qute engine not initialized");
            return "Error: Qute engine not initialized"; // Ili baciti iznimku
        }
        try {

            // TODO: put in static initialization
            Variant variant = Variant.forContentType(Variant.TEXT_HTML);

            Template parsedTemplate = engine.parse(template, variant);

            TemplateInstance instance = parsedTemplate.instance();
            map.forEach(instance::data);
//            instance.setAttribute(TemplateInstance.VARIANTS,
//                    Collections.singletonList(variant));
            return instance.render();
        } catch (TemplateException e) {
            String templateWithLineNumbers = addLineNumbers(template);
            TemplateException cause = findRootTemplateException(e);
            log.debug("Error: '{}' in template:\n{}", cause.getMessage(), templateWithLineNumbers);
            ExceptionUtils.rethrow(e);
        } catch (Exception e) {
            log.error("Unespected error: {}", e.getMessage(), e);
            ExceptionUtils.rethrow(e);
        }
        return ""; // Nedostižno
    }


    /**
     * Renderira predložak koristeći zadani objekt kao korijenski podatkovni objekt.
     * Omogućuje izravan pristup članovima objekta (npr. {param1}) unutar predloška.
     *
     * @param template   Qute predložak.
     * @param thisObject Objekt čiji će članovi biti dostupni u predlošku.
     * @return Renderirani HTML.
     */
    public static String quteThis(String template, Object thisObject) {
        try {
            // TODO: cache Template (not TemplateInstance) by Widget class name or template string hash
            Template parsedTemplate = engine.parse(template);
            TemplateInstance instance = parsedTemplate.instance();
            // Set thisObject as the root data object
            instance.data(thisObject);
            instance.setAttribute(TemplateInstance.VARIANTS,
                    Collections.singletonList(Variant.forContentType(Variant.TEXT_HTML)));

            return instance.render();
        } catch (TemplateException e) {
            String templateWithLineNumbers = addLineNumbers(template);
            TemplateException cause = findRootTemplateException(e);
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            log.debug("Error {} in template:\n{}", cause.getMessage() + "\n" + rootCause.getMessage(), templateWithLineNumbers);
            ExceptionUtils.rethrow(e);
        } catch (Exception e) {
            log.error("Unespected error in quteThis: {}", e.getMessage(), e);
            ExceptionUtils.rethrow(e);
        }
        return ""; // Nedostižno

    }

    // Pomoćna metoda za pronalaženje osnovne TemplateException
    private static TemplateException findRootTemplateException(Throwable e) {
        Throwable cause = e;
        while (cause != null && !(cause instanceof TemplateException)) {
            cause = cause.getCause();
        }
        TemplateException templateException = (cause instanceof TemplateException) ? (TemplateException) cause :
                ((e instanceof TemplateException) ? (TemplateException) e : null);
        return templateException;
    }


    private static String addLineNumbers(String template) {
        String[] lines = template.split("\n");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            result.append(i + 1).append(". ").append(lines[i]).append("\n");
        }
        return result.toString();
    }
}
