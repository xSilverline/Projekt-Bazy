import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginScreenFrame extends NewWindowFrame {

    private Client client;
    private JButton exitButton;
    private JButton connectButton;
    private JLabel titleLabel;
    private JLabel passLabel;
    private JLabel userLabel;
    private JTextField passwordText = new JTextField("",30);
    private JTextField userText = new JTextField("",30);
    private JPanel panel;

    LoginScreenFrame(Client client)
    {
        this.client = client;
        buildFrame();
        makeGui();
    }

    @Override
    void makeGui()
    {
        panel = new JPanel();
        panel.setBounds(0,0,1366,768);
        panel.setLayout(null);
        add(panel);
        panel.setBackground(new Color(74, 73, 75));

        exitButton = new JButton("WYJŚCIE");
        exitButton.setBounds(1200,700,150,50);
        exitButton.addActionListener(this);

        titleLabel=new JLabel("SYSTEM ZARZĄDZANIA PRODUKCJĄ");
        titleLabel.setBounds(100,20,1166,70);
        titleLabel.setFont(titleLabel.getFont().deriveFont(40f));
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        passLabel=new JLabel("HASŁO: ");
        userLabel=new JLabel("USER: ");
        passLabel.setFont(passLabel.getFont().deriveFont(30f));
        userLabel.setFont(userLabel.getFont().deriveFont(30f));
        passLabel.setBounds(300,360,200,50);
        userLabel.setBounds(300,300,200,50);
        passLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        userLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        userText.setBounds(510,310,200,30);
        passwordText.setBounds(510,370,200,30);

        connectButton = new JButton("POŁĄCZ");
        connectButton.addActionListener(this);
        connectButton.setBounds(730,310,130,90);

        panel.add(exitButton);
        panel.add(titleLabel);
        panel.add(passLabel);
        panel.add(userLabel);
        panel.add(passwordText);
        panel.add(userText);
        panel.add(connectButton);

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
        else if (source==connectButton);
        {
            client.setMenuWindow();
            dispose();
            //TODO: if login successful then show message, dispose, show MenuWindow (via Client Class)
        }

    }
}
