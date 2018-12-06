package stocks.view.portfolio;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graph extends JPanel {
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