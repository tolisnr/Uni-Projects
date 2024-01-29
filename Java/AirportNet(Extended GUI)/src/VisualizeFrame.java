import javax.swing.*;
import java.awt.*;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.decorators.*;
import edu.uci.ics.jung.algorithms.shortestpath.*;

public class VisualizeFrame extends JFrame{
    private Graph<String, Flight> g = new UndirectedSparseGraph<>();
    private JPanel panel = new JPanel(new BorderLayout());
    private JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JLabel label;

    public VisualizeFrame() {
        super("City Airport Connections Network");
        this.setSize(400, 400);

        for(Airport airport : CentralRegistry.getAirportList()) {
            g.addVertex(airport.getCity());
        }

        for(Flight flight : CentralRegistry.getFlightList()) {
            g.addEdge(flight, flight.getAirportA().getCity(), flight.getAirportB().getCity());
        }

        label = new JLabel("Diameter: " + DistanceStatistics.diameter(g));
        labelPanel.add(label);
        // This line creates a label that shows the diameter of the graph.

        VisualizationImageServer<String, Flight> vs = new VisualizationImageServer<String, Flight>
        (new CircleLayout<String, Flight>(g), new Dimension(350, 250));

        vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());
        // This line sets the vertex labels to be the cities. The toString() method
        // of the Airport class returns the name of the city.

        panel.add(vs, BorderLayout.CENTER);
        panel.add(labelPanel, BorderLayout.PAGE_END);

        this.setContentPane(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    /*
     * In this constructor we create a graph with the names of the cities as
     * vertices and the flights as edges. Then we create a VisualizationImageServer 
     * object to visualize the graph. We set the vertex labels to be the cities and we
     * add the VisualizationImageServer object to the panel. 
     * 
     * The vertices are not the objects of the Airport class, because the objects
     * of the Airport class don't have a spesific address in the memory. So, we
     * use the names of the cities as vertices.
    */
}
