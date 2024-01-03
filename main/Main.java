package main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

    // MAIN METHOD
    public static JFrame window; // mengindikasikan bahwa sebuah variabel statis dengan tipe data JFrame telah dideklarasikan

    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // close (X) button
        window.setResizable(false); // user bisa mengatur ukuran
        window.setTitle("2D Adventure"); // judul aplikasi

        // Call GamePanel
        GamePanel gamePanel = new GamePanel(); // mengelola panel permainan dalam suatu aplikasi
        window.add(gamePanel); // menambahkan komponen ke dalam 'window'

        gamePanel.config.loadConfig(); 
        // mengecek apakah properti fullScreenOn dari objek gamePanel bernilai true.
        if (gamePanel.fullScreenOn == true) {
            window.setUndecorated(true);
        }

        window.pack();
        window.setLocationRelativeTo(null); // mengatur posisi window relatif terhadap komponen yang diberikan
        window.setVisible(true); // mengatur visibility window yg akan dibuat terlihat dilayar

        gamePanel.setupGame(); // inisialisasi atau pengaturan awal yang diperlukan untuk memulai permainan
        gamePanel.startGameThread(); // memulai eksekusi suatu thread yang menangani logika permainan
    }
}
