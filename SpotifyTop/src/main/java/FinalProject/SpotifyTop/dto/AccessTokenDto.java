package finalproject.spotifytop.dto;
// Lombok annotations for generating getter methods, constructor with all arguments, and default constructor
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Lombok annotation to automatically generate getter methods for the class
@Getter

// Lombok annotation to generate a constructor with all arguments
@AllArgsConstructor

// Lombok annotation to generate a default constructor
@NoArgsConstructor

public class AccessTokenDto {

    // Private field representing the access token obtained from Spotify API
    private String access_token;

}