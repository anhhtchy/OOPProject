package com.simplerpg.game.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.io.*;
import java.util.Scanner;

public class AnimationController {
    private AnimationState[] animationStates;
    private int currentStateIndex = 0;
    private AnimationState currentState;
    private int defaultStateIndex = 0;
    private float elapsedTime = 0f;

    public AnimationController(String pathName) throws IOException{
        BufferedReader bufferIn = null;
        int length = 0;
        String[] stateNames;
        String[][] spritePaths;
        boolean[] loops;
        try {
            bufferIn = new BufferedReader(new FileReader(pathName));

            String strLine = bufferIn.readLine();
            length = Integer.parseInt(strLine);
            stateNames = new String[length];
            spritePaths = new String[length][];
            loops = new boolean[length];
            for(int i = 0; i < length; i++){
                strLine = bufferIn.readLine();
                Scanner sc = new Scanner(strLine);
                int spriteSize = sc.nextInt();
                stateNames[i] = sc.next();
                loops[i] = sc.next().equals("true");
                spritePaths[i] = new String[spriteSize];
                for(int j = 0; j < spriteSize; j++){
                    spritePaths[i][j] = bufferIn.readLine();
                }
            }
            bufferIn.close();
            //
            animationStates = new AnimationState[length];
            for(int i = 0; i < length; i++){
                animationStates[i] = new AnimationState(stateNames[i], spritePaths[i], 0.1f, loops[i]);
            }
            currentState = animationStates[defaultStateIndex];
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                bufferIn.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public AnimationController(AnimationState[] animationStates) {
        this.animationStates = animationStates;
    }

    public void play(int index){

    }

    public void play(String name){
        for(int i = 0; i < animationStates.length; i++){
            if(animationStates[i].getName().equals(name)){
                elapsedTime = 0f;
                currentStateIndex = i;
                currentState = animationStates[i];
                break;
            }
        }
    }

    public Sprite getKeyFrame(){
        elapsedTime += Gdx.graphics.getDeltaTime();
        return (Sprite) currentState.getAnimation().getKeyFrame(elapsedTime, currentState.isLoop());
    }

    public AnimationState[] getAnimationStates() {
        return animationStates;
    }

    public void setAnimationStates(AnimationState[] animationStates) {
        this.animationStates = animationStates;
    }

    public int getCurrentStateIndex() {
        return currentStateIndex;
    }

    public void setCurrentStateIndex(int currentStateIndex) {
        this.currentStateIndex = currentStateIndex;
    }

    public AnimationState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(AnimationState currentState) {
        this.currentState = currentState;
    }
}
