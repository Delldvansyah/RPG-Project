package tile;

import java.awt.image.BufferedImage;

public class Tile { // menggambarkan sifat-sifat perilaku dari objek
    public BufferedImage image; // menyimpan gambar dalam suatu program, dan dapat diakses dan dimanipulasi oleh kode lain yang memiliki akses ke objek tersebut.
    public boolean collision = false; // menyimpan informasi mengenai status suatu kondisi, dalam hal ini, apakah terjadi tabrakan (collision) atau tidak.
}
