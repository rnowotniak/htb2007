/*
 * Copyright (C) 2007,2008   Robert Nowotniak <robert@nowotniak.com>
 * 
 */
package htb;

/**
 *
 * @author rob
 */
public class HTBClass {

    private String name;
    private Bandwidth rate;
    private Bandwidth ceil;
    private int prio;
    private int mark;
    private float percent;

    public HTBClass(String name, float rate, float ceil, int prio) {
        this(name, new Bandwidth(rate), new Bandwidth(ceil), prio);
    }

    public HTBClass(String name, Bandwidth rate, Bandwidth ceil, int prio) {
        this.name = name;
        this.rate = rate;
        this.ceil = ceil;
        this.prio = prio;
    }

    public HTBClass() {
        this("", new Bandwidth(0), new Bandwidth(0), 1);
    }

    public Bandwidth getCeil() {
        return ceil;
    }

    public void setCeil(Bandwidth ceil) {
        this.ceil = ceil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrio() {
        return prio;
    }

    public void setPrio(int prio) {
        this.prio = prio;
    }

    public Bandwidth getRate() {
        return rate;
    }

    public void setRate(Bandwidth rate) {
        this.rate = rate;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
