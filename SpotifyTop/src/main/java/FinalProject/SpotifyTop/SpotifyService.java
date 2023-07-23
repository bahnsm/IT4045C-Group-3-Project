package FinalProject.SpotifyTop;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SpotifyService {

    private final WebClient webClient;

    public SpotifyService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> getTopTracks() {
        return webClient.get()
                .uri("/me/top/tracks?limit=5")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getTopArtists() {
        return webClient.get()
                .uri("/me/top/artists?limit=5")
                .retrieve()
                .bodyToMono(String.class);
    }
}
