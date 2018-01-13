
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public abstract class NewWindowFrame extends JFrame implements ActionListener,ListSelectionListener
{

    abstract void makeGui() throws SQLException;
    void buildFrame()
    {
        setSize(1366,768);
        setUndecorated(true);
        setResizable(false);
        setLayout(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        getContentPane().setBackground(new Color(74, 73, 75));
        setVisible(true);
    }
    abstract void closeWindow();
    public abstract void actionPerformed(ActionEvent e);
}
