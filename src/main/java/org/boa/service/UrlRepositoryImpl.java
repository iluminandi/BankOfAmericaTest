package org.boa.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * The real solution should store the mapped shortened and long URLS in permanent storage (SQL DB or noSQL)
 * very likely I would use Spring Data Repositories (CrudRepository) for storing and selecting data efficiently
 *
 * the row storage format would be [rowId, shortUrl, longUrl], both shortUrl and longUrl indexed and uniquely constrained
 * the above row would be mapped into POJO class
 *
 * However for demo purposes I implemented very simple repository based on hash map
 *
 * @author lukas
 *
 */
//@Repository
@Service
public class UrlRepositoryImpl implements UrlRepository {

    private Map<String, String> urlStorage = new HashMap<>();

    public Map<String, String> getUrlStorage() {
        return urlStorage;
    }

    @Override
    public void save(String shortUrlIdentifier, String longUrl) {
        urlStorage.put(shortUrlIdentifier, longUrl);
    }

    @Override
    public String findByLongUrl(String longUrl) {
        return urlStorage.values().stream().filter(longUrl::equals)
                .findAny().orElseThrow(() -> new RuntimeException("Long Url not found"));
    }

    @Override
    public String findByShortUrl(String shortUrl) {
        return urlStorage.get(shortUrl);
    }
}
