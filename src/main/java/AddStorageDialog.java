import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddStorageDialog extends NewWindowDialog
{
    private Client client;
    JTextField nameField;
    JTextField numberField;
    JTextField valueField;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JLabel valueLabel;

    AddStorageDialog(Client client)
    {
        this.client=client;
        buildDialog();
        makeGui();

    }
    @Override
    void makeGui()
    {

        nameLabel = new JLabel("MATERIAL:");
        numberLabel = new JLabel("ILOSC:");
        valueLabel = new JLabel("WARTOSC_SZTUKI:");

        nameField = new JTextField("",30);
        numberField = new JTextField("",5);
        valueField = new JTextField("",10);

        nameLabel.setBounds(150,200,100,50);
        numberLabel.setBounds(150,260,100,50);
        valueField.setBounds(150,320,100,50);

        nameField.setBounds(250,210,300,30);
        numberField.setBounds(250,270,300,30);
        valueField.setBounds(250,330,300,30);

        add(nameField);
        add(numberField);
        add(valueField);

        add(nameLabel);
        add(numberLabel);
        add(valueLabel);


    }
    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
