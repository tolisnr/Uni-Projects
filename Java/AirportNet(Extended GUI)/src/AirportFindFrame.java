import javax.swing.*;
import java.awt.event.*;

public class AirportFindFrame extends JFrame{
    private JPanel panel1, panel2, panel3;
    private JTextField textField;
    private JButton button, visualizeButton;

    public AirportFindFrame() {
        super("Find Airport");
        this.setSize(500, 200);

        panel1 = new JPanel();
        textField = new JTextField(10);
        button = new JButton("Find");
        panel1.add(textField);
        panel1.add(button);
        panel1.setMaximumSize(panel1.getPreferredSize());
        // We set the maximum size of the panel to be the same as the preferred size
        // so as to reduce the size of the panel

        panel2 = new JPanel();
        visualizeButton = new JButton("Visualize Network");
        panel2.add(visualizeButton);
        panel2.setMaximumSize(panel2.getPreferredSize());
        // We do the same for the second panel

        panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.add(panel1);
        panel3.add(panel2);

        ButtonListener listener = new ButtonListener();
        button.addActionListener(listener);
        textField.addActionListener(listener);
        visualizeButton.addActionListener(listener);

        this.setContentPane(panel3);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* This constructor creates a JFrame that has a text field, a button
         * and a Visualize Network button
         * The text field is where the user will enter the name of the city
         * The button is used to find the airport that is in the city
         * The Visualize Network button is used to create a new VisualizeFrame object
        */
    }

    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == visualizeButton) {
                new VisualizeFrame();
            } 
            else {
                String text = textField.getText();
                Airport airport = CentralRegistry.getAirport(text);
                if (airport != null) {
                    new AirportPage(airport);
                } else {
                    JOptionPane.showMessageDialog(null, text + " does not have an airport");
                }
            }
        }
        /* This method is called when a button or the text field is pressed
         * If the Visualize Network button is pressed, a new VisualizeFrame object is created
         * If the Find button or the text field is pressed, the text in the text field is
         * compared to the names of the airports in the Central Registry
         * If there is an airport with the same name as the text in the text field, a new
         * AirportPage object is created
         * If there is no airport with the same name as the text in the text field, a message
         * is displayed to the user
        */
    }
}

