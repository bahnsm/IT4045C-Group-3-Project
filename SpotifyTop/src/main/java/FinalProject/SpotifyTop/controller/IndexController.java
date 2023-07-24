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

	private final SpotifyUrlService spotifyUrlService;

	@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
	public String showIndex(final Model model) {
		model.addAttribute("url", spotifyUrlService.getAuthorizationURL());
		return Template.INDEX;
	}

}
