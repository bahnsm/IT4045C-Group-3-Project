package finalproject.spotifytop.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import finalproject.spotifytop.constant.ApiPath;

@Controller
public class LogoutController {

    // Handles GET requests to the '/logout' path with the specified media type
    @GetMapping(value = ApiPath.LOGOUT, produces = MediaType.TEXT_HTML_VALUE)
    public String logoutHandler(final HttpSession session) {
        // Invalidates the current session, effectively logging out the user
        session.invalidate();

        // Redirects the user to the index (home) page with a logout parameter in the URL
        return "redirect:/?logout";
    }
}
