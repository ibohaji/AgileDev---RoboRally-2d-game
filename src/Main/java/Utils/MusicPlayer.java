package Utils;

import App.RoborallyApplication.Views.Menus.MainMenuView;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MusicPlayer {
    private MusicPlayer() {}

    private static final MusicPlayer instance = new MusicPlayer();

    public static MusicPlayer getInstance() {
        return instance;
    }


    private final Clip clip;

    private String currentlyPlaying = null;

    {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void playLoop(String filepath) {
        if (filepath.charAt(0) != '/') filepath = "/" + filepath;
        if (filepath.equals(currentlyPlaying)) return;
        else currentlyPlaying = filepath;
        filepath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "Main" + File.separator + "java" + filepath;
        AudioInputStream ais;
        try {
            ais = AudioSystem.getAudioInputStream(new File(filepath));
            if (clip.isOpen()) clip.close();
            clip.open(ais);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopPlaying() {
        currentlyPlaying = null;
        clip.stop();
    }


}
