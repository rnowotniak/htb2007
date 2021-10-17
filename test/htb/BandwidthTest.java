/*
 * Copyright (C) 2007,2008   Robert Nowotniak <robert@nowotniak.com>
 * 
 */

package htb;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rob
 */
public class BandwidthTest {

    Bandwidth b;
    
    public BandwidthTest() {
        b = new Bandwidth(0);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    
    /**
     * Test of toString method, of class Bandwidth.
     */
    @Test
    public void _toString() {
        System.out.println("toString");
        System.out.println(new Bandwidth(95).toString());
        System.out.println(new Bandwidth(64).toString());
        System.out.println(new Bandwidth(1024).toString());
        System.out.println(new Bandwidth(1024).toString());
        System.out.println(new Bandwidth(32).toString());
        System.out.println(new Bandwidth("94 kbps").toString());
        System.out.println(new Bandwidth("2.5 MB/s").toString());
        System.out.println(new Bandwidth("93.75 KB/s").toString());
        System.out.println(new Bandwidth("100 mbit").toString());
    }

}