package ProjectRPG.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import ProjectRPG.object.OBJ_Key;

public class UI {
    
    GamePanel gp;
    Font arial_30, arial_80B;
    BufferedImage keyImage;
    public boolean messageON = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;

        arial_30 = new Font("Arial", Font.PLAIN, 30);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }

    public void showNotif(String text){

        message = text;
        messageON = true;
    }

    public void draw(Graphics2D g2){

        if(gameFinished == true){

            g2.setFont(arial_30);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You Found the Treasure";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            text = "Time Record : " + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 4);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);

            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        } else {
            g2.setFont(arial_30);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 4, gp.tileSize / 4, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 85, 50);

            // TIMER
            playTime += (double) 1 / 60;
            g2.drawString("Time : " + dFormat.format(playTime), gp.tileSize * 17, 50);

            // MESSAGE
            if(messageON == true){

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2 , gp.tileSize * 5);

                messageCounter++;

                if(messageCounter > 120){
                    messageCounter = 0;
                    messageON = false;
                }
            }
        }
    }
}
