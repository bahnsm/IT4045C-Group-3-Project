package FinalProject.SpotifyTop.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "FinalProject.SpotifyTop")
public class SpotifyAppConfigurationProperties {

	private App app = new App();

	@Data
	public class App {
		private String clientId;
		private String redirectUrl;
	}
}
