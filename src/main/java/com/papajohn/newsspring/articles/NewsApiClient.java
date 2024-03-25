package com.papajohn.NewsSpring.articles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * This class is used to interact with the NewsAPI.org news API.
 */
@Service
public class NewsApiClient {

    private final RestClient restClient;

    public NewsApiClient(RestClient.Builder builder, @Value("${newsApi.apiKey}") String apikey) {
        this.restClient = builder
            .baseUrl("https://newsapi.org/")
            .defaultHeader("X-Api-Key", apikey)
            .build();
    }

    @Cacheable("newsCache")
    public NewsApiResponse getBySourceName(String sourceName) {
        String uri = UriComponentsBuilder
            .fromPath("/v2/everything")
            .queryParam(
                "q", ""
            )
            .queryParam(
                "sources", sourceName
            )
            .build()
            .toString();

        return restClient.get()
            .uri(uri)
            .retrieve()
            .body(NewsApiResponse.class);
    }

    @Cacheable("newsCache")
    public NewsApiResponse getByTitle(String title) {
        String uri = UriComponentsBuilder
            .fromPath("/v2/everything")
            .queryParam(
                "q", title
            )
            .queryParam(
                "searchIn", "title"
            )
            .build()
            .toString();

        return restClient.get()
            .uri(uri)
            .retrieve()
            .body(NewsApiResponse.class);
    }

    @Cacheable("newsCache")
    public NewsApiResponse getByKeyword(String keyword) {
        String uri = UriComponentsBuilder
            .fromPath("/v2/everything")
            .queryParam(
                "q", keyword
            )
            .build()
            .toString();

        return restClient.get()
            .uri(uri)
            .retrieve()
            .body(NewsApiResponse.class);
    }

    @Cacheable("newsCache")
    public NewsApiResponse getTop(String country) {
        String uri = UriComponentsBuilder
            .fromPath("/v2/top-headlines")
            .queryParam(
                "country", country
            )
            .build()
            .toString();

        return restClient.get()
            .uri(uri)
            .retrieve()
            .body(NewsApiResponse.class);
    }

    @Cacheable("newsCache")
    public NewsApiResponse getTop(String country, String category) {
        String uri = UriComponentsBuilder
            .fromPath("/v2/top-headlines")
            .queryParam(
                "country", country
            )
            .queryParam(
                "category", category
            )
            .build()
            .toString();

        return restClient.get()
            .uri(uri)
            .retrieve()
            .body(NewsApiResponse.class);
    }
}
