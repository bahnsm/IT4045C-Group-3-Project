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
public class ProfileDetailService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.spotify.com/v1/me";

    /**
     * Fetches the user profile details from the Spotify API.
     *
     * @param token The access token required for authentication with the Spotify API.
     * @return A LinkedHashMap containing the user profile data.
     */
    public LinkedHashMap<String, Object> getUser(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>("paramters", headers);

        ResponseEntity<LinkedHashMap<String, Object>> response = restTemplate.exchange(URL, HttpMethod.GET, entity, new ParameterizedTypeReference<LinkedHashMap<String, Object>>() {});

        // Retrieve the response body directly as LinkedHashMap<String, Object>
        LinkedHashMap<String, Object> result = response.getBody();

        return result;
    }

    /**
     * Fetches the username from the user profile details.
     *
     * @param token The access token required for authentication with the Spotify API.
     * @return The display name (username) of the user.
     */
    public String getUsername(String token) {
        LinkedHashMap<String, Object> user = getUser(token);
        return (String) user.get("display_name");
    }

}

