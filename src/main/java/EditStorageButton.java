import java.awt.event.ActionEvent;

public class EditStorageButton extends NewWindowDialog
{
    private Client client;
    private MenuButton closeButton;

    EditStorageButton(Client client)
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
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == closeButton);
        {
            dispose();
        }

    }
}
