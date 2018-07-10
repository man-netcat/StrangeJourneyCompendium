package com.rickteuthof.strangejourneycompendium;

import java.util.ArrayList;

class Attack {
    private String target;

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    private String ailment;

    public String getAilment() {
        return this.ailment;
    }

    public void setAilment(String ailment) {
        this.ailment = ailment;
    }

    private String hits;

    public String getHits() {
        return this.hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    private String element;

    public String getElement() {
        return this.element;
    }

    public void setElement(String element) {
        this.element = element;
    }
}

class Reslvls {
    private int fir;

    public int getFir() {
        return this.fir;
    }

    public void setFir(int fir) {
        this.fir = fir;
    }

    private int ele;

    public int getEle() {
        return this.ele;
    }

    public void setEle(int ele) {
        this.ele = ele;
    }

    private int gun;

    public int getGun() {
        return this.gun;
    }

    public void setGun(int gun) {
        this.gun = gun;
    }

    private int ice;

    public int getIce() {
        return this.ice;
    }

    public void setIce(int ice) {
        this.ice = ice;
    }

    private int win;

    public int getWin() {
        return this.win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    private int phy;

    public int getPhy() {
        return this.phy;
    }

    public void setPhy(int phy) {
        this.phy = phy;
    }

    private int cur;

    public int getCur() {
        return this.cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }

    private int exp;

    public int getExp() {
        return this.exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}

public class Demon {
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String align;

    public String getAlign() {
        return this.align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    private Attack attack;

    public Attack getAttack() {
        return this.attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    private int code;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private String inherits;

    public String getInherits() {
        return this.inherits;
    }

    public void setInherits(String inherits) {
        this.inherits = inherits;
    }

    private double lvl;

    public double getLvl() {
        return this.lvl;
    }

    public void setLvl(double lvl) {
        this.lvl = lvl;
    }

    private int pcoeff;

    public int getPcoeff() {
        return this.pcoeff;
    }

    public void setPcoeff(int pcoeff) {
        this.pcoeff = pcoeff;
    }

    private String race;

    public String getRace() {
        return this.race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    private String resists;

    public String getResists() {
        return this.resists;
    }

    public void setResists(String resists) {
        this.resists = resists;
    }

    private ArrayList<String> skills;

    public ArrayList<String> getSkills() {
        return this.skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    private ArrayList<String> source;

    public ArrayList<String> getSource() {
        return this.source;
    }

    public void setSource(ArrayList<String> source) {
        this.source = source;
    }

    private ArrayList<Integer> stats;

    public ArrayList<Integer> getStats() {
        return this.stats;
    }

    public void setStats(ArrayList<Integer> stats) {
        this.stats = stats;
    }

    private String ailments;

    public String getAilments() {
        return this.ailments;
    }

    public void setAilments(String ailments) {
        this.ailments = ailments;
    }

    private Reslvls reslvls;

    public Reslvls getReslvls() {
        return this.reslvls;
    }

    public void setReslvls(Reslvls reslvls) {
        this.reslvls = reslvls;
    }

    private Double hpmod;

    public Double getHpmod() {
        return this.hpmod;
    }

    public void setHpmod(Double hpmod) {
        this.hpmod = hpmod;
    }
}