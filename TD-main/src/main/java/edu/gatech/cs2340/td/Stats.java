package edu.gatech.cs2340.td;

public class Stats {
    private static int finalHealth = 0;
    private static int totalTime = 0;
    private static int numTowers = 0;

    public static int getFinalHealth() {
        return finalHealth;
    }

    public static void setFinalHealth(int i) {
        finalHealth = i;
    }

    public static int getTotalTime() {
        return totalTime;
    }

    public static void setTotalTime(int i) {
        totalTime = i;
    }

    public static int getNumTowers() {
        return GameData.getInstance().getMap().getTowers();
    }

    public static void setNumTowers(int i) {
        numTowers = i;
    }
}
