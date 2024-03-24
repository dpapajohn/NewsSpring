package com.papajohn.NewsSpring.articles;

import java.util.List;

public record NewsApiResponse(
    String status,
    Integer totalResults,
    List<Article> articles
)
{}
