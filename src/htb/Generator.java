/*
 * Copyright (C) 2007,2008   Robert Nowotniak <robert@nowotniak.com>
 * 
 */
package htb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rob
 */
public class Generator {

    private Bandwidth upload;
    private Bandwidth download;
    private Bandwidth lan;
    private boolean imq;
    private String outiface;
    private String iniface;
    private List<HTBClass> inclasses;
    private List<HTBClass> outclasses;
    private PrintStream stream;

    public Generator() {
        upload = new Bandwidth("512kbit");
        download = new Bandwidth("1mbit");
        lan = new Bandwidth("100mbit");
        imq = false;
        outiface = "eth1";
        iniface = "br1";
        inclasses = new ArrayList<HTBClass>();
        outclasses = new ArrayList<HTBClass>();
        stream = System.out;
    }

    public void generate() {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/template.txt")));
        while (true) {
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException ex) {
            }
            if (line == null) {
                break;
            }

            line = line.replaceAll("\\$\\(OUT_IFACE\\)", outiface);
            line = line.replaceAll("\\$\\(IN_IFACE\\)", iniface);

            if (line.matches(".*\\$\\(OUT_CLASSES\\).*")) {
                makeClasses(outiface, upload, outclasses, false);
            } else if (line.matches(".*\\$\\(IN_CLASSES\\).*")) {
                makeClasses(iniface, download, inclasses, imq ? false : true);
            } else if (line.matches(".*\\$\\(OUT_RULES\\).*")) {
                makeRules(outiface, outclasses);
            } else if (line.matches(".*\\$\\(IN_RULES\\).*")) {
                makeRules(iniface, inclasses);
            } else {
                stream.println(line);
            }
        }
    }

    public void makeClasses(String iface, Bandwidth band, List<HTBClass> classes, boolean dolan) {
        int lastid = 0;
        int rootid = 1;

        stream.println(String.format("tc qdisc add dev %s root handle 1:0 htb", iface));

        if (dolan) {
            stream.println(String.format("tc class add dev %s parent 1:0 classid 1:1 htb rate %s ceil %s",
                    iface, band.add(lan), band.add(lan)));
            stream.println(String.format("tc class add dev %s parent 1:1 classid 1:2 htb rate %s ceil %s",
                    iface, lan, lan));
            rootid = 3;
            lastid = 2;
        }

        stream.println(String.format("tc class add dev %s parent 1:%d classid 1:%d htb rate %s ceil %s",
                iface, dolan ? 1 : 0, ++lastid, band, band));

        stream.println();

        for (int i = 0; i < classes.size(); i++) {
            HTBClass c = classes.get(i);
            stream.println("# " + c.getName());
            stream.println(String.format("tc class add dev %s parent 1:%d classid 1:%d htb rate %s ceil %s prio %d",
                    iface, rootid, rootid + 1 + i, c.getRate(), c.getCeil(), c.getPrio()));
        }
        stream.println();

        for (int i = 0; i < classes.size(); i++) {
            HTBClass c = classes.get(i);
            stream.println(String.format("tc filter add dev %s protocol ip parent 1:0 handle %d fw flowid 1:%d # %s",
                    iface, rootid + 1 + i, rootid + 1 + i, c.getName()));
            c.setMark(rootid + 1 + i);
        }
        stream.println();

        for (int i = 0; i < classes.size(); i++) {
            HTBClass c = classes.get(i);
            stream.println(String.format("tc qdisc add dev %s parent 1:%s handle %d:0 sfq perturb 10 # %s",
                    iface, rootid + 1 + i, rootid + 1 + i, c.getName()));
        }

    }

    private void makeRules(String iface, List<HTBClass> classes) {
        for (HTBClass c : classes) {
            stream.println(String.format(
                    "## %s", c.getName()));
            stream.println(String.format(
                    "#iptables -t mangle -A POSTROUTING -o %s -p udp --dport ... -j MARK --set-mark %d",
                    iface, c.getMark()));
            stream.println(String.format(
                    "#iptables -t mangle -A POSTROUTING -o %s -p udp --dport ... -j RETURN",
                    iface));
            stream.println();
        }
    }

    public Bandwidth getDownload() {
        return download;
    }

    public void setDownload(Bandwidth download) {
        this.download = download;
    }

    public boolean isImq() {
        return imq;
    }

    public void setImq(boolean imq) {
        this.imq = imq;
    }

    public List<HTBClass> getInclasses() {
        return inclasses;
    }

    public void setInclasses(List<HTBClass> inclasses) {
        this.inclasses = inclasses;
    }

    public String getIniface() {
        return iniface;
    }

    public void setIniface(String iniface) {
        this.iniface = iniface;
    }

    public Bandwidth getLan() {
        return lan;
    }

    public void setLan(Bandwidth lan) {
        this.lan = lan;
    }

    public List<HTBClass> getOutclasses() {
        return outclasses;
    }

    public void setOutclasses(List<HTBClass> outclasses) {
        this.outclasses = outclasses;
    }

    public String getOutiface() {
        return outiface;
    }

    public void setOutiface(String outiface) {
        this.outiface = outiface;
    }

    public Bandwidth getUpload() {
        return upload;
    }

    public void setUpload(Bandwidth upload) {
        this.upload = upload;
    }

    public PrintStream getStream() {
        return stream;
    }

    public void setStream(PrintStream stream) {
        this.stream = stream;
    }
}
