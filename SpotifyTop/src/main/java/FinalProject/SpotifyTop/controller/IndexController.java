package finalproject.spotifytop.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import finalproject.spotifytop.constant.Template;
import finalproject.spotifytop.service.SpotifyUrlService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class IndexController {

    // Injects the SpotifyUrlService using constructor-based dependency injection
    private final SpotifyUrlService spotifyUrlService;

    // Handles GET requests to the root path ("/") with the specified media type
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String showIndex(final Model model) {
        // Add the authorization URL to the model for the view to access
        model.addAttribute("url", spotifyUrlService.getAuthorizationURL());
        // Return the view template for the index page
        return Template.INDEX;
    }

}
