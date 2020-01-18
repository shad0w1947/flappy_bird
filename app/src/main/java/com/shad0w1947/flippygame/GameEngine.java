package com.shad0w1947.flippygame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {
    BackgroundImage backgroundImage;
    Bird bird;
     public static  int gameState;
     ArrayList<Tube> tubes;
     private Random random;
     int score;
     int scoringTube;
     Paint scorePaint;


    public GameEngine(){
        backgroundImage=new BackgroundImage();
        bird=new Bird();
        //0=Not start
        //1=Playing
        //2=GameOver
        gameState=0;

        Log.e("tube","nn");
        tubes=new ArrayList<>();
        random=new Random();
        for(int i=0;i<AppConstants.numberOfTubes;i++){
            int tubeX=AppConstants.SCREEN_WIDTH+i*AppConstants.distanceBetweenTubes;
            int topTubeOffsetY=AppConstants.minTubeOffSetY+
                    random.nextInt(AppConstants.maxTubeOffSetY-AppConstants.minTubeOffSetY+1);
            Tube tube=new Tube(tubeX,topTubeOffsetY);
            tubes.add(tube);
            Log.e("tube",tubes.size()+"");
        }
        score=0;
        scoringTube=0;
        scorePaint=new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(100);
        scorePaint.setTextAlign(Paint.Align.LEFT);

    }
    public void updateAndDrawTubes(Canvas canvas) {
        if (gameState == 1) {
            if(tubes.get(scoringTube).getTubeX()<bird.getBirdX()+AppConstants.getBitmapBank().getBirdWidth()
            &&(tubes.get(scoringTube).getTopTubeOffSetY()>bird.getBirdY()||
                    tubes.get(scoringTube).getBottomTubeY()<(bird.getBirdY()+
                            AppConstants.getBitmapBank().getBirdHeight()))){
                //Game over
                gameState=2;
                Log.i("over","its over");
                AppConstants.getSoundBank().playHit();
                Intent intent=new Intent(AppConstants.context,GameOver.class);
                intent.putExtra("score",score);
                 AppConstants.context.startActivity(intent);
                ((Activity)(AppConstants.context)).finish();

            }
            if (tubes.get(scoringTube).getTubeX()<bird.getBirdX()-AppConstants.getBitmapBank().getTubeWidth()) {
                score++;
                scoringTube++;
                if(scoringTube>AppConstants.numberOfTubes-1){
                    scoringTube=0;
                }
                AppConstants.getSoundBank().playPoint();
            }
            //AppConstants.getSoundBank().playPoint();
        for (int i = 0; i < AppConstants.numberOfTubes; i++) {
            if (tubes.get(i).getTubeX() < -AppConstants.getBitmapBank().getTubeWidth()) {
                tubes.get(i).setTubeX(tubes.get(i).getTubeX() +
                        AppConstants.numberOfTubes * AppConstants.distanceBetweenTubes);
                int topTubeOffsetY = AppConstants.minTubeOffSetY +
                        random.nextInt(AppConstants.maxTubeOffSetY - AppConstants.minTubeOffSetY + 1);
                tubes.get(i).setTopTubeOffSetY(topTubeOffsetY);
                tubes.get(i).setTubeColor();
            }
            tubes.get(i).setTubeX(tubes.get(i).getTubeX() - AppConstants.tubeVelocity);
            if (tubes.get(i).getTubeColor() == 0) {
                canvas.drawBitmap(AppConstants.getBitmapBank().getTubeTop(), tubes.get(i).getTubeX(), tubes.get(i).getTopTubeY(), null);
                canvas.drawBitmap(AppConstants.getBitmapBank().getTubeBottom(), tubes.get(i).getTubeX(), tubes.get(i).getBottomTubeY(), null);
            } else {
                canvas.drawBitmap(AppConstants.getBitmapBank().getRedTubeTop(), tubes.get(i).getTubeX(), tubes.get(i).getTopTubeY(), null);
                canvas.drawBitmap(AppConstants.getBitmapBank().getRedTubeBottom(), tubes.get(i).getTubeX(), tubes.get(i).getBottomTubeY(), null);
            }
        }
        canvas.drawText("Pt: " + score, 0, 110, scorePaint);
    }

//    public void updateAndDrawTubes(Canvas canvas){
//        if(gameState==1){
//            for(int i=0;i<AppConstants.numberOfTubes;i++){
//                //if(i>=tubes.size())continue;
//                if(tubes.get(i).getTubeX()<-AppConstants.getBitmapBank().getTubeWidth()){
//                    tubes.get(i).setTubeX(tubes.get(i).getTubeX()+
//                            AppConstants.numberOfTubes*AppConstants.distanceBetweenTubes);
//                    int topTubeOffsetY=AppConstants.minTubeOffSetY+
//                            random.nextInt(AppConstants.maxTubeOffSetY-AppConstants.minTubeOffSetY+1);
//                    tubes.get(i).setTopTubeOffSetY(topTubeOffsetY);
//                    tubes.get(i).setTubeX(tubes.get(i).getTubeX()-AppConstants.tubeVelocity);
//                    canvas.drawBitmap(AppConstants.getBitmapBank().getTubeTop(),tubes.get(i).getTubeX(),tubes.get(i).getTopTubeY(),null);
//                    canvas.drawBitmap(AppConstants.getBitmapBank().getTubeBottom(),tubes.get(i).getTubeX(),tubes.get(i).getBottomTubeY(),null);
//                }
//            }
//        }
    }
    public void updateAndDrawBackgroundImage(Canvas canvas){
        backgroundImage.setBackgroundImageX(backgroundImage.getBackgroundImageX()-backgroundImage.getBackgroundImageVelocity());
        if(backgroundImage.getBackgroundImageX()<-AppConstants.getBitmapBank().getBackgroundWidth()){
            backgroundImage.setBackgroundImageX(0);
        }
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(),backgroundImage.getBackgroundImageX(),backgroundImage.getBackgroundImageY(),null);
        if(backgroundImage.getBackgroundImageX()<-(AppConstants.getBitmapBank().getBackgroundWidth()-AppConstants.SCREEN_WIDTH)){
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(),backgroundImage.getBackgroundImageX()+AppConstants.getBitmapBank().getBackgroundWidth(),backgroundImage.getBackgroundImageY(),null);
        }
    }

    public void  updateAndDrawBird(Canvas canvas){
        if(gameState==1)
        if(bird.getBirdY()<(AppConstants.SCREEN_HEIGHT-AppConstants.getBitmapBank().getBirdHeight())||bird.getVelocity()<0){
            bird.setVelocity(bird.getVelocity()+AppConstants.gravity);
            bird.setBirdY(bird.getBirdY()+bird.getVelocity());
        }


        int currentFrame=bird.getCurrentFrame();
        canvas.drawBitmap(AppConstants.getBitmapBank().getBird(currentFrame),bird.getBirdX(),bird.getBirdY(),null);
        currentFrame++;
        if(currentFrame>bird.getMaxFrame())
            currentFrame=0;
        bird.setCurrentFrame(currentFrame);
    }
}
