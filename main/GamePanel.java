package ProjectRPG.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import ProjectRPG.entity.Player;
import ProjectRPG.object.SuperObject;
import ProjectRPG.tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTING
    final int originalTileSize = 16; // 16x16 Tiles - 32x32 Tiles
    final int scale = 4; // Scale Tiles 3x - Scale 2x

    public final int tileSize = originalTileSize * scale; // 64x64 Tiles
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 1024 Pixels
    public final int screenHeight = tileSize * maxScreenRow; // 768 Pixels

    // WORLD SETTING
    public final int maxWorldCol = 50; 
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound sfx = new Sound();
    public CollisionCheck cChek = new CollisionCheck(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public String drawFPS;
    

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000/FPS; // 0.01666 Seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        // int drawFPS = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                // 1 UPDATE Information
                update();

                // 2 DRAW The Updated Infomation
                repaint();
                delta--;
                //drawFPS++;
            }

            // if (timer >= 1000000000){
            //     System.out.println("FPS : " + drawFPS);
            //     drawFPS = 0;
            //     timer = 0;
            // }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true){
            drawStart = System.nanoTime();
        }
        
        // TILE
        tileM.draw(g2);

        // OBJECT
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        // PLAYER
        player.draw(g2);

        // UI
        ui.draw(g2);

        // DEBUG
        if(keyH.checkDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time : " + passed, 10, 400);
            System.out.println("Draw Time : " + passed);
        }
        
        g2.dispose();
    }

    public void playMusic(int i){

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        
        music.stop();
    }

    public void playSFX(int i){

        sfx.setFile(i);
        sfx.play();
    }
}
