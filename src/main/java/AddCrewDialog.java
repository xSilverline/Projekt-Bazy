import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddCrewDialog extends NewWindowDialog
{
    private Client client;
    private MenuButton addButton;
    private MenuButton closeButton;


    private JLabel nameLabel;
    private JLabel surLabel;
    private JLabel posLabel;
    private JLabel payLabel;
    private JLabel loginLabel;
    private JLabel passLabel;


    private JTextField nameField;
    private JTextField surField;
    private JTextField posField;
    private JTextField payField;
    private JTextField loginField;
    private JTextField passField;


    AddCrewDialog(Client client)
    {
        this.client=client;
        buildDialog();
        makeGui();
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
    }

    @Override
    void makeGui()
    {
        JLabel titleLabel = new JLabel("DODAWANIE DO KADRY");
        titleLabel.setBounds(100,10,600,50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(40f));
        add(titleLabel);

        nameLabel = new JLabel("IMIE:");
        surLabel = new JLabel("NAZWISKO:");
        posLabel = new JLabel("STANOWISKO:");
        payLabel = new JLabel("PENSJA:");
        loginLabel = new JLabel("LOGIN:");
        passLabel = new JLabel("HASŁO:");

        nameLabel.setBounds(150,200,140,40);
        surLabel.setBounds(150,240,140,40);
        posLabel.setBounds(150,280,140,40);
        payLabel.setBounds(150,320,140,40);
        loginLabel.setBounds(150,360,140,40);
        passLabel.setBounds(150,400,140,40);

        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        surLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        posLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        payLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        loginLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        passLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        nameField = new JTextField("", 30);
        surField = new JTextField("", 15);
        posField = new JTextField("", 15);
        payField = new JTextField("",30);
        loginField = new JTextField("",30);
        passField = new JTextField("",30);

        nameField.setBounds(300,205,200,30);
        surField.setBounds(300,245,200,30);
        posField.setBounds(300,285,200,30);
        payField.setBounds(300,325,200,30);
        loginField.setBounds(300,365,200,30);
        passField.setBounds(300,405,200,30);

        add(nameField);
        add(surField);
        add(payField);
        add(posField);
        add(loginField);
        add(passField);

        add(nameLabel);
        add(surLabel);
        add(posLabel);
        add(payLabel);
        add(loginLabel);
        add(passLabel);


        addButton = new MenuButton("DODAJ");
        closeButton = new MenuButton("ZAMKNIJ");

        addButton.setBounds(300,450,200,50);
        closeButton.setBounds(590,540,200,50);
        add(addButton);
        add(closeButton);

        addButton.addActionListener(this);
        closeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == closeButton)
        {
            dispose();
            try
            {
                client.crewWindowFrame.getList();
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        }
        else if(source == addButton)
        {
            if(nameField.getText().isEmpty() || surField.getText().isEmpty() || posField.getText().isEmpty() || payField.getText().isEmpty()|| loginField.getText().isEmpty() || passField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Błąd danych - uzupełnij");
            }
            else
            {
                client.server.register(nameField.getText(),surField.getText(),posField.getText(),payField.getText(),loginField.getText(),passField.getText());
                dispose();
                try
                {
                    client.crewWindowFrame.dispose();
                    client.setCrewWindow();
                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }


            }


        }

    }
}
