package main;

import java.awt.*;
import java.util.Random;

public class Threader implements Runnable {
    //variables
    private int type;
    private int typeCode;
    private int keyCode;
    private int sleepTime;
    private Robot robot;
    private Random rd;
    private Thread thread;

    //constructor
    public Threader(int type){
        //robot will be called to make the mouse & key movements
        try{ robot = new Robot(); }
        catch (AWTException e) { e.printStackTrace(); }

        //type differentiates between mouse threader or key threader
        this.type = type;

        //different intervals between activation for mouse vs keys
        if(type == 0){
            sleepTime = 80;
        }else{
            sleepTime = 200;
        }

        rd = new Random();
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            switch (type) {
                case 0:
                    mouseThread();
                    break;
                case 1:
                    keyThread();
                    break;
            }

            try {
                thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //mouse will make small random movements at random intervals around 500 to 900 miliseconds
    public void mouseThread(){
        //random time is chosen, program waits that time and then executes the movement
        int temp = rd.nextInt(333)+500;

        try {
            thread.sleep(temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //storing the mouse's position
        int mouseX = MouseInfo.getPointerInfo().getLocation().x;
        int mouseY = MouseInfo.getPointerInfo().getLocation().y;
        //moving the mouse based on small random vaues added to the current position
        robot.mouseMove(mouseX + rd.nextInt(10)-5, mouseY + rd.nextInt(10)-5);
    }

    //keys will randomly be typed at random intervals between 2.5 to 32.5 seconds
    public void keyThread(){
        //random time is chosen, program waits until that time is up then executes the key press
        int temp = rd.nextInt(30000)+2500;

        try {
            thread.sleep(temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pickKey();
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
    }

    //picks whether or not the random key the keyboard will type is uppercase or lowercase
    public void pickKey(){
        typeCode = rd.nextInt(2);
        letterPicker(typeCode);
    }

    //the letter for the keyboard is chosen
    public void letterPicker(int typeCode){
        if(typeCode == 0){
            keyCode = rd.nextInt(10)+48;
        }else if(typeCode == 1){
            keyCode = rd.nextInt(26)+65;
        }
    }

}
