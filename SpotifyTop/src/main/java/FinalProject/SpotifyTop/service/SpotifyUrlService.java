package finalproject.spotifytop.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import finalproject.spotifytop.properties.SpotifyAppConfigurationProperties;
import finalproject.spotifytop.utility.CodeChallengeUtility;
import finalproject.spotifytop.utility.CodeVerifierUtility;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(SpotifyAppConfigurationProperties.class)
public class SpotifyUrlService {

	private final SpotifyAppConfigurationProperties spotifyAppConfigurationProperties;
	private String codeVerifier;

	/**
	 * Generates the authorization URL for Spotify API authentication.
	 *
	 * @return The authorization URL containing the necessary parameters for the authentication process.
	 */
	public String getAuthorizationURL() {
		final var properties = spotifyAppConfigurationProperties.getApp();

		// Generate a new code verifier for OAuth 2.0 PKCE (Proof Key for Code Exchange) flow
		final var codeVerifier = CodeVerifierUtility.generate();
		setCodeVerifier(codeVerifier);

		// Construct the authorization URL with the required parameters
		return "https://accounts.spotify.com/en/authorize?client_id=" + properties.getClientId()
				+ "&response_type=code&redirect_uri=" + properties.getRedirectUrl()
				+ "&code_challenge_method=S256&code_challenge=" + CodeChallengeUtility.generate(codeVerifier)
				+ "&scope=ugc-image-upload,user-read-playback-state,user-modify-playback-state,user-read-currently-playing,streaming,app-remote-control,user-read-email,user-read-private"
				+ ",playlist-read-collaborative,playlist-modify-public,playlist-read-private,playlist-modify-private,user-library-modify,user-library-read,user-top-read,user-read-playback-position,user-read-recently-played,user-follow-read,user-follow-modify";
	}
}
