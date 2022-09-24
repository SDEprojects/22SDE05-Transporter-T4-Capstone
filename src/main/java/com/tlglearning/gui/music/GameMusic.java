package com.tlglearning.gui.music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import java.net.URL;

public class GameMusic {

    //Sound sound = new Sound(); //Instantiate this somewhere in another panel.

    Clip clip;
    URL soundURL[] = new URL[30];

    public GameMusic(){

        soundURL[0] = getClass().getResource("/Users/lakitgai/Desktop/Desktop_Folder/22SDE05-Transporter-T4-Capstone/src/main/resources/music/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURL[2] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURL[3] = getClass().getResource("/sound/BlueBoyAdventure.wav");
    }

    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch(Exception e) {
        }
    }

    public void play() {

        clip.start();
    }

    public void loop() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
