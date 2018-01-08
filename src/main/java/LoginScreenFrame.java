import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginScreenFrame extends NewWindowFrame {

    private Client client;
    private JButton exitButton;
    private JLabel titleLabel;
    private JLabel passLabel;
    private JLabel userLabel;

    LoginScreenFrame(Client client)
    {
        this.client = client;
        makeGui();
    }

    @Override
    void makeGui()
    {
        setSize(1366,768);
        setUndecorated(true);
        setResizable(false);
        setLayout(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        getContentPane().setBackground(new Color(74, 73, 75));
        setVisible(true);

        exitButton = new JButton("Exit");
        exitButton.setBounds(1200,700,150,50);
        exitButton.addActionListener(this);

        titleLabel=new JLabel("FACTORY MANAGEMENT SYSTEM ");
        titleLabel.setBounds(100,20,1166,70);
        titleLabel.setFont(titleLabel.getFont().deriveFont(40f));
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        passLabel=new JLabel("PASSWORD: ");
        userLabel=new JLabel("USER: ");
        passLabel.setFont(passLabel.getFont().deriveFont(30f));
        userLabel.setFont(userLabel.getFont().deriveFont(30f));
        passLabel.setBounds(300,360,200,50);
        userLabel.setBounds(300,300,200,50);
        passLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        add(exitButton);
        add(titleLabel);
        add(passLabel);
        add(userLabel);
    }

    @Override
    void closeWindow()
    {

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source==exitButton)
        {
            System.exit(0);
        }

    }
}
