/*
 * Copyright (C) 2007,2008   Robert Nowotniak <robert@nowotniak.com>
 * 
 */

package htb;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rob
 */
public class GeneratorTest {

    public GeneratorTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of generate method, of class Generator.
     */
    @Test
    public void generate() {
        System.out.println("generate");
        Generator gen = new Generator();
        
        gen.setIniface("in0");
        gen.setOutiface("out0");
        
        gen.setUpload(new Bandwidth("1 mbit"));
        gen.setDownload(new Bandwidth("1 mbit"));
        gen.setLan(new Bandwidth("100 mbit"));
        
        List<HTBClass> outclasses = new ArrayList<HTBClass>();
        outclasses.add(new HTBClass("q3", 50, 90, 1));
        outclasses.add(new HTBClass("interactive", 20, 20, 2));
        gen.setOutclasses(outclasses);
        
        List<HTBClass> inclasses = new ArrayList<HTBClass>();
        inclasses.add(new HTBClass("q3", 50, 90, 1));
        inclasses.add(new HTBClass("interactive", 20, 20, 2));
        inclasses.add(new HTBClass("p2p", 4, 90, 5));
        gen.setInclasses(inclasses);
        
        gen.setImq(true);
        
        gen.generate();
    }

    /**
     * Test of generate method, of class Generator.
     */
    //@Test
    public void makeClasses() {
        System.out.println("makeClasses");
        Generator gen = new Generator();
        //gen.setUpload(new Bandwidth(bandwidth))
        gen.setLan(new Bandwidth("100 mbit"));
        List<HTBClass> classes = new ArrayList<HTBClass>();
        classes.add(new HTBClass("q3", 50, 90, 1));
        classes.add(new HTBClass("interactive", 20, 20, 2));
        gen.makeClasses("out0", new Bandwidth(94), classes, false);
        System.out.println("---");
        classes.add(new HTBClass("peer-to-peer", 4, 70, 6));
        gen.makeClasses("in0", new Bandwidth("1MB/s"), classes, true);
    }
    
}



