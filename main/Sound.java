package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    Clip clip; // memainkan suara dalam java sound
    URL soundUrl[] = new URL[30]; // menyimpan url dengan ukuran maksimum 30 
    FloatControl fc; // mengendalikan parameter suara
    int volumeScale = 3; // deklarasi variabel 'volumeScale' dengan nilai awal 3
    float volume; // menyimpan nilai volume suara

    public Sound() {
        soundUrl[0] = getClass().getResource("../asset/sound/BlueBoyAdventure.wav");
        soundUrl[1] = getClass().getResource("../asset/sound/coin.wav");
        soundUrl[2] = getClass().getResource("../asset/sound/powerup.wav");
        soundUrl[3] = getClass().getResource("../asset/sound/unlock.wav");
        soundUrl[4] = getClass().getResource("../assets/sound/fanfare.wav");
        soundUrl[5] = getClass().getResource("../asset/sound/hitmonster.wav");
        soundUrl[6] = getClass().getResource("../asset/sound/receivedamage.wav");
        soundUrl[7] = getClass().getResource("../asset/sound/swingweapon.wav");
        soundUrl[8] = getClass().getResource("../asset/sound/levelup.wav");
        soundUrl[9] = getClass().getResource("../asset/sound/cursor.wav");
        soundUrl[10] = getClass().getResource("../asset/sound/burning.wav");
        soundUrl[11] = getClass().getResource("../asset/sound/cuttree.wav");
        soundUrl[12] = getClass().getResource("../asset/sound/gameover.wav");
        soundUrl[13] = getClass().getResource("../asset/sound/stairs.wav");
    }

    public void setFile(int i) { // menetapkan (set) file suara yang akan digunakan oleh objek
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume(); // penyesuaian terhadap volume suara
        } catch (Exception e) {
        }
    }

    public void play() { // metode untuk memulai pemutaran suara yang telat diatur
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void checkVolume() { // mengatur volume suara berdasarkan skala tertentu
        switch (volumeScale) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;
        }

        fc.setValue(volume);
    }
}
