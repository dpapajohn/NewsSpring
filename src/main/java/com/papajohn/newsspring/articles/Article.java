package com.papajohn.NewsSpring.articles;

import java.time.LocalDateTime;

public record Article(
    Source source,
    String author,
    String title,
    String description,
    String url,
    String urlToImage,
    LocalDateTime publishedAt,
    String content
)
{}
