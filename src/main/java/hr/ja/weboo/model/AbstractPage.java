package hr.ja.weboo.model;

import hr.ja.weboo.pages.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.web.servlet.View;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractPage extends Page implements View {


    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        Layout layout = new Layout();
        layout.setModel(model);
        layout.setRequest(request);
        layout.setResponse(response);

        render(layout);
        response.getWriter().write(layout.toHtml(this));
    }

    protected void render(Layout layout) {
        //return "No implemented toHtml() method in " + this.getClass().getName();
        //layout.setTitle("No implemented toHtml() method in " + this.getClass().getName());
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
