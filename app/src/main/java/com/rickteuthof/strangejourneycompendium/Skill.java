package com.rickteuthof.strangejourneycompendium;

public class Skill
{
    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private int code;

    public int getCode() { return this.code; }

    public void setCode(int code) { this.code = code; }

    private int cost;

    public int getCost() { return this.cost; }

    public void setCost(int cost) { this.cost = cost; }

    private String effect;

    public String getEffect() { return this.effect; }

    public void setEffect(String effect) { this.effect = effect; }

    private String element;

    public String getElement() { return this.element; }

    public void setElement(String element) { this.element = element; }

    private int rank;

    public int getRank() { return this.rank; }

    public void setRank(int rank) { this.rank = rank; }

    private Integer accuracy;

    public Integer getAccuracy() { return this.accuracy; }

    public void setAccuracy(Integer accuracy) { this.accuracy = accuracy; }

    private Integer power;

    public Integer getPower() { return this.power; }

    public void setPower(Integer power) { this.power = power; }

    private String inherit;

    public String getInherit() { return this.inherit; }

    public void setInherit(String inherit) { this.inherit = inherit; }
}