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

	private final TopTrackService topTrackService;
	private final TopArtistService topArtistService;
	private final SavedTrackService savedTrackService;
	private final SavedAlbumService savedAlbumService;
	private final NewReleasedService newReleasedService;
	private final RecentPlayesTrackService recentPlayedTrackService;
	private final FeaturedPlaylistService featuredPlaylistService;

	@GetMapping(value = ApiPath.TOP_TRACKS, produces = MediaType.TEXT_HTML_VALUE)
	public String topTracksaHandler(@RequestParam("term") final Integer term, final HttpSession session,
			final Model model) {
		try {
			model.addAttribute("tracks",
					topTrackService.getTopTracks((String) session.getAttribute("accessToken"), term));
			model.addAttribute("term", TermPeriodUtility.getTerm(term));
		} catch (NoAccountDataException exception) {
			return Template.NO_DATA;
		}
		return Template.TOP_TRACKS;
	}

	@GetMapping(value = ApiPath.TOP_ARTISTS, produces = MediaType.TEXT_HTML_VALUE)
	public String topArtistsHandler(@RequestParam("term") final Integer term, final HttpSession session,
			final Model model) {
		try {
			model.addAttribute("artists",
					topArtistService.getTopArtists((String) session.getAttribute("accessToken"), term));
			model.addAttribute("term", TermPeriodUtility.getTerm(term));
		} catch (NoAccountDataException exception) {
			return Template.NO_DATA;
		}
		return Template.TOP_ARTISTS;
	}

}
