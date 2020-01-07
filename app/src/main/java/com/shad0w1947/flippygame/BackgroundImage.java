package com.shad0w1947.flippygame;

public class BackgroundImage {
    private int backgroundImageX,backgroundImageY,backgroundImageVelocity;

    public BackgroundImage(){
        backgroundImageX=0;
        backgroundImageY=0;
        backgroundImageVelocity=5;
    }

    public int getBackgroundImageVelocity() {
        return backgroundImageVelocity;
    }

    public int getBackgroundImageX() {
        return backgroundImageX;
    }

    public int getBackgroundImageY() {
        return backgroundImageY;
    }

    public void setBackgroundImageVelocity(int backgroundImageVelocity) {
        this.backgroundImageVelocity = backgroundImageVelocity;
    }

    public void setBackgroundImageX(int backgroundImageX) {
        this.backgroundImageX = backgroundImageX;
    }

    public void setBackgroundImageY(int backgroundImageY) {
        this.backgroundImageY = backgroundImageY;
    }
}
