package stocks.view.portfolio;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 * The following class represents drawing of the graph for describing the valuation of the
 * portfolio.
 */
public class Graph extends JPanel {
  /**
   * The following constructor initializes the Graph constructor.
   * @param chartTitle the title of the graph.
   * @param XAxis the x axis of the graph.
   * @param YAxis the y axis of the graph.
   * @param dataset the dataset of which the graph is to be drawn.
   */
  public Graph(String chartTitle, String XAxis, String YAxis,
          DefaultCategoryDataset dataset) {
    JFreeChart lineChart = ChartFactory.createLineChart3D(
            chartTitle,
            XAxis,YAxis,
            dataset,
            PlotOrientation.VERTICAL,
            false,false,false);
    ChartPanel chartPanel = new ChartPanel( lineChart );
    chartPanel.setPreferredSize( new java.awt.Dimension( 1040 , 700 ) );
    this.add(chartPanel,BorderLayout.CENTER);
    this.validate();
  }
}