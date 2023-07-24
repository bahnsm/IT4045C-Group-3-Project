// Import necessary classes from Spring framework
package finalproject.spotifytop.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// Configuration class that defines Spring beans
@Configuration
public class RestTemplateBean {

    // Bean definition method for RestTemplate
    @Bean
    public RestTemplate restTemplate() {
        // Creates and configures a new instance of RestTemplate
        return new RestTemplate();
    }

}

