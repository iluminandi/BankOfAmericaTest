package org.boa.service;

import lombok.extern.slf4j.Slf4j;
import org.boa.model.LongUrlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * This service implements functionality for shortening of long urls
 * as well as resolving shortened urls into the long ones.
 *
 * The service generates a shorter and unique alias of long url by getting base64 encoded current millisecons.
 * The collisions of short url (alias) are unlikely but not completely ruled out, retry should solve the issue
 * The generated base64 encoded alias must be associated with long url, thereby they must be saved together in the repo
 * The saved combination of short and long urls must be unique
 *
 *
 * 
 * @author lukas
 */
@Service
@Slf4j
public class UrlShortenerServiceImpl implements UrlShortenerService {


	private UrlRepository urlRepository;

	@Autowired
	public UrlShortenerServiceImpl(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}

	@Override
	public  String createShortUrl(LongUrlRequest request) {

		//TODO check the size of the passed long url, it should be longer than configured/declared min

		//TODO check if the long url is already shortened/registered
		//TODO if so then return already mapped short url or return error depending on requirements
		//urlRepository.findByLongUrl(request.getLongUrl());

		String shortUrlIdentifier = getShortUrlIdentifier();
		// todo the collisions of short url (alias) are unlikely but not completely ruled out, retry should solve the issue
		// urlRepository.findByShortUrl(shortUrl); // if found then throw retryable exp
		urlRepository.save(shortUrlIdentifier, request.getLongUrl());
		return shortUrlIdentifier;
	}

	private String getShortUrlIdentifier() {
		return Base64.getEncoder().withoutPadding().encodeToString((System.currentTimeMillis() + "").getBytes());
	}

	@Override
	public String resolveShortUrl(String shortUrl) {
		// todo handle the case when short url is not found, 404 should be returned most likely
		return urlRepository.findByShortUrl(shortUrl);
	}

}
