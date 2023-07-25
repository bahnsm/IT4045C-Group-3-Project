package finalproject.spotifytop.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import finalproject.spotifytop.constant.ApiPath;
import finalproject.spotifytop.constant.Template;
import finalproject.spotifytop.exception.NoTrackPlayingException;
import finalproject.spotifytop.service.AccessTokenService;
import finalproject.spotifytop.service.CurrentPlayingService;
import finalproject.spotifytop.service.ProfileDetailService;
import finalproject.spotifytop.service.SpotifyUrlService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CallbackController {

    // Injects necessary services using constructor-based dependency injection
    private final SpotifyUrlService spotifyUrlService;
    private final AccessTokenService accessToken;
    private final ProfileDetailService userDetails;
    private final CurrentPlayingService currentPlaying;

    // Handles GET requests to the '/callback' path with the specified media type
    @GetMapping(value = ApiPath.CALLBACK, produces = MediaType.TEXT_HTML_VALUE)
    public String handleCallback(@RequestParam(value = "code", required = false) final String code,
            @RequestParam(value = "error", required = false) final String error, final Model model,
            final HttpSession session) {

        // Check if an error occurred during the authentication process
        if (error != null) {
            // If there's an error, add the authorization URL to the model and return the failure template
            model.addAttribute("url", spotifyUrlService.getAuthorizationURL());
            return Template.CALLBACK_FAILURE;
        }

        // If there's no error, store the received code in the session
        session.setAttribute("code", code);

        // Retrieve the access token using the received code
        String token = accessToken.getToken(code);

        // Store the access token in the session and add it to the model
        session.setAttribute("accessToken", token);
        model.addAttribute("accessToken", token);

        // Retrieve the username of the user associated with the access token and add it to the model
        model.addAttribute("userName", userDetails.getUsername(token));

        try {
            // Try to retrieve information about the currently playing track and add it to the model
            model.addAttribute("currentPlaying", currentPlaying.getCurrentPlaying(token));
            model.addAttribute("display", 1); // Set a flag to indicate that a track is currently playing
        } catch (NoTrackPlayingException exception) {
            // If there's no track currently playing, set the flag to indicate that and handle the exception
            model.addAttribute("display", 0);
        }

        // Return the success template
        return Template.CALLBACK_SUCCESS;
    }
}
