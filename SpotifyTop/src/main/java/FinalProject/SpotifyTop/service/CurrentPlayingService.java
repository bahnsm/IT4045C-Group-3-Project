package finalproject.spotifytop.service;

import java.util.LinkedHashMap;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import finalproject.spotifytop.exception.NoTrackPlayingException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrentPlayingService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.spotify.com/v1/me/player/currently-playing";

    public LinkedHashMap<String, Object> getCurrentPlaying(String token) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + token);

    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

    ResponseEntity<LinkedHashMap<String, Object>> response = restTemplate.exchange(URL, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});

    // Check the response status code, and if no track is playing (204 status), throw an exception
    if (response.getStatusCode().value() == 204) {
        throw new NoTrackPlayingException();
    }

    // Retrieve the response body directly as LinkedHashMap<String, Object>
    LinkedHashMap<String, Object> result = response.getBody();
    return result;
}

}
