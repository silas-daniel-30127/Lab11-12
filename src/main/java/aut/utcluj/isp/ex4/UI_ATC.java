package aut.utcluj.isp.ex4;

import javax.swing.*;
import java.awt.*;

public class UI_ATC extends JFrame {
    private final AirplaneTicketController airplaneTicketController;
    private JButton buyTicketButton;
    private JTextField customerTextField;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JTextField destinationTextField;
    private JLabel errorMessageLabel;
    private JTextField cancelCustomerTextField;
    private JLabel cancelMessageLabel;
    private JButton showButton;
    private JTextArea showTextField;
    private JTextField idToShowTextField;
    private JButton cancelTicketButton;
    private JButton changeButton;
    private JTextField idToChangeTextField;
    private JTextField newCustomerTextField;
    private JLabel changeMessageLabel;


    public UI_ATC(AirplaneTicketController airplaneTicketController) {
        this.airplaneTicketController = airplaneTicketController;
        initFrame();
        initHandlers();
    }

    private void initFrame() {
        setTitle("Aircraft Traffic Controller");

        setSize(700, 200);
        setBackground(Color.gray);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        //Create pages
        createPage1();
        createPage2();
        createPage3();
        createPage4();


        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Buy", panel1);
        tabbedPane.addTab("Cancel", panel2);
        tabbedPane.addTab("Change", panel3);
        tabbedPane.addTab("Get info", panel4);

        topPanel.add(tabbedPane, BorderLayout.CENTER);
    }

    private void createPage1() {
        panel1 = new JPanel();
        panel1.setLayout(null);

        JLabel label1 = new JLabel("Destination:");
        label1.setBounds(10, 15, 150, 20);
        panel1.add(label1);

        destinationTextField = new JTextField();
        destinationTextField.setBounds(10, 35, 150, 20);
        panel1.add(destinationTextField);

        JLabel label2 = new JLabel("Customer ID:");
        label2.setBounds(10, 60, 150, 20);
        panel1.add(label2);

        this.customerTextField = new JTextField();
        this.customerTextField.setBounds(10, 85, 150, 20);
        panel1.add(this.customerTextField);

        this.buyTicketButton = new JButton("Buy Ticket");
        this.buyTicketButton.setBounds(180, 35, 150, 20);
        panel1.add(this.buyTicketButton);

        this.errorMessageLabel = new JLabel();
        this.errorMessageLabel.setBounds(180, 85, 300, 20);
        this.errorMessageLabel.setVisible(false);
        panel1.add(errorMessageLabel);

    }

    private void createPage2() {
        panel2 = new JPanel();
        panel2.setLayout(null);

        JLabel label1 = new JLabel("ID:");
        label1.setBounds(10, 15, 150, 20);
        panel2.add(label1);

        cancelCustomerTextField = new JTextField("");
        cancelCustomerTextField.setBounds(90, 15, 125, 20);
        panel2.add(cancelCustomerTextField);

        this.cancelTicketButton = new JButton("Cancel Ticket");
        this.cancelTicketButton.setBounds(90, 65, 125, 20);
        panel2.add(cancelTicketButton);


        this.cancelMessageLabel = new JLabel();
        this.cancelMessageLabel.setBounds(10, 85, 300, 20);
        this.cancelMessageLabel.setVisible(false);
        panel2.add(cancelMessageLabel);
    }

    private void createPage3() {
        panel3 = new JPanel();
        panel3.setLayout(null);

        JLabel label1 = new JLabel("ID:");
        label1.setBounds(15, 15, 150, 20);
        panel3.add(label1);

        idToChangeTextField = new JTextField("");
        idToChangeTextField.setBounds(90, 15, 125, 20);
        panel3.add(idToChangeTextField);

        JLabel label2 = new JLabel("Customer:");
        label2.setBounds(15, 40, 150, 20);
        panel3.add(label2);

        newCustomerTextField = new JTextField("");
        newCustomerTextField.setBounds(90, 40, 125, 20);
        panel3.add(newCustomerTextField);

        this.changeButton = new JButton("Change");
        this.changeButton.setBounds(90, 65, 125, 20);
        panel3.add(changeButton);


        this.changeMessageLabel = new JLabel();
        this.changeMessageLabel.setBounds(10, 85, 150, 20);
        this.changeMessageLabel.setVisible(false);
        panel2.add(changeMessageLabel);
    }

    private void createPage4() {
        panel4 = new JPanel();
        panel4.setLayout(null);

        JLabel jLabel = new JLabel("ID: ");
        jLabel.setBounds(15, 15, 20, 20);
        panel4.add(jLabel);

        this.idToShowTextField = new JTextField();
        this.idToShowTextField.setBounds(35, 15, 60, 20);
        panel4.add(this.idToShowTextField);

        this.showButton = new JButton("Show");
        this.showButton.setBounds(15, 40, 80, 40);
        panel4.add(this.showButton);

        this.showTextField = new JTextArea();
        this.showTextField.setBounds(115, 15, 250, 100);
        this.showTextField.setColumns(2);
        panel4.add(this.showTextField);
    }

    private void initHandlers() {
        this.buyTicketButton.addActionListener(e -> {
            String id = this.customerTextField.getText();
            String destination = this.destinationTextField.getText();
            try {
                airplaneTicketController.buyTicket(destination, id);
                setErrorMessageLabel(id + " had bought a ticket to " + destination + " !");
            } catch (NoTicketAvailableException ex) {
                setErrorMessageLabel("No more tickets available to " + destination);
            } catch (NoDestinationAvailableException ex) {
                setErrorMessageLabel("No ticket to " + destination);
            }
        });

        this.cancelTicketButton.addActionListener(e -> {
            String id = this.cancelCustomerTextField.getText();
            try {
                airplaneTicketController.cancelTicket(id);
                AirplaneTicket airplaneTicket = airplaneTicketController.getTicketDetails(id);
                setCancelMessageLabel(airplaneTicket.getCustomerId() + "'s ticket to " + airplaneTicket.getDestination() + " has been canceled!");
            } catch (NoTicketAvailableException | TicketNotAssignedException ex) {
                setCancelMessageLabel(id + " has no active tickets!");
            }
        });

        this.changeButton.addActionListener(e -> {
            String id = this.idToChangeTextField.getText();
            String customerNextId = this.newCustomerTextField.getText();
            try {
                airplaneTicketController.changeTicketCustomerId(id, customerNextId);
                setChangeMessageLabel("Ticket customer has been changed!");
            } catch (NoTicketAvailableException | TicketNotAssignedException ex) {
                setChangeMessageLabel("Ticket not available or not assigned");
            }
        });

        this.showButton.addActionListener(e -> {
            String id = this.idToShowTextField.getText();
            AirplaneTicket airplaneTicket = airplaneTicketController.getTicketDetails(id);
            String text = "Customer: " + airplaneTicket.getCustomerId() + "\n" +
                    "Destination: " + airplaneTicket.getDestination() + "\n" +
                    "Status: " + airplaneTicket.getStatus() + "\n" +
                    "Price: " + airplaneTicket.getPrice();

            this.showTextField.setText(text);
        });
    }

    private void setErrorMessageLabel(String messageText) {
        errorMessageLabel.setText(messageText);
        errorMessageLabel.setVisible(true);
    }

    private void setCancelMessageLabel(String messageText) {
        cancelMessageLabel.setText(messageText);
        cancelMessageLabel.setVisible(true);
    }

    private void setChangeMessageLabel(String messageText) {
        changeMessageLabel.setText(messageText);
        changeMessageLabel.setVisible(true);
    }

}