package finalproject.spotifytop.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import finalproject.spotifytop.dto.AccessTokenDto;
import finalproject.spotifytop.properties.SpotifyAppConfigurationProperties;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(value = SpotifyAppConfigurationProperties.class)
public class AccessTokenService {

    // Injected dependencies using constructor-based dependency injection
    private final SpotifyUrlService spotifyUrlService;
    private final RestTemplate restTemplate;
    private final SpotifyAppConfigurationProperties spotifyAppConfigurationProperties;
    private static final String URL = "https://accounts.spotify.com/api/token";

    // Method to obtain an access token using the authorization code from Spotify API
    public String getToken(String code) {
        // Retrieve the Spotify application properties
        final var properties = spotifyAppConfigurationProperties.getApp();

        // Set up HTTP headers for the request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Set up the request body (parameters) for the token retrieval
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", properties.getClientId());
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", properties.getRedirectUrl());
        map.add("code_verifier", spotifyUrlService.getCodeVerifier());

        // Create an HTTP entity with the request body and headers
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        // Send a POST request to the Spotify API to obtain the access token
        ResponseEntity<AccessTokenDto> response = restTemplate.postForEntity(URL, request, AccessTokenDto.class);
        
        // Get the response body containing the access token
        AccessTokenDto accessTokenDto = response.getBody();

        // Check if the access token is not null and return it, otherwise throw an exception
        if (accessTokenDto != null) {
            return accessTokenDto.getAccess_token();
        } else {
            // Handle the case when the response body is null
            throw new IllegalStateException("Access token response body is null.");
        }
    }
}
