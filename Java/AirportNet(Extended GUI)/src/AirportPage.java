import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AirportPage extends JFrame{
    private Airport airport;
    private JPanel containerPanel, detailsPanel, textPanel, buttonPanel, panel;
    private JTextField nameField, codeField, cityField, countryField, cityField2;
    private JTextArea area1, area2;
    private JList<String> list;
    private JButton findButton, backButton;

    public AirportPage(Airport airport) {
        super("Airport Page");
        this.setSize(1000, 400);
        this.airport = airport;

        makeTextFields();
        makeList();

        containerPanel.add(list);
        containerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        makeDetails();

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // The panel uses a BoxLayout to display the components vertically

        panel.add(containerPanel);
        panel.add(detailsPanel);
        panel.add(textPanel);
        panel.add(buttonPanel);

        this.setContentPane(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void makeDetails() {

        detailsPanel = new JPanel();

        cityField2 = new JTextField(15);
        findButton = new JButton("Find Flights");

        ButtonListener listener = new ButtonListener();
        findButton.addActionListener(listener);
        cityField2.addActionListener(listener);

        detailsPanel.add(cityField2);
        detailsPanel.add(findButton);

        /*
         * The detailsPanel is used to display the text field and the button
         * The text field and the button have the same ActionListener so as to 
         * implement the same functionality when the user presses the enter key
         * or clicks the button
        */

        area1 = new JTextArea(10, 30);
        area2 = new JTextArea(10, 30);

        area1.setEditable(false);
        area2.setEditable(false);

        textPanel = new JPanel();
        textPanel.add(area1);
        textPanel.add(area2);

        /*
         * The textPanel is used to display the text areas
         * The text areas are used to display the direct and indirect flights
         * The text areas are not editable
        */

        backButton = new JButton("Back to Search Screen");
        backButton.addActionListener(listener);

        buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        /*
         * The buttonPanel is used to display the back button
         * The back button is used to close the AirportPage
        */
    }

    private void makeTextFields() {
        containerPanel = new JPanel();
        nameField = new JTextField(airport.getName(), 15);
        codeField = new JTextField(airport.getCode(), 15);
        cityField = new JTextField(airport.getCity(), 15);
        countryField = new JTextField(airport.getCountry(), 15);

        nameField.setEditable(false);
        codeField.setEditable(false);
        cityField.setEditable(false);
        countryField.setEditable(false);

        containerPanel.add(nameField);
        containerPanel.add(codeField);
        containerPanel.add(cityField);
        containerPanel.add(countryField);

        // Until here, the code creates a JPanel and 4 JTextFields
        // The JTextFields are used to display the airport's name, code, city and country
    }

    private void makeList() {

        list = new JList<>();
        HashSet<String> companies = new HashSet<>();
        DefaultListModel<String> model = new DefaultListModel<String>();

        for(String company : airport.getCompanies()) {
            companies.add(company);
        }

        for(String company : companies) {
            model.addElement(company);
        }
        list.setModel(model);

        // A list is created to display the companies that use the airport
        // The companies are stored in a HashSet to avoid duplicates
    }

    class ButtonListener implements ActionListener{
            
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == backButton) {
                dispose();
            } 
            else {
                String text = cityField2.getText();
                Airport b = CentralRegistry.getAirport(text);
                if(b != null && airport != b) {
                    area1.setText("");
                    area2.setText("");
                    area1.append(CentralRegistry.getDirectFlightsDetails(airport, b));
                    area2.append(CentralRegistry.getInDirectFlightsDetails(airport, b));
                    // In this line we call the method for opening the file with the details
                    new FileCreation(airport, b);
                }
                else if(airport == b) {
                    JOptionPane.showMessageDialog(null, "Arrival and departure city cannot be the same!");
                }
                else {
                    JOptionPane.showMessageDialog(null, text + " does not have an airport");
                }
            }
        }
        /* This method is called when the button is clicked or the enter key is pressed
         * If the button is the back button, the AirportPage is closed
         * If the button is the find button, the text from the text field is used to find the airport
         * If the airport is found, the details of the direct and indirect flights are displayed
        */
    }
}
