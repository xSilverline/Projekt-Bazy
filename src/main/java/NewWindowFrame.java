
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class NewWindowFrame extends JFrame implements ActionListener
{
    abstract void makeGui();
    abstract void closeWindow();
    public abstract void actionPerformed(ActionEvent e);
}
