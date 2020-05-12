package org.jahiduls.sudokuservice.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HelloControllerTest {

    private HelloController helloController;

    @Before
    public void setUp() throws Exception {
        helloController = new HelloController();
    }

    @Test
    public void callToIndexDeliversHelloMessage() {
        Assert.assertEquals("Hello from SpringBoot!", helloController.index());
    }
}
