package finalproject.spotifytop.service;

import java.util.LinkedHashMap;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeaturedPlaylistService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.spotify.com/v1/browse/featured-playlists?limit=50";

    /**
     * Fetches the featured playlists from the Spotify API.
     *
     * @param token The access token required for authentication with the Spotify API.
     * @return A LinkedHashMap containing the featured playlists data.
     */
    public LinkedHashMap<String, Object> getPlaylists(String token) {
        // Set the authorization header with the access token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        // Create an HttpEntity with the headers
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        // Make a GET request to the Spotify API and retrieve the response
        ResponseEntity<LinkedHashMap<String, Object>> response = restTemplate.exchange(URL, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});

        // Retrieve the response body directly as LinkedHashMap<String, Object>
        LinkedHashMap<String, Object> result = response.getBody();

        return result;
    }
}

