package cs311.hw4;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.util.List;

/**
 * Created by taylorsdugger on 10/7/16.
 */
public class Part4Test extends ApplicationFrame {

    public Part4Test(final String title){
        super(title);

        final XYSeries graph = new XYSeries("multiply()");

        SlowMatrixFactory in = new SlowMatrixFactory();
        MeasureTimeComplexity timeComp = new MeasureTimeComplexity();
        int times1 = timeComp.normalize((in.createRandom(2)), 2);


        //2

        List<? extends IResult> times2 = timeComp.measure(in, times1, 2, 100, 1);

        //3

        System.out.print(times1);

        for(int i=0; i<times2.size(); i++){
            graph.add(times2.get(i).getSize(),times2.get(i).getTime());
        }

        final XYSeriesCollection data = new XYSeriesCollection(graph);
        final JFreeChart c = ChartFactory.createXYLineChart("multiply()", "Run Time", "Size", data, PlotOrientation.VERTICAL, true, true, false);

        final ChartPanel cPanel = new ChartPanel(c);
        cPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(cPanel);
    }

    public static void main(final String[] args) {

        final Part4Test runIt = new Part4Test("Part 4");
        runIt.pack();
        RefineryUtilities.centerFrameOnScreen(runIt);
        runIt.setVisible(true);
    }
}
