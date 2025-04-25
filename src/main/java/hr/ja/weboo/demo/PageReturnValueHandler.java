package hr.ja.weboo.demo;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PageReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return PageOld.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                 ModelAndViewContainer mavContainer, NativeWebRequest webRequest) {
        if (returnValue == null) {
            mavContainer.setRequestHandled(true);
            return;
        }

        PageOld pageOld = (PageOld) returnValue;
        
        String className = returnValue.getClass().getName();
        String simpleClassName = returnValue.getClass().getSimpleName();
        String attributeName = Character.toLowerCase(simpleClassName.charAt(0)) + 
                              simpleClassName.substring(1);
        mavContainer.addAttribute(attributeName, pageOld);
        
        mavContainer.setViewName(className);
    }
}