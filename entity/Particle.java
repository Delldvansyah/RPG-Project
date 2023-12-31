package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class Particle extends Entity { // metode khusus untuk kelas partikel
    
    Entity generator;
    Color color;
    int size;
    int xd;
    int yd;

    public Particle(GamePanel gp, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
        super(gp); // inisialisasi objek partikel

        this.generator = generator;
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.maxLife = maxLife;
        this.xd = xd;
        this.yd = yd;

        life = maxLife;
        int offset = (gp.tileSize / 2) - (size / 2);
        worldX = generator.worldX + offset;
        worldY = generator.worldY + offset;
    }

    public void update() {  // memperbarui posisi berdasarkan kecepatan dan arah
        life--;

        if (life < maxLife / 3) { // mengurangi masa hidup
            yd++;
        }

        worldX += xd * speed;
        worldY += yd * speed;

        if (life == 0) {
            alive = false;
        }
    }

    public void draw(Graphics2D g2) { // menggambar objek menggunakan suatu objek Graphics2D. 
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.setColor(color);
        g2.fillRect(screenX, screenY, size, size);
    }

}
