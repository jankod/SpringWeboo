package hr.ja.weboo.pages;

import hr.ja.weboo.model.User;
import hr.ja.weboo.ui.PageContext;
import hr.ja.weboo.ui.widgets.ColumnRender;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.function.*;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Slf4j
@Component
public class Route {


    public void init() {
        RouterFunctions.Builder builder = route().GET("/", Route::home);
        route().GET("/user", Route::user).build();
    }

    private static ServerResponse home(ServerRequest req) {
        HomePage homePage = new HomePage();
        homePage.render(PageContext.getCurrentContext());

        return ServerResponse.ok().build();
    }

    private static ServerResponse user(ServerRequest request) throws BindException {
        log.debug("GET /user");
        UserPage page = new UserPage();

        request.bind(User.class);
        return ServerResponse.ok().build();
    }


}


@FunctionalInterface
interface RouteHandler extends Serializable {
    String handle(ServerRequest req);
}

final class RouteMethods {

    public static Method extract(RouteHandler handler) {
        try {
            Method writeReplace =
                  handler.getClass().getDeclaredMethod("writeReplace");
            writeReplace.setAccessible(true);

            SerializedLambda lambda =
                  (SerializedLambda) writeReplace.invoke(handler);

            Class<?> clazz =
                  Class.forName(lambda.getImplClass().replace('/', '.'));

            for (Method m : clazz.getDeclaredMethods()) {
                if (m.getName().equals(lambda.getImplMethodName())) {
                    return m;
                }
            }
            throw new IllegalStateException("Method not found");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

@Getter
@RequiredArgsConstructor
final class RouteRef {
    private final String path;
    private final Method method;
}

final class Routes {

    private final Map<Method, RouteRef> byMethod = new HashMap<>();

    public RouteRef GET(String path, RouteHandler handler) {
        Method m = RouteMethods.extract(handler);
        RouteRef ref = new RouteRef(path, m);
        byMethod.put(m, ref);

        // ovdje registriraš RouterFunction
        // RouterFunctions.route(GET(path), handler)
        RouterFunctions.route().GET(path, new HandlerFunction<ServerResponse>() {
            @Override
            public ServerResponse handle(ServerRequest request) throws Exception {
                String html = handler.handle(request);

                return null;
            }
        });

        return ref;
    }

    public String path(RouteHandler handler) {
        Method m = RouteMethods.extract(handler);
        return byMethod.get(m).getPath();
    }
}
