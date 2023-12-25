package ProjectRPG.main;

import ProjectRPG.entity.NPC_Sepuh;
import ProjectRPG.object.OBJ_Boots;
import ProjectRPG.object.OBJ_Chest;
import ProjectRPG.object.OBJ_Door;
import ProjectRPG.object.OBJ_Key;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

    }

    public void setNPC(){

        gp.npc[0] = new NPC_Sepuh(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;
    }
}
