package org.boa.service;

public interface UrlRepository {
    void save(String shortUrlIdentifier, String longUrl);

    String findByLongUrl(String shortUrl);

    String findByShortUrl(String shortUrl);
}
