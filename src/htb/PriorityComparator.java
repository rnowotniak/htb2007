/*
 * Copyright (C) 2007,2008   Robert Nowotniak <robert@nowotniak.com>
 * 
 */

package htb;

import java.util.Comparator;

/**
 *
 * @author rob
 */
public class PriorityComparator implements Comparator<HTBClass> {

    public int compare(HTBClass c1, HTBClass c2) {
        if (c1.getPrio() > c2.getPrio()) {
            return 1;
        }
        if (c1.getPrio() < c2.getPrio()) {
            return -1;
        }
        return 0;
    }

}
