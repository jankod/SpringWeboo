package hr.ja.weboo.pages;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping(path = {"/"})
    public HomePage home() {
        return new HomePage();
    }

    @GetMapping(path = {"/user"})
    public UserPage user() {
        return new UserPage();
    }
}
