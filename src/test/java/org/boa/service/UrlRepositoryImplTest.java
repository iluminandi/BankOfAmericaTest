package org.boa.service;

import org.boa.TestUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  a unit test class for {@link org.boa.service.UrlRepositoryImpl}
 * @author lukas
 */
public class UrlRepositoryImplTest {


    private UrlRepositoryImpl urlRepository;

    @Before
    public void before() {
        urlRepository = new UrlRepositoryImpl();
    }

    @Test
    public void save() {
        urlRepository.save(TestUtil.SHORT_URL, TestUtil.LONG_URL);
        Assert.assertEquals(TestUtil.LONG_URL, urlRepository.getUrlStorage().get(TestUtil.SHORT_URL));
    }

    @Test
    public void findByLongUrl() {
        urlRepository.getUrlStorage().put(TestUtil.SHORT_URL, TestUtil.LONG_URL);
        String longUrlActual = urlRepository.findByLongUrl(TestUtil.LONG_URL);
        Assert.assertEquals(TestUtil.LONG_URL, longUrlActual);
    }

    @Test
    public void findByShortUrl() {
        urlRepository.getUrlStorage().put(TestUtil.SHORT_URL, TestUtil.LONG_URL);
        String shortUrlActual = urlRepository.findByShortUrl(TestUtil.SHORT_URL);
        Assert.assertEquals(TestUtil.LONG_URL, shortUrlActual);
    }
}