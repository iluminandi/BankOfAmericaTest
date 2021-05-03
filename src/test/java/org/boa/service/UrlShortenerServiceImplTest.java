package org.boa.service;

import org.apache.commons.lang3.StringUtils;
import org.boa.TestUtil;
import org.boa.model.LongUrlRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UrlShortenerServiceImplTest {

    private UrlShortenerService urlShortenerService;

    private UrlRepository urlRepository;

    @Before
    public void before() {
        urlRepository = mock(UrlRepository.class);
        urlShortenerService = new UrlShortenerServiceImpl(urlRepository);
    }

    @Test
    public void createShortUrl() {
        String shortUrlActual = urlShortenerService.createShortUrl(new LongUrlRequest(TestUtil.LONG_URL));
        Assert.assertTrue(StringUtils.isNotBlank(shortUrlActual));

        verify(urlRepository, times(1)).save(anyString(), anyString());
    }

    @Test
    public void resolveShortUrl() {
        when(urlRepository.findByShortUrl(TestUtil.SHORT_URL)).thenReturn(TestUtil.LONG_URL);
        String longUrlActual = urlShortenerService.resolveShortUrl(TestUtil.SHORT_URL);
        Assert.assertEquals(TestUtil.LONG_URL, longUrlActual);

        verify(urlRepository, times(1)).findByShortUrl(anyString());

    }
}