import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddOrderDialog extends NewWindowDialog
{
    private Client client;
    private MenuButton addButton;
    private MenuButton closeButton;

    private JLabel nameLabel;
    private JLabel amountLabel;
    private JLabel valueLabel;
    private JLabel dayeLabel;
    private JLabel statusLabel;


    private JTextField nameField;
    private JTextField amountField;
    private JTextField valueField;
    private JTextField dateField;
    private JTextField statusField;

    AddOrderDialog(Client client)
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

        JLabel titleLabel = new JLabel("DODAWANIE DO ZAMÓWIEŃ");
        titleLabel.setBounds(100,10,600,50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(40f));
        add(titleLabel);

        nameLabel = new JLabel("MATERIAŁ:");
        amountLabel = new JLabel("ZAMÓWIONA ILOŚĆ:");
        valueLabel = new JLabel("WARTOŚĆ:");
        dayeLabel = new JLabel("DATA ZAMÓWIENIA:");
        statusLabel = new JLabel("STATUS:");


        nameLabel.setBounds(150,200,140,40);
        amountLabel.setBounds(150,240,140,40);
        valueLabel.setBounds(150,280,140,40);
        dayeLabel.setBounds(150,320,140,40);
        statusLabel.setBounds(150,360,140,40);

        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        amountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dayeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);


        nameField = new JTextField("", 30);
        amountField = new JTextField("", 15);
        valueField = new JTextField("", 15);
        dateField = new JTextField("",30);
        statusField = new JTextField("",30);

        nameField.setBounds(300,205,200,30);
        amountField.setBounds(300,245,200,30);
        valueField.setBounds(300,285,200,30);
        dateField.setBounds(300,325,200,30);
        statusField.setBounds(300,365,200,30);

        nameField.setFont(nameField.getFont().deriveFont(20f));
        amountField.setFont(amountField.getFont().deriveFont(20f));
        valueField.setFont(valueField.getFont().deriveFont(20f));
        dateField.setFont(dateField.getFont().deriveFont(20f));
        statusField.setFont(statusField.getFont().deriveFont(20f));

        add(nameField);
        add(amountField);
        add(dateField);
        add(valueField);
        //add(statusField);

        add(nameLabel);
        add(amountLabel);
        add(valueLabel);
        add(dayeLabel);
        //add(statusLabel);

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
        }
        else if(source == addButton)
        {
            if(nameField.getText().isEmpty() || amountField.getText().isEmpty() || dateField.getText().isEmpty() || valueField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Błąd danych - uzupełnij");
            }
            else
            {
                client.server.addOrder(nameField.getText(),amountField.getText(),dateField.getText(),valueField.getText());
                dispose();
                try
                {
                    client.ordersWindowFrame.dispose();
                    client.setOrdersWindow();
                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }


            }
        }

    }
}
