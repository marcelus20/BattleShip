package com.jetbrains.players;


public class Player{


    public final String name;
    public final Integer age;
    public final String e_mail;
    private Integer hitCounter;
    private Integer missCounter;
    private Integer attempts;


    public Player(String name, Integer age, String e_mail) {
        this.name = name;
        this.age = age;
        this.e_mail = e_mail;
        this.hitCounter = 0;
        this.missCounter = 0;
        this.attempts = 0;
    }

    public Integer[] choosesCoordenate(final Integer row, final Integer col){
        this.attempts++;
        return new Integer[]{row, col};
    }

    public void incrementHitCounter() {
        this.hitCounter++;
    }

    public void incrementMissCounter() {
        this.missCounter++;
    }

    public void incrementAttempts() {
        this.attempts++;
    }

    public Integer getHitCounter() {
        return hitCounter;
    }

    public Integer getMissCounter() {
        return missCounter;
    }

    public Integer getAttempts() {
        return attempts;
    }
}