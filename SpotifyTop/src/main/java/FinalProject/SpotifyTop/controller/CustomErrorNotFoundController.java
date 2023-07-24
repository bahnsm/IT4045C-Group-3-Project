package finalproject.spotifytop.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import finalproject.spotifytop.constant.ApiPath;
import finalproject.spotifytop.constant.Template;

@Controller
public class CustomErrorNotFoundController implements ErrorController {

    // Handles requests to the '/error' path with the specified media type
    @RequestMapping(value = ApiPath.ERROR, produces = MediaType.TEXT_HTML_VALUE)
    public String handleError() {
        // Returns the view template for the error page
        return Template.ERROR;
    }

}