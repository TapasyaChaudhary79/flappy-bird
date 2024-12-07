import javax.swing.JFrame;
public class AppFrame extends JFrame {
    AppFrame(){
        setTitle("Bird Game");
        setSize(500,500);
        setLocationRelativeTo(null);
        AppPanel apanel = new AppPanel();
        add(apanel);
        //add(apanel);
        setVisible(true);
    }
    
}