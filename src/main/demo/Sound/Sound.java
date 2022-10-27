package demo.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound implements SoundPath{
    Clip clip;
    public Sound() {
        try {
            clip = AudioSystem.getClip();
        } catch (Exception e) {
            System.out.println("Cannot load URL from Sound");
        }

    }

    public void setFile(String s) {
        try {
            clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(new File(s));

            clip.open(audioInputStream);
        } catch (Exception e) {
            System.out.println("Cannot set File " + s + " from Sound");
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
        clip.close();
        clip.drain();
    }
}
