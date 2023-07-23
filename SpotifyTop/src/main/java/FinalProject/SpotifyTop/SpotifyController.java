package FinalProject.SpotifyTop;

import FinalProject.SpotifyTop.SpotifyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

@RestController
public class SpotifyController {

    private final SpotifyService spotifyService;

    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/")
    public ModelAndView getTopData() {
        ModelAndView modelAndView = new ModelAndView("home");

        spotifyService.getTopTracks().subscribe(tracks -> modelAndView.addObject("topTracks", tracks));
        spotifyService.getTopArtists().subscribe(artists -> modelAndView.addObject("topArtists", artists));

        return modelAndView;
    }
}


