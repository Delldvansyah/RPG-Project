package tile_interactive;

import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity {
    GamePanel gp;
    public boolean destructible = false;

    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
    }

    public boolean isCorrectItem(Entity entity) { // implementasi logika untuk memeriksa apakah entity adalah item yang benar
        boolean isCorrectItem = false;
        // return nilai boolean berdasarkan hasil pemeriksaan
        return isCorrectItem;
    }

    public void playSe() {
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = null;

        return tile;
    }

    public void update() {
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) { // menggambar objek pada suatu konteks grafis dalam permainan
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(down1, screenX, screenY, null);
        }
    }
}
