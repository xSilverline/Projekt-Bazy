import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddRequestDialog extends NewWindowDialog
{
    private Client client;
    private MenuButton addButton;
    private MenuButton closeButton;

    private JLabel projectLabel;
    private JLabel materialLabel;
    private JLabel potrzLabel;
    private JLabel zgromLabel;
    private JLabel valueLabel;

    private JTextField materialField;
    private JTextField potrzField;
    private JTextField zgromField;
    private JTextField valueField;
    private JTextField projectField;
    String pro;

    AddRequestDialog(Client client,String pro)
    {
        this.client=client;
        this.pro = pro;
        buildDialog();
        makeGui();
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
    }

    @Override
    void makeGui()
    {
        JLabel titleLabel = new JLabel("DODAWANIE DO POTRZEB");
        titleLabel.setBounds(100,10,600,50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(40f));
        add(titleLabel);

        projectLabel = new JLabel("Projekt:\t"+pro);
        materialLabel = new JLabel("Materiał");
        potrzLabel = new JLabel("Potrzebne");
        zgromLabel = new JLabel("Zgromadzono");
        valueLabel = new JLabel("Wartość");

        materialField = new JTextField("",30);
        potrzField = new JTextField("",10);
        zgromField = new JTextField("",10);
        valueField = new JTextField("",10);
        projectField = new JTextField("",30);

        projectLabel.setBounds(150,140,140,50);
        materialLabel.setBounds(150,200,140,50);
        potrzLabel.setBounds(150,260,140,50);
        zgromLabel.setBounds(150,320,140,50);
        valueLabel.setBounds(150,380,140,50);

        add(materialLabel);
        add(potrzLabel);
        add(zgromLabel);
        add(valueLabel);
        add(projectLabel);

        projectField.setBounds(300,150,200,30);
        materialField.setBounds(300,210,200,30);
        potrzField.setBounds(300,270,200,30);
        zgromField.setBounds(300,330,200,30);
        valueField.setBounds(300,390,200,30);


        add(materialField);
        add(zgromField);
        add(potrzField);
        add(valueField);

        projectLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        materialLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        potrzLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        zgromLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);


        materialField.setFont(materialField.getFont().deriveFont(20f));
        potrzField.setFont(potrzField.getFont().deriveFont(20f));
        zgromField.setFont(zgromField.getFont().deriveFont(20f));
        valueField.setFont(valueField.getFont().deriveFont(20f));


        addButton = new MenuButton("DODAJ");
        closeButton = new MenuButton("ZAMKNIJ");

        addButton.setBounds(300,480,200,50);
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
            if( materialField.getText().isEmpty() || potrzField.getText().isEmpty() || zgromField.getText().isEmpty()|| valueField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Błąd danych - uzupełnij");
            }
            else
            {
                int x = Integer.parseInt(potrzField.getText()) - Integer.parseInt(zgromField.getText());
                client.server.addRequired(pro,materialField.getText(),potrzField.getText(),zgromField.getText(),Integer.toString(x),valueField.getText());
                dispose();
                client.requestsWindowFrame.dispose();
                try
                {
                    client.setRequestsWindow();
                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }


            }

        }

    }
}
