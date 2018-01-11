import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditStorageDialog extends NewWindowDialog
{
    private Client client;
    private MenuButton closeButton;
    private MenuButton saveButton;
    private String currentMaterial;

    EditStorageDialog(Client client)
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
        closeButton = new MenuButton("ZAMKNIJ");
        closeButton.setBounds(590,540,200,50);
        add(closeButton);
        closeButton.addActionListener(this);

        saveButton = new MenuButton("ZAPISZ");
        saveButton.setBounds(300,500,200,50);
        add(saveButton);
        saveButton.addActionListener(this);

        JLabel nameLabel = new JLabel("MATERIAŁ: "+currentMaterial);
        JLabel numberLabel = new JLabel("ILOŚĆ: ");
        JLabel valueLabel = new JLabel("WARTOŚĆ SZTUKI: ");
        JTextField numberField = new JTextField("",5);
        JTextField valueField = new JTextField("",10);

        nameLabel.setBounds(100,10,600,50);
        nameLabel.setFont(nameLabel.getFont().deriveFont(30f));
        add(nameLabel);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        numberLabel.setBounds(100,200,200,50);
        numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        numberLabel.setFont(numberLabel.getFont().deriveFont(20f));
        add(numberLabel);

        numberField.setBounds(310,200,200,50);
        numberField.setFont(numberField.getFont().deriveFont(20f));
        add(numberField);

        valueLabel.setFont(valueLabel.getFont().deriveFont(20f));
        valueLabel.setBounds(100,300,200,50);
        valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(valueLabel);

        valueField.setBounds(310,300,200,50);
        valueField.setFont(valueField.getFont().deriveFont(20f));
        add(valueField);

    }
    void setCurrentMaterial(String currentMaterial)
    {
        this.currentMaterial = currentMaterial;
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
