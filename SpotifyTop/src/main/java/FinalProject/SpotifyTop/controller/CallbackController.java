package FinalProject.SpotifyTop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import FinalProject.SpotifyTop.constant.ApiPath;
import FinalProject.SpotifyTop.constant.Template;
import FinalProject.SpotifyTop.exception.NoTrackPlayingException;
import FinalProject.SpotifyTop.service.AccessTokenService;
import FinalProject.SpotifyTop.service.CurrentPlayingService;
import FinalProject.SpotifyTop.service.ProfileDetailService;
import FinalProject.SpotifyTop.service.SpotifyUrlService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CallbackController {

	private final SpotifyUrlService spotifyUrlService;
	private final AccessTokenService accessToken;
	private final ProfileDetailService userDetails;
	private final CurrentPlayingService currentPlaying;

	@GetMapping(value = ApiPath.CALLBACK, produces = MediaType.TEXT_HTML_VALUE)
	public String handleCallback(@RequestParam(value = "code", required = false) final String code,
			@RequestParam(value = "error", required = false) final String error, final Model model,
			final HttpSession session) {

		if (error != null) {
			model.addAttribute("url", spotifyUrlService.getAuthorizationURL());
			return Template.CALLBACK_FAILURE;
		}
		session.setAttribute("code", code);
		String token = accessToken.getToken(code);

		session.setAttribute("accessToken", token);
		model.addAttribute("accessToken", token);
		model.addAttribute("userName", userDetails.getUsername(token));

		try {
			model.addAttribute("currentPlaying", currentPlaying.getCurrentPlaying(token));
			model.addAttribute("display", 1);
		} catch (NoTrackPlayingException exception) {
			model.addAttribute("display", 0);
		}

		return Template.CALLBACK_SUCCESS;
	}
}
