
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import java.util.Random;
public class Bird {
    int xAxis;
    int yAxis;
    int height;
    int width;
    String filename;
    int carSpeed;
    BufferedImage carImage;
    Random rand = new Random();
    Bird(int xAxis, int yAxis, int height, int width, String filename, int carSpeed) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.height = height;
        this.width = width;
        this.filename = filename;
        this.carSpeed = carSpeed;
        showCarImage(); 
    }
    public Bird(URL resource) {
        //TODO Auto-generated constructor stub
    }
    void showCarImage() {
        try {
            carImage = ImageIO.read(getClass().getResource(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void showBird(Graphics g) {
        g.drawImage(carImage, xAxis, yAxis, width, height, null);
    }
    void moveBird() {
     xAxis += carSpeed; 
        if (xAxis >= 500) { 
         xAxis = (-width);   
        }
    }
    boolean collidesWith(Bird other) {
        Rectangle thisRect = new Rectangle(xAxis, yAxis, width, height);
        Rectangle otherRect = new Rectangle(other.xAxis, other.yAxis, other.width, other.height);
        return thisRect.intersects(otherRect);
    }
    // public Bird getImage() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getImage'");
    // }
    // public int getWidth() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getWidth'");
    // }
    // public int getHeight() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getHeight'");
    // }
    public BufferedImage getSubimage(int i, int j, int frameWidth, int frameHeight) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSubimage'");
    }
    public void showBirdImg(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showBirdImg'");
    }
}
