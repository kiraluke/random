package com.ascending.repository;

import com.ascending.model.Pack;
import com.ascending.ApplicationBootsTrap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootsTrap.class)
public class PackDaoImplTest {
        private Logger logger = LoggerFactory.getLogger(getClass());
        @Autowired
        private PackDao packDao;
        private Pack p5;

    @Before
    public void setUp(){
         p5 = new Pack();
         p5.setTrackingId("2321ssdsd");
         p5.setCategory("fedex pack");
         p5.setDestination("Fairfax");
         packDao.save(p5);
    }
    @After
    public void tearDown(){
        packDao.delete(p5);
    }
    @Test
    public void getPacksTest() {
        List<Pack> packs = packDao.getPacks();
        int expectedNumOfPack = 1;
        assertEquals(expectedNumOfPack, packs.size());
    }
    @Test
    public void updateTest(){
        String trackId = "nn19940525";
        p5.setTrackingId(trackId);
        p5.setCategory("fedex large box");
        p5.setDestination("Chantilly");
        packDao.update(p5);
        assertEquals(trackId, p5.getTrackingId());
    }
    @Test
    public void getPackByTrackingTest(){
        Pack testPack = packDao.getPackByTracking(p5.getTrackingId());
        assertEquals(testPack.getTrackingId(),"2321ssdsd");
    }
}
