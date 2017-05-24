package io.khasang.restaurant.model;

import io.khasang.restaurant.config.HibernateConfig;
import io.khasang.restaurant.config.application.WebConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, HibernateConfig.class})
public class CatTest {
    @Autowired
    private Cat cat;

    @Test
    @Rollback
    public void testCreateTable() {
        Assert.assertEquals("table created", cat.createCatTable());
    }
}
