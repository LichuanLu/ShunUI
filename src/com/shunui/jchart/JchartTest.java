package com.shunui.jchart;

import javax.swing.JFrame;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.ui.RefineryUtilities;

import com.shunui.help.GlobalHelper;
import com.shunui.model.WmparcStats;


public class JchartTest {

	public static void main(String args[]) {
		
		GlobalHelper globalObj = GlobalHelper.getInstance();
		
		//DefaultKeyedValues defaultkeyedvalues = new DefaultKeyedValues();
		//add the test data to global object
		globalObj.dataChart.add(new WmparcStats(2645D,"wm-lh-bankssts",2040.0D,2878.0000D,25D));
		globalObj.dataChart.add(new WmparcStats(2608D,"wm-lh-caudalanteriorcingulate",1900.0000D,2960.0000,21D));
		globalObj.dataChart.add(new WmparcStats(6227D,"wm-lh-caudalmiddlefrontal",5700.12D,6429.0000,15D));
		globalObj.dataChart.add(new WmparcStats(1736D,"wm-lh-cuneus",1500D,2000.0000D,11D));
		
		Display display = new Display();
        Shell shell = new Shell(display);
		
        //new chart class and show the panel
		JFrame.setDefaultLookAndFeelDecorated(true);
		JchartBeta shunbeta = new JchartBeta(
				"Shun Chart Beta","bar", shell);
		//for the line chart
		//JchartBeta shunbeta = new JchartBeta("Shun Chart Beta","line");
		shunbeta.pack();
		RefineryUtilities.centerFrameOnScreen(shunbeta);
		shunbeta.setVisible(true);
	}
}
