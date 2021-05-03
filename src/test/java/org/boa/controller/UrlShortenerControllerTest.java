package org.boa.controller;

import org.boa.TestUtil;
import org.boa.model.LongUrlRequest;
import org.boa.service.UrlShortenerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UrlShortenerControllerTest {

    private UrlShortenerController urlShortenerController;

    private UrlShortenerService urlShortenerService;

    @Before
    public void setUp() throws Exception {
        urlShortenerService = mock(UrlShortenerService.class);
        urlShortenerController = new UrlShortenerController(urlShortenerService);

        when(urlShortenerService.createShortUrl(any())).thenReturn(TestUtil.SHORT_URL);
        when(urlShortenerService.resolveShortUrl(any())).thenReturn(TestUtil.LONG_URL);
    }

    @Test
    public void createShortUrl() {
        ResponseEntity<String> responseEntity = urlShortenerController.createShortUrl(new LongUrlRequest(TestUtil.LONG_URL));
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(TestUtil.SHORT_URL, responseEntity.getBody());

        verify(urlShortenerService, times(1)).createShortUrl(any());

    }

    @Test
    public void resolveShortUrl() {
        ResponseEntity<Void> responseEntity = urlShortenerController.resolveShortUrl(TestUtil.SHORT_URL);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());

        verify(urlShortenerService, times(1)).resolveShortUrl(anyString());

    }
}