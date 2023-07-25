package finalproject.spotifytop.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import finalproject.spotifytop.exception.NoAccountDataException;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TopArtistService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.spotify.com/v1/me/top/artists?time_range=";

    /**
     * Get the user's top artists from Spotify API.
     *
     * @param token The access token required for authentication with the Spotify API.
     * @param term  The time range for which to get the top artists (0 for short-term, 1 for medium-term, 2 for long-term).
     * @return The user's top artists as a LinkedHashMap containing the response from the Spotify API.
     * @throws NoAccountDataException if there are no top artists for the given time range.
     */
    public LinkedHashMap<String, Object> getTopArtists(String token, int term) throws NoAccountDataException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        String terms[] = { "short_term", "medium_term", "long_term" };

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // Perform the search with the formatted URL
        ResponseEntity<LinkedHashMap<String, Object>> response = restTemplate.exchange(
                URL + terms[term], HttpMethod.GET, entity, new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {}
        );

        // Retrieve the response body directly as LinkedHashMap<String, Object>
        LinkedHashMap<String, Object> result = response.getBody();

        // Check if the result is null or empty (e.g., API issue)
        if (result == null || result.isEmpty()) {
            throw new NoAccountDataException();
        }

        // Retrieve the "items" list containing top artists
        List<?> items = (List<?>) result.get("items");

        // Check if there are any top artists in the list
        if (items == null || items.isEmpty()) {
            throw new NoAccountDataException();
        }

        return result;
    }
}
