import java.awt.event.ActionEvent;

public class AddCrewDialog extends NewWindowDialog
{
    private Client client;
    private MenuButton addButton;
    private MenuButton closeButton;

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
