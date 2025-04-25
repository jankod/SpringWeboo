package hr.ja.weboo.pages;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@Slf4j
@Controller
public class HomeController {

    @GetMapping(path = {"/"})
    public HomePage home() {
        return new HomePage();
    }


    @GetMapping(path = {"/user"})
    public UserPage user() {
        String url = getUrlFromController(HomeController.class, HomeController::home);
        log.info("User home url: {}", url);

        return new UserPage();
    }

//

    /**
     * Izvlači URL iz anotacije metode kontrolera
     *
     * @param controllerClass klasa kontrolera
     * @param methodRef       referenca na metodu kontrolera koja ima GetMapping anotaciju
     * @return URL definiran u GetMapping anotaciji
     */
    private <T, C> String getUrlFromController(Class<C> controllerClass, MethodReference<C, T> methodRef) {
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


    /**
     //     * Izvlači URL iz anotacije metode kontrolera
     //     *
     //     * @param <T>              tip povratne vrijednosti metode kontrolera
     //     * @param controllerMethod referenca na metodu kontrolera koja ima GetMapping anotaciju
     //     * @return URL definiran u GetMapping anotaciji
     //     */
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
