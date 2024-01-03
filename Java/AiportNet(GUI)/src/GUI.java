import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame{
    private JPanel panel;
    private JTextField textField;
    private JButton button;

    public GUI() {
        super("Find Airport");
        this.setSize(500, 200);

        panel = new JPanel();
        textField = new JTextField(10);
        button = new JButton("Find");

        panel.add(textField);
        panel.add(button);

        ButtonListener listener = new ButtonListener();
        button.addActionListener(listener);
        textField.addActionListener(listener);

        this.setContentPane(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* This constructor creates a JFrame that has a text field and a button
         * The text field is where the user will enter the name of the city
         * The button is used to find the airport that is in the city
        */
    }

    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String text = textField.getText();
            Airport airport = CentralRegistry.getAirport(text);
            if(airport != null) {
                new AirportPage(airport);
            }
            else {
                JOptionPane.showMessageDialog(null, text + " does not have an airport");
            }
        }
        /* This method is called when the button is clicked
         * It gets the text from the text field and uses it to find the airport
         * If the airport is found, it creates a new AirportPage object
         * If the airport is not found, it displays a message dialog 
        */
    }
}

