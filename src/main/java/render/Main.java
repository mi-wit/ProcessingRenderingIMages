package render;

import processing.core.PApplet;
import util.FileSaver;


public class Main extends PApplet {

    FileSaver fileSaver = new FileSaver(3, this);
    int scale = 250;
    int[] screenRatio = {4, 3};

    @Override
    public void settings() {
        size(screenRatio[0] * scale, screenRatio[1] * scale);
//        fullScreen();
        smooth(2);
    }

    @Override
    public void setup() {
        fileSaver.seededRender();
    }

    public void render() {
        background(200);
        ellipse(random(width), random(height), 20,20);
    }



    
    @Override
    public void keyPressed() {
        fileSaver.keyPressed(key);
    }
    @Override
    public void draw() {
        // wait for key input
    }

    public static void main(String[] args) {
        String[] processingArgs = {Main.class.getName()};
        Main mySketch = new Main();

        PApplet.runSketch(processingArgs, mySketch);
    }
}
