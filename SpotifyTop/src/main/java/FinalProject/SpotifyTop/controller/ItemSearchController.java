package finalproject.spotifytop.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import finalproject.spotifytop.constant.ApiPath;
import finalproject.spotifytop.constant.Template;
import finalproject.spotifytop.exception.InvalidSearchException;
import finalproject.spotifytop.service.SearchResultService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ItemSearchController {


    // Injects the SearchResultService using constructor-based dependency injection
    private final SearchResultService searchResults;

    // Handles POST requests to the '/search' path with the specified media type
    @PostMapping(value = ApiPath.SEARCH, produces = MediaType.TEXT_HTML_VALUE)
    public String showSearchResults(@RequestParam("searchQuery") final String searchQuery, final HttpSession session,
            final Model model) {
        // Retrieve the access token from the session
        String token = (String) session.getAttribute("accessToken");
        try {
            // Perform the search operation using the SearchResultService and add the results to the model
            model.addAttribute("results", searchResults.search(token, searchQuery));
        } catch (InvalidSearchException exception) {
            // If the search query is invalid, return the search error template
            return Template.SEARCH_ERROR;
        }
        // Return the search results template
        return Template.SEARCH_RESULTS;
    }

}
