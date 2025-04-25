package hr.ja.weboo.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    //private final CompHtmlConverter compHtmlConverter;

//    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;

//    private final PageReturnValueHandler pageReturnValueHandler;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }


    // za sve controllere

//        registry.addInterceptor(new RenderingInterceptor())
//                .addPathPatterns("/**");

}
