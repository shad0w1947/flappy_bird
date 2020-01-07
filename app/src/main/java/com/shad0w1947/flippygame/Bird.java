package com.shad0w1947.flippygame;

public class Bird {
    private  int birdX,birdY,currentFrame,maxFrame,velocity;
    public Bird(){

        birdX=AppConstants.SCREEN_WIDTH/2-AppConstants.getBitmapBank().getBirdWidth()/2;
        birdY=AppConstants.SCREEN_HEIGHT/2-AppConstants.getBitmapBank().getBirdHeight()/2;
        currentFrame=0;
        maxFrame=3;
        velocity=0;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getBirdX() {
        return birdX;
    }

    public int getBirdY() {
        return birdY;
    }

    public void setBirdX(int birdX) {
        this.birdX = birdX;
    }

    public void setBirdY(int birdY) {
        this.birdY = birdY;
    }

    public int getMaxFrame() {
        return maxFrame;
    }
}
