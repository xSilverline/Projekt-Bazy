import java.awt.event.ActionEvent;

public class EditRequestDialog extends NewWindowDialog
{
    private Client client;
    private MenuButton closeButton;
    private MenuButton saveButton;

    EditRequestDialog(Client client)
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
