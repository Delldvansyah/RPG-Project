package ProjectRPG.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import ProjectRPG.main.GamePanel;
import ProjectRPG.main.KeyHandler;
import ProjectRPG.main.UtilityTool;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler KeyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.KeyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2); 

        hitBox = new Rectangle();
        hitBox.x = 8;
        hitBox.y = 16;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        hitBox.width = 42;
        hitBox.height = 45;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");

    }

    public BufferedImage setup(String imageName){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("../asset/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            
        } catch (IOException e){
            e.printStackTrace();
        }

        return image;
    }

    public void update(){
        if(KeyH.upPressed == true || KeyH.downPressed == true || KeyH.leftPressed == true || KeyH.rightPressed == true){
                if(KeyH.upPressed == true){ 
                direction = "up";    
            } else if (KeyH.downPressed == true){
                direction = "down";               
            } else if (KeyH.leftPressed == true){
                direction = "left";    
            } else if (KeyH.rightPressed == true){
                direction = "right";
            }

            // CHECK COLLISION
            collisionOn = false;
            gp.cChek.checkTiles(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChek.checkObject(this, true);
            interactObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false){
                switch(direction){
                    case "up":
                            worldY -= speed;
                        break;
                    case "down":
                            worldY += speed;
                        break;
                    case "left":
                            worldX -= speed;
                        break;
                    case "right":
                            worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 15){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void interactObject(int i){
        
        if(i != 999){
            String objectName = gp.obj[i].name;

            switch(objectName){
                case "Key":
                        gp.playSFX(1);
                        hasKey++;
                        gp.obj[i] = null;
                        gp.ui.showNotif("You got a key!");
                    break;
                case "Door":
                        if(hasKey > 0){
                            gp.playSFX(3);
                            gp.obj[i] = null;
                            hasKey--;
                            gp.ui.showNotif("Door opened!");
                        } else {
                            gp.ui.showNotif("Need a key!");
                        }
                    break;
                case "Boots":
                        gp.playSFX(2);
                        speed += 1;
                        gp.obj[i] = null;
                        gp.ui.showNotif("Speed up!");
                    break;
                case "Chest":
                        gp.ui.gameFinished = true;
                        gp.stopMusic();
                        gp.playSFX(4);
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch(direction){
            case "up":
                if (spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }
}