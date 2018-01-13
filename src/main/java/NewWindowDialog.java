import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class NewWindowDialog extends JDialog implements ActionListener
{
    abstract void makeGui();
    void buildDialog()
    {

        setSize(800,600);
        setUndecorated(true);
        setResizable(false);
        setLayout(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        //getContentPane().setBackground(new Color(74, 73, 75));



    }
    public abstract void actionPerformed(ActionEvent e);
}
