package hr.ja.weboo.demo;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class CompHtmlConverter implements HttpMessageConverter<PageOld> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return PageOld.class.isAssignableFrom(clazz) &&
               mediaType != null &&
               mediaType.includes(MediaType.TEXT_HTML);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(MediaType.TEXT_HTML);
    }

    @Override
    public PageOld read(Class<? extends PageOld> clazz, HttpInputMessage inputMessage)
            throws HttpMessageNotReadableException {

        throw new HttpMessageNotReadableException("Reading not supported", inputMessage);
    }

    @Override
    public void write(PageOld comp, MediaType contentType, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        outputMessage.getHeaders().setContentType(MediaType.TEXT_HTML);
        byte[] bytes = comp.toHtml().getBytes(StandardCharsets.UTF_8);
        outputMessage.getBody().write(bytes);
    }
}