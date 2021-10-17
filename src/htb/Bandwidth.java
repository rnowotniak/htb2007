/*
 * Copyright (C) 2007,2008   Robert Nowotniak <robert@nowotniak.com>
 * 
 */
package htb;

import javax.swing.JOptionPane;

/**
 *
 * @author rob
 */
public class Bandwidth {

    /*
     * 1 KB = 1024 B
     */
    
    // internal unit is: b/s (bytes per second)
    private int bandwidth;

    /*
     * default:  KB/s
     * allowed units:  KB, KB/s, KBps,   kbit,
     *                 MB, MB/s, MBps,   mbit
     * allowed formats:
     *     <float>
     *     <float> <unit>
     */
    public void parse(String str) {
        try {
            str = str.toLowerCase();
            float val = Float.parseFloat(
                    str.replaceAll("kb/s|kbps|mb/s|mbps|kbit|mbit|kb|mb", ""));
            if (!str.matches(".*[a-zA-Z].*")) {
                // no unit (KB/s default)
                bandwidth = (int) (1024.0f * val);
            } else if (str.matches(".*kbit.*")) {
                bandwidth = (int) (1024.0f * val / 8.0f);
            } else if (str.matches(".*mbit.*")) {
                bandwidth = (int) (1024.0f * 1024.0f * val / 8.0f);
            } else if (str.matches(".*(kb|kb/s|kbps).*")) {
                bandwidth = (int) (1024.0f * val);
            } else if (str.matches(".*(mb|mb/s|mbps).*")) {
                bandwidth = (int) (1024.0f * 1024.0f * val);
            } else {
            // throw exception
            }
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null,
                    "Wrong format of bandwidth!", "Error", JOptionPane.ERROR_MESSAGE);
            bandwidth = 0;
        }
    }

    public Bandwidth(double bandwidth) {
        this.bandwidth = (int) (1024.0f * bandwidth);
    }

    public Bandwidth(String bandwidth) {
        parse(bandwidth);

    }

    public Bandwidth add(Bandwidth b2) {
        Bandwidth result = new Bandwidth("0");
        result.bandwidth = this.bandwidth + b2.bandwidth;
        return result;
    }
    
    public int getBps() {
        return bandwidth;
    }
    
    public void setBps(int bps) {
        bandwidth = bps;
    }
    
    @Override
    public String toString() {
        int val = bandwidth;
//        if (val % 1024 != 0) {
//            return ((Integer)val).toString() + "Bps";
//        }
        val /= 1024;
        if (val == 0 || val % 1024 != 0) {
            return ((Integer)val).toString() + "KBps";
        }
        val /= 1024;
        return ((Integer)val).toString() + "MBps";
    }
}
