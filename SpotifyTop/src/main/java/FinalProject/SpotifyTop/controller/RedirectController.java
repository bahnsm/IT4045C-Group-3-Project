package finalproject.spotifytop.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import finalproject.spotifytop.constant.ApiPath;
import finalproject.spotifytop.constant.Template;
import finalproject.spotifytop.exception.NoTrackPlayingException;
import finalproject.spotifytop.service.CurrentPlayingService;
import finalproject.spotifytop.service.ProfileDetailService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RedirectController {

    // Injects the ProfileDetailService and CurrentPlayingService using constructor-based dependency injection
    private final ProfileDetailService userDetails;
    private final CurrentPlayingService currentPlaying;

    // Handles GET requests to the '/redirect' path with the specified media type
    @GetMapping(value = ApiPath.REDIRECT, produces = MediaType.TEXT_HTML_VALUE)
    public String redirectToCallbackSuccess(final HttpSession session, final Model model) {
        // Retrieve the access token from the session
        String token = (String) session.getAttribute("accessToken");

        // Add the access token and the associated username to the model
        model.addAttribute("accessToken", token);
        model.addAttribute("userName", userDetails.getUsername(token));

        try {
            // Try to retrieve information about the currently playing track and add it to the model
            model.addAttribute("currentPlaying", currentPlaying.getCurrentPlaying(token));
            model.addAttribute("display", 1); // Set a flag to indicate that a track is currently playing
        } catch (NoTrackPlayingException exception) {
            // If there's no track currently playing, set the flag to indicate that and handle the exception
            model.addAttribute("display", 0);
        }

        // Return the success template (callback success page)
        return Template.CALLBACK_SUCCESS;
    }
}
