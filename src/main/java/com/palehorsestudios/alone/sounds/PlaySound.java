package com.palehorsestudios.alone.sounds;

import javax.sound.sampled.*;
import java.io.*;

public class PlaySound {

  public static void playSound(String fileName) {
      try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resources/sounds/"+fileName));
          Clip clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
          e.printStackTrace();
      }
  }
}
