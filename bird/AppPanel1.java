import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class AppPanel1 extends JPanel{
    int xAxis=60;
    int yAxis=215;
    int backgroundY=0;
    int backgroundz=10;
    int backgroundx=10;
    int width;
    int currentFrame = 0;
    int height;
    BufferedImage background;
    Bird obstacle;
    Bird obstacle1;
    Bird birdSpriteSheet;
    Timer timer;
     BufferedImage[] frames;
    Random random;
    AppPanel1(){
        setSize(400,400);
    setBackground(Color.cyan);
    showBgImage();
    loadBirdSpriteSheet();
    extractBirdFrames();
    callPaintAgain();
    keyboardControl();
    setFocusable(true);
    }
    

    public void showBgImage(){
 try{
    JOptionPane.showMessageDialog(this, "Press Ok to start the game","Car Race",JOptionPane.INFORMATION_MESSAGE);
            background = ImageIO.read(AppPanel.class.getResource("B.jpeg"));
        obstacle = new Bird(350, 400, 70, 50, "03.png", 4);
            obstacle1 = new Bird(450, 0, 60,40, "o2.png", 2);
            ImageIcon imageIcon = new ImageIcon(AppPanel.class.getResource("b.gif"));
            JLabel label = new JLabel(imageIcon);}
         catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("NO IMAGE FOUND");
            e.printStackTrace();
        }
    } 
    void loadBirdSpriteSheet() {
        Bird birdImage = new Bird(AppPanel.class.getResource("bird2.png"));
        if (birdImage == null) {
            System.err.println("Bird image not found!");
        } else {
            //birdSpriteSheet = convertToBufferedImage(birdImage);
            birdSpriteSheet = birdImage;
        }
    }
    void extractBirdFrames() {
        if (birdSpriteSheet == null) {
            System.err.println("Bird sprite sheet not loaded!");
            return;
        }
        int frameWidth = birdSpriteSheet.getWidth() / 3;
        int frameHeight = birdSpriteSheet.getHeight() / 3;
        frames = new BufferedImage[3];

        for (int i = 0; i < 3; i++) {
            frames[i] = birdSpriteSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        g.drawImage(background, backgroundY,0, 500, 500, this);
        g.drawImage(background, backgroundY+500,0, 500, 500, this);
        obstacle.showBird(g);
        obstacle1.showBird(g);
        birdSpriteSheet.showBirdImg(g);
      if (frames != null) {
        g.drawImage(frames[currentFrame], xAxis, yAxis, 50, 50, null);
    }   
    }
    void callPaintAgain() {
        timer = new Timer(300, (abcd) -> {
            currentFrame = (currentFrame + 1) % frames.length;
            repaint();
        });
        timer.start();
        timer = new Timer(30, (abcd) -> {
            currentFrame = (currentFrame + 1) % frames.length;
            backgroundY-=1;
            if(backgroundY<=-500){
                backgroundY=0;
            }
            backgroundx-=2;
            if(backgroundx<=-300){
                backgroundx=0;
            }
            backgroundz-=3;
            if(backgroundz<=-400){
                backgroundz=50;
            }
            checkCollisions();
            repaint();
        });
        timer.start();
    }

    void checkCollisions() {
        if (birdSpriteSheet.collidesWith(obstacle) || birdSpriteSheet.collidesWith(obstacle1)  ) {
            exitGame();
        }
        
    }
   
    void exitGame() {
        JOptionPane.showMessageDialog(this, "Game Over!");
        System.exit(0); // Exit the program
    }
void keyboardControl(){
    System.out.println("KEYBOARD CALLED");
     addKeyListener(new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("KEY PRESS CALLED");
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                xAxis = xAxis +5;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                xAxis -= 5;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                yAxis += 5;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {   
                yAxis -= 5;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {   
                yAxis -= 5;
                xAxis +=5;
            }
           

            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
        }

        @Override
        public void keyReleased(KeyEvent e) {
  // TODO Auto-generated method stub
  throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
}});
}}

