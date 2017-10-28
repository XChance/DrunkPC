package main;

//Written by Xian Chance - 2017
//Drunk PC Prank Program

public class DrunkPC {
    public static DrunkPC main;
    public Threader drunkMouse;
    public Threader drunkKeys;

    //both threaders of keys and mouse are instantiated
    public DrunkPC() {
        drunkMouse = new Threader(0);
        drunkKeys = new Threader(1);
    }

    //main method runs the program
    public static void main(String[] args){
        main = new DrunkPC();
    }
}