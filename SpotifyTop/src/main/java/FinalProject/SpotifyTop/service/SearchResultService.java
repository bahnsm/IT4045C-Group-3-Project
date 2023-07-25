package finalproject.spotifytop.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import finalproject.spotifytop.exception.InvalidSearchException;
import finalproject.spotifytop.utility.SearchQueryFormatter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchResultService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.spotify.com/v1/search?q=SEARCH_QUERY&type=album,artist,playlist,track&limit=3";

    /**
     * Performs a search on the Spotify API based on the given search query.
     *
     * @param token       The access token required for authentication with the Spotify API.
     * @param searchQuery The search query to perform the search.
     * @return The search result as a LinkedHashMap containing the response from the Spotify API.
     * @throws InvalidSearchException if the search query is invalid or the result is empty.
     */
    public LinkedHashMap<String, Object> search(String token, String searchQuery) throws InvalidSearchException {
        if (!isValid(token, searchQuery)) {
            throw new InvalidSearchException();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // Perform the search with the formatted URL
        ResponseEntity<LinkedHashMap<String, Object>> response = restTemplate.exchange(
                URL.replace("SEARCH_QUERY", SearchQueryFormatter.format(searchQuery)),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {}
        );

        // Retrieve the response body directly as LinkedHashMap<String, Object>
        LinkedHashMap<String, Object> result = response.getBody();

        // Handle the case where the response is null (e.g., API issue)
        if (result == null) {
            throw new RuntimeException("Search result is null or empty.");
        }

        return result;
    }

    /**
     * Checks if the search query is valid and returns true if there are any results.
     *
     * @param token       The access token required for authentication with the Spotify API.
     * @param searchQuery The search query to perform the search.
     * @return true if the search query is valid and there are any results, false otherwise.
     */
    public boolean isValid(String token, String searchQuery) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // Perform the search with the formatted URL
        ResponseEntity<LinkedHashMap<String, Object>> response = restTemplate.exchange(
                URL.replace("SEARCH_QUERY", SearchQueryFormatter.format(searchQuery)),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {}
        );

        // Retrieve the response body directly as LinkedHashMap<String, Object>
        LinkedHashMap<String, Object> result = response.getBody();

		    // Check if the result is null (no search results)
			if (result == null) {
				return false;
			}

        // Check if there are any results in albums, artists, playlists, or tracks
        LinkedHashMap<?, ?> albums = (LinkedHashMap<?, ?>) result.get("albums");
        ArrayList<?> itemsAlbums = (ArrayList<?>) albums.get("items");

        LinkedHashMap<?, ?> artists = (LinkedHashMap<?, ?>) result.get("artists");
        ArrayList<?> itemsArtists = (ArrayList<?>) artists.get("items");

        LinkedHashMap<?, ?> tracks = (LinkedHashMap<?, ?>) result.get("tracks");
        ArrayList<?> itemsTracks = (ArrayList<?>) tracks.get("items");

        LinkedHashMap<?, ?> playlists = (LinkedHashMap<?, ?>) result.get("playlists");
        ArrayList<?> itemsPlaylists = (ArrayList<?>) playlists.get("items");
		

        return (itemsAlbums.size() + itemsArtists.size() + itemsPlaylists.size() + itemsTracks.size()) > 0;
    }
}
