package org.boa.controller;

import lombok.extern.slf4j.Slf4j;
import org.boa.model.LongUrlRequest;
import org.boa.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * A rest controller that handles requests that shorten URL
 * as well as resolve the shortened url and redirect accordingly
 *
 * @author lukas
 * 
 */
@RestController
@Slf4j
public class UrlShortenerController {

	private UrlShortenerService urlShortenerService;

	@Autowired
	public UrlShortenerController(UrlShortenerService urlShortenerService) {
		this.urlShortenerService = urlShortenerService;
	}

	/** 
	 * shortens the long url
	 * 
	 * @param longUrlRequest url to be shortened
	 * @return responds with 200 if OK as well as returns the short url
	 */
	@RequestMapping(value = "/createShortUrl", method = RequestMethod.POST)
	public ResponseEntity<String> createShortUrl(@RequestBody LongUrlRequest longUrlRequest) {
		log.info("createShortUrl called with: " + longUrlRequest);
		final String shortUrl = urlShortenerService.createShortUrl(longUrlRequest);
		log.info("createShortUrl generated short URL: " + shortUrl);
		return new ResponseEntity<>(shortUrl, HttpStatus.OK);
	}

	/**
	 * resolves the short url and redirects accordingly
	 *
	 * @param shortUrl url to be resolved and redirected to
	 * @return redirects (302) into resolved url
	 */
	@RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET)
	public ResponseEntity<Void> resolveShortUrl(@PathVariable("shortUrl") String shortUrl) {
		// TODO add validation to check if passed short url is NOT empty

		log.info("resolveShortUrl called with short URL: " + shortUrl);
		final String longUrl = urlShortenerService.resolveShortUrl(shortUrl);
		log.info("resolveShortUrl returned long URL: " + longUrl);

		//TODO  add error handling, if corresponding long URL not found/empty then throw an exception that would map into 404 (not found)

		//lets redirect to 302 found
		return ResponseEntity.status(HttpStatus.FOUND)
				.location(URI.create(longUrl))
				.build();
	}

}
