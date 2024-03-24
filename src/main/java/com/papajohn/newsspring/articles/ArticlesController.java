package com.papajohn.NewsSpring.articles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class serves as a controller for managing articles in the application.
 */
@RestController
@RequestMapping("/api/articles")
public class ArticlesController {
    
    public final NewsApiClient newsApiClient;

    public ArticlesController(NewsApiClient newsApiClient) {
        this.newsApiClient = newsApiClient;
    }

    /**
     * The function `topArticlesCategorically` retrieves top articles based on country and category,
     * with an option to retrieve all categories for a specific country.
     * 
     * @param country The `country` parameter in the `@GetMapping` annotation represents the country
     * for which the user wants to retrieve top articles. It is a path variable that is part of the URL
     * path and is used to specify the country for which the news articles should be fetched.
     * @param category The `category` parameter in the `topArticlesCategorically` method represents the
     * category of news articles that the user wants to retrieve. This method is designed to fetch top
     * articles based on a specific country and category. If the value of the `category` parameter is
     * "all", it indicates that articles of all categories will be returned
     * @return The method `topArticlesCategorically` returns either the top articles for all categories
     * in a specific country or the top articles for a specific category in that country based on the
     * values of the `country` and `category` parameters.
     */
    @GetMapping("/top/{country}/{category}")
    NewsApiResponse topArticlesCategorically (
        @PathVariable("country") String country,
        @PathVariable("category") String category) {
        // The line `if("all".equals(category)) {` is checking if the value of the `category` variable
        // is equal to the string "all". This is a common way to compare strings in Java to avoid
        // potential `NullPointerException` errors. If the condition is true, it means that the user
        // wants to retrieve top articles for all categories in a specific country.
        if("all".equals(category)) {
            return newsApiClient.getTop(country);
        }
        return newsApiClient.getTop(country, category);
    }

    /**
     * The function `getArticlesBySourceName` retrieves news articles from a specified source using a
     * NewsApiClient.
     * 
     * @param source The `source` parameter in the `@GetMapping` annotation represents a path variable
     * in the URL. It is used to dynamically capture the value provided in the URL path and pass it to
     * the `getArticlesBySourceName` method as a parameter. In this case, it is used to specify the
     * @return An instance of the `NewsApiResponse` class is being returned.
     */
    @GetMapping("/source/{source}")
    NewsApiResponse getArticlesBySourceName (
        @PathVariable String source) {
        return newsApiClient.getBySourceName(source);
    }

    /**
     * This Java function uses a GET request to retrieve news articles from a specified source using
     * the NewsApiClient.
     * 
     * @param source The `source` parameter in the `@GetMapping` annotation represents a path variable
     * in the URL. In this case, it is used to specify the source name for which the articles are being
     * requested. The `source` parameter is then passed to the `getArticlesBySourceName` method as a
     * @return An instance of the `NewsApiResponse` class is being returned.
     */
    @GetMapping("/title/{title}")
    NewsApiResponse getArticlesByTitle (
        @PathVariable String title) {
        return newsApiClient.getByTitle(title);
    }

    /**
     * This Java function retrieves news articles based on a specified keyword using a GET request.
     * 
     * @param keyword The `keyword` parameter in the `@GetMapping` annotation represents a path
     * variable in the URL. When a request is made to the specified endpoint with a keyword value in
     * the URL, this method will be invoked, and the `keyword` value will be passed as an argument to
     * the `getArticles
     * @return An instance of the `NewsApiResponse` class is being returned.
     */
    @GetMapping("/keyword/{keyword}")
    NewsApiResponse getArticlesByKeyword (
        @PathVariable String keyword) {
        return newsApiClient.getByKeyword(keyword);
    }
}
