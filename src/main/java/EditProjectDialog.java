import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class EditProjectDialog extends NewWindowDialog
{
    private Client client;
    private MenuButton closeButton;
    private MenuButton saveButton;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel startLabel;
    private JLabel termLabel;
    private JLabel careLabel;
    private JLabel orderLabel;
    private JLabel priceLabel;
    private JLabel budgetLabel;
    private JLabel matCostLabel;
    private JLabel totalLabel;
    private JLabel statusLabel;

    private JTextField nameField;
    private JTextField startField;
    private JTextField termField;
    private JTextField careField;
    private JTextField orderField;
    private JTextField priceField;
    private JTextField budgetField;
    private JTextField matCostField;
    private JTextField totalFIeld;
    private JTextField statusField;

    private String currentProject;

    EditProjectDialog(Client client,String currentProject)
    {
        this.client = client;
        this.currentProject = currentProject;
        buildDialog();
        makeGui();
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
    }


    @Override
    void makeGui()
    {

        statusLabel = new JLabel("STATUS:");
        statusField = new JTextField("",30);
        statusLabel.setBounds(150,460,140,40);
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        statusField.setBounds(300,465,200,30);
        add(statusField);
        add(statusLabel);

/*
        nameLabel = new JLabel("PROJEKT: "+currentProject);
        startLabel = new JLabel("DATA ROZPOCZĘCIA:");
        termLabel = new JLabel("TERMIN:");
        careLabel = new JLabel("ZARZĄDCA:");
        orderLabel = new JLabel("ZAMAWIAJĄCY:");
        priceLabel = new JLabel("WARTOŚĆ:");
        budgetLabel = new JLabel("BUDŻET:");
        matCostLabel = new JLabel("KOSZT MATERIAŁÓW:");
        totalLabel = new JLabel("KOSZT CAŁKOWITY:");




        startField = new JTextField("", 15);
        termField = new JTextField("", 15);
        careField = new JTextField("",30);
        orderField = new JTextField("",30);
        priceField = new JTextField("",30);
        budgetField = new JTextField("",30);
        matCostField = new JTextField("",30);
        totalFIeld = new JTextField("",30);


        nameLabel.setBounds(100,10,600,50);
        nameLabel.setFont(nameLabel.getFont().deriveFont(30f));



        startLabel.setBounds(150,140,140,40);
        termLabel.setBounds(150,180,140,40);
        careLabel.setBounds(150,220,140,40);
        orderLabel.setBounds(150,260,140,40);
        priceLabel.setBounds(150,300,140,40);
        budgetLabel.setBounds(150,340,140,40);
        matCostLabel.setBounds(150,380,140,40);
        totalLabel.setBounds(150,420,140,40);



        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        termLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        careLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        budgetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        matCostLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);



        startField.setBounds(300,145,200,30);
        termField.setBounds(300,185,200,30);
        careField.setBounds(300,225,200,30);
        orderField.setBounds(300,265,200,30);
        priceField.setBounds(300,305,200,30);
        budgetField.setBounds(300,345,200,30);
        matCostField.setBounds(300,385,200,30);
        totalFIeld.setBounds(300,425,200,30);



        startField.setFont(startField.getFont().deriveFont(20f));

        termField.setFont(termField.getFont().deriveFont(20f));


        add(startField);
        add(termField);
        add(careField);
        add(orderField);
        add(priceField);
        add(budgetField);
        add(matCostField);
        add(totalFIeld);


        add(nameLabel);
        add(matCostLabel);
        add(totalLabel);
        add(startLabel);
        add(termLabel);
        add(careLabel);
        add(orderLabel);
        add(priceLabel);
        add(budgetLabel);

*/



        closeButton = new MenuButton("ZAMKNIJ");
        closeButton.setBounds(590,540,200,50);
        add(closeButton);
        closeButton.addActionListener(this);
        saveButton = new MenuButton("ZAPISZ");
        saveButton.setBounds(590,400,200,50);
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

            if(statusField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Prosze wprowadzić status!");

            }
            else
            {
                client.server.setProjectStatus(currentProject,statusField.getText());

                client.projectsWindowFrame.dispose();
                try
                {
                    client.setProjectsWindow();
                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
                dispose();
            }
        }

    }
}
