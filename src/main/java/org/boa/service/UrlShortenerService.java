package org.boa.service;


import org.boa.model.LongUrlRequest; /**
 * Defines general contract for a mediator service
 * 
 * @author lukas
 *
 */
public interface UrlShortenerService {

    String createShortUrl(LongUrlRequest request);

    String resolveShortUrl(String shortUrl);
}