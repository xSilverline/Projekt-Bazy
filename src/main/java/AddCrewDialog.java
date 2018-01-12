import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddCrewDialog extends NewWindowDialog
{
    private Client client;
    private MenuButton addButton;
    private MenuButton closeButton;


    private JLabel nameLabel;
    private JLabel surLabel;
    private JLabel posLabel;
    private JLabel payLabel;


    private JTextField nameField;
    private JTextField surField;
    private JTextField posField;
    private JTextField payField;


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

        nameLabel.setBounds(150,200,140,40);
        surLabel.setBounds(150,240,140,40);
        posLabel.setBounds(150,280,140,40);
        payLabel.setBounds(150,320,140,40);

        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        surLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        posLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        payLabel.setHorizontalAlignment(SwingConstants.RIGHT);


        nameField = new JTextField("", 30);
        surField = new JTextField("", 15);
        posField = new JTextField("", 15);
        payField = new JTextField("",30);

        nameField.setBounds(300,205,200,30);
        surField.setBounds(300,245,200,30);
        posField.setBounds(300,285,200,30);
        payField.setBounds(300,325,200,30);

        add(nameField);
        add(surField);
        add(payField);
        add(posField);

        add(nameLabel);
        add(surLabel);
        add(posLabel);
        add(payLabel);


        addButton = new MenuButton("DODAJ");
        closeButton = new MenuButton("ZAMKNIJ");

        addButton.setBounds(300,400,200,50);
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
            dispose();
            //TODO: add to storage
        }

    }
}
