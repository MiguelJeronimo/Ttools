package com.example.TibiaTools.recyclerview;

public class ItemsArchievementsCharacter {
    String achievementsName;
    String achievementsGrade;
    boolean secret;
    public ItemsArchievementsCharacter(String achievementsName, String achievementsGrade, boolean secret) {
        this.achievementsName = achievementsName;
        this.achievementsGrade = achievementsGrade;
        this.secret = secret;
    }

    public String getAchievementsName() {
        return achievementsName;
    }

    public void setAchievementsName(String achievementsName) {
        this.achievementsName = achievementsName;
    }

    public String getAchievementsGrade() {
        return achievementsGrade;
    }

    public void setAchievementsGrade(String achievementsGrade) {
        this.achievementsGrade = achievementsGrade;
    }

    public boolean isSecret() {
        return secret;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }
}
