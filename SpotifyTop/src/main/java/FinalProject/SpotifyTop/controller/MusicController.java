package finalproject.spotifytop.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import finalproject.spotifytop.constant.ApiPath;
import finalproject.spotifytop.constant.Template;
import finalproject.spotifytop.exception.NoAccountDataException;
import finalproject.spotifytop.exception.NoAlbumSavedException;
import finalproject.spotifytop.exception.NoTrackSavedException;
import finalproject.spotifytop.service.FeaturedPlaylistService;
import finalproject.spotifytop.service.NewReleasedService;
import finalproject.spotifytop.service.RecentPlayesTrackService;
import finalproject.spotifytop.service.SavedAlbumService;
import finalproject.spotifytop.service.SavedTrackService;
import finalproject.spotifytop.service.TopArtistService;
import finalproject.spotifytop.service.TopTrackService;
import finalproject.spotifytop.utility.TermPeriodUtility;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MusicController {

    // Injects the TopTrackService and TopArtistService using constructor-based dependency injection
    private final TopTrackService topTrackService;
    private final TopArtistService topArtistService;

    // Handles GET requests to the '/top-tracks' path with the specified media type
    @GetMapping(value = ApiPath.TOP_TRACKS, produces = MediaType.TEXT_HTML_VALUE)
    public String topTracksaHandler(@RequestParam("term") final Integer term, final HttpSession session,
            final Model model) {
        try {
            // Fetches the top tracks from the TopTrackService and adds them to the model
            model.addAttribute("tracks", topTrackService.getTopTracks((String) session.getAttribute("accessToken"), term));
            // Adds the selected term (time period) to the model using TermPeriodUtility.getTerm() method
            model.addAttribute("term", TermPeriodUtility.getTerm(term));
        } catch (NoAccountDataException exception) {
            // If there's no account data (e.g., no tracks), return the 'no-data' template
            return Template.NO_DATA;
        }
        // Return the template for displaying the top tracks
        return Template.TOP_TRACKS;
    }

    // Handles GET requests to the '/top-artists' path with the specified media type
    @GetMapping(value = ApiPath.TOP_ARTISTS, produces = MediaType.TEXT_HTML_VALUE)
    public String topArtistsHandler(@RequestParam("term") final Integer term, final HttpSession session,
            final Model model) {
        try {
            // Fetches the top artists from the TopArtistService and adds them to the model
            model.addAttribute("artists", topArtistService.getTopArtists((String) session.getAttribute("accessToken"), term));
            // Adds the selected term (time period) to the model using TermPeriodUtility.getTerm() method
            model.addAttribute("term", TermPeriodUtility.getTerm(term));
        } catch (NoAccountDataException exception) {
            // If there's no account data (e.g., no artists), return the 'no-data' template
            return Template.NO_DATA;
        }
        // Return the template for displaying the top artists
        return Template.TOP_ARTISTS;
    }
}
