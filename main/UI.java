package ProjectRPG.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import ProjectRPG.object.OBJ_Key;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    public boolean messageON = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("../asset/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch(FontFormatException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showNotif(String text){

        message = text;
        messageON = true;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        // TITLE STATE
        if(gp.titleState == gp.titleState){
            titleScreen();
        }

        // PLAY STATE
        if(gp.gameState == gp.playState){
        
        }

        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
            pauseScreen();
        }

        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            dialogueScreen();
        }
    }

    public void titleScreen(){

        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TITLE
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Gatau Judulnya Apa";
        int x = centeredText(text);
        int y = gp.tileSize * 3;

        // TITLE SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);

        // TITLE COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // CHARACTER IMAGE
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = centeredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x , y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = centeredText(text);
        y += gp.tileSize;
        g2.drawString(text, x , y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = centeredText(text);
        y += gp.tileSize;
        g2.drawString(text, x , y);
        if(commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void pauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));

        String text = "PAUSED";
        int x = centeredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);

    }

    public void dialogueScreen(){

        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect( x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public int centeredText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;

    }
}
