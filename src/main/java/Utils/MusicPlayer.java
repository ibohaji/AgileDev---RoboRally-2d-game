package Utils;

import App.RoborallyApplication.RoboRally;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Random;

public class MusicPlayer {

    private MusicPlayer() {}
    private SourceDataLine sourceDataLine;
    private static final MusicPlayer instance = new MusicPlayer();
    private final byte[] EmptyBuffer = {0};
    public static MusicPlayer getInstance() {
        return instance;
    }
    private static final int BUFFER_SIZE = 4096;
    private AudioInputStream ais;
    private boolean vFlag = false;

    private InputStream findfile(String filepath) {
        if (filepath.charAt(0) != '/') filepath = "/" + filepath;
        InputStream ins = RoboRally.class.getResourceAsStream("/Music" + filepath);
        return new BufferedInputStream(ins);
    }

    public void playLoop(String filepath) {
        new Thread(() -> {

            do {
                try {
                    ais = AudioSystem.getAudioInputStream(findfile(filepath));
                    AudioFormat audioFormat = ais.getFormat();
                    DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                    sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
                    if (!vFlag) {
                        sourceDataLine.open(audioFormat);
                        sourceDataLine.start();
                        byte[] bufferBytes = new byte[BUFFER_SIZE];
                        int readBytes;
                        while ((readBytes = ais.read(bufferBytes)) != -1) {
                            sourceDataLine.write(bufferBytes, 0, readBytes);
                        }
                    }
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    throw new RuntimeException(e);
                }
            } while (true);


        }).start();
    }

    public void play(String filepath) {
        try {
            AudioInputStream snd = AudioSystem.getAudioInputStream(findfile(filepath));
            AudioFormat audioFormat = snd.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            SourceDataLine sndLine = (SourceDataLine) AudioSystem.getLine(info);
            sndLine.open(audioFormat);
            sndLine.start();
            byte[] bufferBytes = new byte[BUFFER_SIZE];
            int readBytes;
            while ((readBytes = snd.read(bufferBytes)) != -1) {
                sndLine.write(bufferBytes, 0, readBytes);
            }
            sndLine.drain();
            sndLine.flush();
            sndLine.close();
            snd.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeLoop() {
        sourceDataLine.close();
        this.vFlag = true;

    }

    public void openLoop() {
        this.vFlag = false;

    }

    public void playDamageSound() {
        Random rnd = new Random();
        int i = (rnd.nextInt(9) + 1);
        String filepath = "damage" + i + ".wav";
        this.play(filepath);
    }

    public void playDieSound() {
        Random rnd = new Random();
        int i = (rnd.nextInt(5) + 1);
        String filepath = "die" + i + ".wav";
        this.play(filepath);
    }

    public boolean checkLoopPlay() {
        return (sourceDataLine == null);
    }

}
