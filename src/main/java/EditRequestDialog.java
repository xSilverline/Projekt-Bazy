import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditRequestDialog extends NewWindowDialog
{
    private Client client;
    private MenuButton closeButton;
    private MenuButton saveButton;

    private JLabel projectLabel;
    private JLabel materialLabel;
    private JLabel potrzLabel;
    private JLabel zgromLabel;
    private JLabel valueLabel;

    private JTextField materialField;
    private JTextField potrzField;
    private JTextField zgromField;
    private JTextField valueField;

    private String currentProject;

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
        projectLabel = new JLabel("PROJEKT: "+currentProject);
        materialLabel = new JLabel("MATERIAL");
        potrzLabel = new JLabel("POTRZEBNE");
        zgromLabel = new JLabel("ZGROMADZONO");
        valueLabel = new JLabel("WARTOŚĆ");

        materialField = new JTextField("",30);
        potrzField = new JTextField("",10);
        zgromField = new JTextField("",10);
        valueField = new JTextField("",10);

        projectLabel.setBounds(200,10,400,50);
        projectLabel.setFont(projectLabel.getFont().deriveFont(30f));
        projectLabel.setHorizontalAlignment(SwingConstants.CENTER);


        materialLabel.setBounds(150,200,140,50);
        potrzLabel.setBounds(150,260,140,50);
        zgromLabel.setBounds(150,320,140,50);
        valueLabel.setBounds(150,380,140,50);

        add(materialLabel);
        add(potrzLabel);
        add(zgromLabel);
        add(valueLabel);
        add(projectLabel);


        materialField.setBounds(300,210,200,30);
        potrzField.setBounds(300,270,200,30);
        zgromField.setBounds(300,330,200,30);
        valueField.setBounds(300,390,200,30);


        add(materialField);
        add(zgromField);
        add(potrzField);
        add(valueField);

        materialLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        potrzLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        zgromLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        materialField.setFont(materialField.getFont().deriveFont(20f));
        potrzField.setFont(potrzField.getFont().deriveFont(20f));
        zgromField.setFont(zgromField.getFont().deriveFont(20f));
        valueField.setFont(valueField.getFont().deriveFont(20f));

        saveButton = new MenuButton("ZAPISZ");
        closeButton = new MenuButton("ZAMKNIJ");

        saveButton.setBounds(300,480,200,50);
        closeButton.setBounds(590,540,200,50);
        add(saveButton);
        add(closeButton);

        saveButton.addActionListener(this);
        closeButton.addActionListener(this);
    }

    void setCurrentProject(String currentProject)
    {
        this.currentProject = currentProject;
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
