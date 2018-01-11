import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddStorageDialog extends NewWindowDialog
{
    private Client client;

    private MenuButton addButton;
    private MenuButton closeButton;

    AddStorageDialog(Client client)
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
        /*
        panel = new JPanel();
        panel.setBounds(0,0,800,600);
        add(panel);
        panel.setLayout(null);*/
        //panel.setBackground();

        JLabel titleLabel = new JLabel("DODAWANIE DO MAGAZYNU");
        titleLabel.setBounds(100,10,600,50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(40f));
        add(titleLabel);

        addButton = new MenuButton("DODAJ");
        closeButton = new MenuButton("ZAMKNIJ");

        addButton.setBounds(300,400,200,50);
        closeButton.setBounds(590,540,200,50);

        JLabel nameLabel = new JLabel("MATERIAL:");
        JLabel numberLabel = new JLabel("ILOSC:");
        JLabel valueLabel = new JLabel("WARTOSC SZTUKI:");

        JTextField nameField = new JTextField("", 30);
        JTextField numberField = new JTextField("", 5);
        JTextField valueField = new JTextField("", 10);

        nameLabel.setBounds(150,200,140,50);
        numberLabel.setBounds(150,260,140,50);
        valueLabel.setBounds(150,320,140,50);

        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        nameField.setBounds(300,210,200,30);
        numberField.setBounds(300,270,200,30);
        valueField.setBounds(300,330,200,30);

        numberField.setFont(nameField.getFont().deriveFont(20f));
        nameField.setFont(nameField.getFont().deriveFont(20f));
        valueField.setFont(valueField.getFont().deriveFont(20f));

        add(nameField);
        add(numberField);
        add(valueField);

        add(nameLabel);
        add(numberLabel);
        add(valueLabel);

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
            dispose();
            //TODO: add to storage
        }

    }
}
