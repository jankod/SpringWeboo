package hr.ja.weboo.utils;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

public class SpringUtil {

    public static <T, C> String getUrlFromController(Class<C> controllerClass, MethodReference<C, T> methodRef) {
        return MvcUriComponentsBuilder
                .fromMethodCall(methodRef.invoke(on(controllerClass)))
                .build()
                .toString();
    }

    /**
     * Funkcionalno sučelje za type-safe referencu na metodu kontrolera
     */
    @FunctionalInterface
    private interface MethodReference<C, T> {
        T invoke(C controller);
    }


//    private <T> String getUrlFromController(Supplier<T> controllerMethod) {
//        String result = MvcUriComponentsBuilder.fromMethodCall(controllerMethod)
//                .build()
//                .toString();
//
//        return result;
////        String url = controllerMethod.getClass().getAnnotation(GetMapping.class).path()[0];
////        log.debug("URL: {}", url);
////        return url;
//    }
}
