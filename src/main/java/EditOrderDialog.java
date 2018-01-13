import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class EditOrderDialog extends NewWindowDialog
{
    private Client client;
    private MenuButton closeButton;
    private MenuButton saveButton;

    private JLabel nameLabel;
    private JLabel amountLabel;
    private JLabel valueLabel;
    private JLabel dayeLabel;
    private JLabel statusLabel;

    private JTextField amountField;
    private JTextField valueField;
    private JTextField dateField;
    private JTextField statusField;
    private JTextField nameField;

    private String currentOrder;

    EditOrderDialog(Client client,String currentOrder)
    {
        this.client = client;
        this.currentOrder = currentOrder;
        buildDialog();
        makeGui();
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
    }

    @Override
    void makeGui()
    {
        JLabel titleLabel = new JLabel("ZAMÓWIENIE ID: "+currentOrder);
        titleLabel.setBounds(100,10,600,50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(30f));
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

      //  add(nameField);
       // add(amountField);
       // add(dateField);
       // add(valueField);
        add(statusField);

        //add(nameLabel);
        //add(amountLabel);
        //add(valueLabel);
        //add(dayeLabel);
        add(statusLabel);

        closeButton = new MenuButton("ZAMKNIJ");
        closeButton.setBounds(590,540,200,50);
        add(closeButton);
        closeButton.addActionListener(this);

        saveButton = new MenuButton("ZAPISZ");
        saveButton.setBounds(300,500,200,50);
        add(saveButton);
        saveButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        if(source == closeButton)
        {
            dispose();
        } else if(source == saveButton)
        {
            if(statusField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Proszę wpisać status!");
            }
            else
            {
                client.server.setOrderStatus(currentOrder,statusField.getText());
                client.ordersWindowFrame.dispose();
                try
                {
                    client.setOrdersWindow();
                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
                dispose();

            }

        }
    }
}
