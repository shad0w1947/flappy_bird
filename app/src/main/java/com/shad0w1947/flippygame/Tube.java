package com.shad0w1947.flippygame;

import java.util.Random;

public class Tube {
    private int tubeX,topTubeOffSetY;
    private  Random random;
    public Tube(int tubeX,int topTubeOffSetY){
        this.tubeX=tubeX;
        this.topTubeOffSetY=topTubeOffSetY;
        random=new Random();
    }

    public int getTopTubeOffSetY() {
        return topTubeOffSetY;
    }

    public int getTubeX() {
        return tubeX;
    }
    public int getTopTubeY() {
        return topTubeOffSetY-AppConstants.getBitmapBank().getTubeHeight();
    }
    public int getBottomTubeY() {
        return topTubeOffSetY+AppConstants.gapBetweenTopAndBottomTubes;
    }

    public void setTubeX(int tubeX) {
        this.tubeX = tubeX;
    }

    public void setTopTubeOffSetY(int topTubeOffSetY) {
        this.topTubeOffSetY = topTubeOffSetY;
    }

}
