import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditCrewDialog extends NewWindowDialog
{
    private Client client;
    private MenuButton closeButton;
    private MenuButton saveButton;

    private JLabel nameLabel;
    private JLabel posLabel;
    private JLabel payLabel;

    private JTextField posField;
    private JTextField payField;

    private String currentWorker="None";

    EditCrewDialog(Client client)
    {
        this.client = client;
        buildDialog();
        makeGui();
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
    }

    @Override
    void makeGui()
    {
        nameLabel = new JLabel(currentWorker);

        posLabel = new JLabel("STANOWISKO:");
        payLabel = new JLabel("PENSJA:");

        nameLabel.setBounds(100,10,600,50);
        nameLabel.setFont(nameLabel.getFont().deriveFont(30f));

        posLabel.setBounds(150,280,140,40);
        payLabel.setBounds(150,320,140,40);

        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
;
        posLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        payLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        posField = new JTextField("", 15);
        payField = new JTextField("",30);

        posField.setBounds(300,285,200,30);
        payField.setBounds(300,325,200,30);

        add(payField);
        add(posField);

        add(nameLabel);
        add(posLabel);
        add(payLabel);

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
            dispose();
        }
    }
}
