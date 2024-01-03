package tile_interactive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class IT_DryTree extends InteractiveTile {
    GamePanel gp;

    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("../asset/tiles_interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
    }

    public boolean isCorrectItem(Entity entity) {  // metode untuk memeriksa apakah suatu Entity adalah item yang benar
        boolean isCorrectItem = false;

        if (entity.currentWeapon.type == type_axe) {
            isCorrectItem = true;
        }

        return isCorrectItem;
    }

    public void playSe() { // memainkan efek suara (Sound Effect) 
        gp.playSE(11);
    }

    public InteractiveTile getDestroyedForm() { // mengembalikan suatu bentuk atau representasi objek dari kelas InteractiveTile yang terkait dengan situasi atau kondisi "destroyed" (hancur).
        InteractiveTile tile = new IT_Trunk(gp, worldX / gp.tileSize, worldY / gp.tileSize);

        return tile;
    }

    public Color getParticleColor() { // metode untuk mendapatkan warna partikel
        Color color = new Color(65, 50, 30);

        return color;
    }

    public int getParticleSize() { //metode untuk mendapatkan nilai ukuran partikel
        int size = 6;

        return size;
    }

    public int getParticleSpeed() { // metode untuk mendapatkan nilai kecepatan partikel
        int speed = 1;

        return speed;
    }

    public int getParticleMaxLife() { // metode untuk mendapatkan nilai umur maksimum partikel
        int maxLife = 20;

        return maxLife;
    }
}
