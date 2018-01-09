import javax.swing.*;

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

    LoginScreenFrame(Client client)
    {
        this.client = client;
        buildFrame();
        makeGui();
    }

    @Override
    void makeGui()
    {

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

        userText.setBounds(510,310,200,30);
        passwordText.setBounds(510,370,200,30);

        connectButton = new JButton("CONNECT");
        connectButton.addActionListener(this);
        connectButton.setBounds(730,310,130,90);

        add(exitButton);
        add(titleLabel);
        add(passLabel);
        add(userLabel);
        add(passwordText);
        add(userText);
        add(connectButton);

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
