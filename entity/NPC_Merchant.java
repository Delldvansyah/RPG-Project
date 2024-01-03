package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class NPC_Merchant extends Entity { // menambahkan properti tambahan yang spesifik

    public NPC_Merchant(GamePanel gp) {
        // memanggil konstruktor kelas induk (Entity) jika diperlukan
        super(gp);

        // inisialisasi objek NPC_Merchant dengan informasi dari GamePanel

        direction = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getImage();
        setDialogue();
        setItems();
    }

    public void getImage() {
        up1 = setup("../asset/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("../asset/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("../asset/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("../asset/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("../asset/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("../asset/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("../asset/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("../asset/npc/merchant_down_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() { // menambahkan percakapan
        dialogues[0] = "You found me! He he. \nI have some good stuff. \nDo you want to trade?";
    }

    public void setItems() { // Mengatur item baru pada inventaris
        inventory.add(new OBJ_Potion_Red(gp)); // menambahkan suatu instansi dari kelas OBJ_Potion_Red ke dalam suatu koleksi atau inventaris (inventory).
        inventory.add(new OBJ_Key(gp)); // menambahkan suatu instansi dari kelas OBJ_Key ke dalam suatu koleksi atau inventaris (inventory).
        inventory.add(new OBJ_Sword_Normal(gp)); // menambahkan suatu instansi dari kelas OBJ_Sword_Normal ke dalam suatu koleksi atau inventaris (inventory).
        inventory.add(new OBJ_Axe(gp)); // menambahkan suatu instansi dari kelas OBJ_Axe ke dalam suatu koleksi atau inventaris (inventory). 
        inventory.add(new OBJ_Shield_Wood(gp)); // menambahkan suatu instansi dari kelas OBJ_Shield_Wood ke dalam suatu koleksi atau inventaris (inventory).
        inventory.add(new OBJ_Shield_Blue(gp)); // menambahkan suatu instansi dari kelas OBJ_Shield_Blue ke dalam suatu koleksi atau inventaris (inventory). 
    }

    public void speak() {
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
