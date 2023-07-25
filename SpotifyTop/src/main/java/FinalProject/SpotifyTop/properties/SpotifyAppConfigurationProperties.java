package finalproject.spotifytop.properties;

// Lombok annotation that automatically generates getter, setter, equals, hashCode, and toString methods
import lombok.Data;

// Spring Boot annotation to mark the class as configuration properties
import org.springframework.boot.context.properties.ConfigurationProperties;

// Lombok annotation to automatically generate getter and setter methods for the class
@Data

// Spring Boot annotation that specifies the prefix for the configuration properties
@ConfigurationProperties(prefix = "finalproject.spotifytop")
public class SpotifyAppConfigurationProperties {

    // Instance of the inner class 'App' which holds the properties related to the Spotify application
    private App app = new App();

    // Inner class representing properties related to the Spotify application
    @Data
    public class App {
        // Property to store the Spotify client ID
        private String clientId;
        
        // Property to store the URL where the Spotify authentication callback should redirect
        private String redirectUrl;
    }
}
