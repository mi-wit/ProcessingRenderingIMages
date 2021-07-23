package util;

import render.Main;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * Save images to file.
 * Can output high resolution images
 */
public class FileSaver extends PApplet {

    Main sketch;
    private final int CONFIG_SCALE_FACTOR; // multiplies file resolution
    private int seed = 0;

    public FileSaver(int scaleFactor, Main sketch) {
        CONFIG_SCALE_FACTOR = scaleFactor;
        this.seed = millis();
        this.sketch = sketch;
    }


    public void seededRender() {
        sketch.randomSeed(seed);
        sketch.noiseSeed(seed);
        sketch.render();
    }

    public void keyPressed(char key) {
        if (key == 's') {
            saveLowRes();
        } else if (key == 'h') {
            saveHighRes(CONFIG_SCALE_FACTOR);
        } else {
            seed = millis();
            seededRender();
        }
    }

    private void saveHighRes(int scaleFactor) {
        PGraphics hires = sketch.createGraphics(
                sketch.width * scaleFactor,
                sketch.height * scaleFactor,
                sketch.JAVA2D);
        println("Generating high-resolution image...");

        sketch.beginRecord(hires);
        hires.scale(scaleFactor);
        seededRender();
        sketch.endRecord();

        hires.save("Rendered\\" + seed + "-highres.png");
        println("Finished");
    }

    private void saveLowRes() {
        println("Saving low-resolution image...");
        sketch.save("Rendered\\" + seed + "-lowres.png");
        println("Finished");
    }

}
