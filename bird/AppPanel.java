import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
public class AppPanel extends JPanel{
    int xAxis=60;
    int yAxis=215;
    int backgroundY=0;
    int backgroundz=10;
    int backgroundx=10;
    int width;
    int currentFrame = 0;
    int height;
    BufferedImage background;
    BufferedImage obstacle;
    BufferedImage obstacle1;
    BufferedImage birdSpriteSheet;
    Timer timer;
     BufferedImage[] frames;
    Random random;
    AppPanel(){
        setSize(400,400);
    setBackground(Color.cyan);
    showBgImage();
    loadBirdSpriteSheet();
    extractBirdFrames();
    callPaintAgain();
    keyboardControl();
    setFocusable(true);
    }
    public BufferedImage convertToBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return bufferedImage;
    }

    public void showBgImage(){
 try{
    JOptionPane.showMessageDialog(this, "Press Ok to start the game","Car Race",JOptionPane.INFORMATION_MESSAGE);
            background = ImageIO.read(AppPanel.class.getResource("B.jpeg"));
            obstacle = ImageIO.read(AppPanel.class.getResource("03.png"));
            obstacle1 = ImageIO.read(AppPanel.class.getResource("o2.png"));
            //URL url = AppPanel.class.getResource("b.gif");
            ImageIcon imageIcon = new ImageIcon(AppPanel.class.getResource("b.gif"));
            JLabel label = new JLabel(imageIcon);}
         catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("NO IMAGE FOUND");
            e.printStackTrace();
        }
    } 
    void loadBirdSpriteSheet() {
        Image birdImage = new ImageIcon(AppPanel.class.getResource("bird2.png")).getImage()
        ;
        if (birdImage == null) {
            System.err.println("Bird image not found!");
        } else {
            birdSpriteSheet = convertToBufferedImage(birdImage);
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
        g.drawImage(obstacle, backgroundx,-20, 200, 500, this);
        g.drawImage(obstacle, backgroundx+300,-20, 200, 500, this);
        g.drawImage(obstacle1, backgroundz,350, 50, 150, this);
        g.drawImage(obstacle1, backgroundz+300,350, 50, 150, this);
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
           // currentFrame = (currentFrame + 1) % frames.length;
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
           //checkCollisions();
            repaint();
        });
        timer.start();
    }

    // void checkCollisions() {
    //     if (bird.collidesWith(obstacle) || bird.collidesWith(obstacle1)  ) {
    //         exitGame();
    //     }
        
    // }
   
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

