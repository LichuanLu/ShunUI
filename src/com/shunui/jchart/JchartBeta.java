//chart main class
package com.shunui.jchart;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

import com.shunui.help.GlobalHelper;
import com.shunui.model.WmparcStats;

public class JchartBeta extends ApplicationFrame {
	
	//chart data
	private CategoryDataset dataSetChart1Vo;
	private CategoryDataset dataSetChart1Max;
	private CategoryDataset dataSetChart1Min;
	private CategoryDataset dataSetChart2;
	private DefaultCategoryDataset dataSetChart1Vo2;
	private GlobalHelper globalObj = GlobalHelper.getInstance();
	

    public JchartBeta(String s, String type, Shell shell) {
	super(s);
	// setContentPane(createDemoPanel());
	GlobalHelper globalObj = GlobalHelper.getInstance();
	this.getMax(globalObj.dataChart);
	if (type == "bar") {
		setContentPane(createDemoPanel1(globalObj.dataChart, shell));
	} else if (type == "line") {
		setContentPane(createDemoPanel2(globalObj.dataChart, shell));
	}
	// this.add(createDemoPanel1(globalObj.dataChart),BorderLayout.WEST);
	// this.add(createDemoPanel2(globalObj.dataChart),BorderLayout.EAST);
    }
	
	public Boolean getMax(ArrayList<WmparcStats> input){
		
		int len = input.size();
		for(int i=0;i<len;++i){
			if(input.get(i).volume_mm3 > globalObj.chart1_max){
				globalObj.chart1_max = input.get(i).volume_mm3;
			}
			if(input.get(i).zscore > globalObj.chart2_max){
				globalObj.chart2_max = input.get(i).zscore;
			}
		}
		return true;
	}
	
	//just for testing
	private static XYDataset createDataset1() {
		XYSeries xyseries = new XYSeries("Random Data 1");
		xyseries.add(1.0D, 181.80000000000001D);
		xyseries.add(2D, 167.30000000000001D);
		xyseries.add(3D, 153.80000000000001D);
		xyseries.add(4D, 167.59999999999999D);
		xyseries.add(5D, 158.80000000000001D);
		xyseries.add(6D, 148.30000000000001D);
		xyseries.add(7D, 153.90000000000001D);
		xyseries.add(8D, 142.69999999999999D);
		xyseries.add(9D, 123.2D);
		xyseries.add(10D, 131.80000000000001D);
		xyseries.add(11D, 139.59999999999999D);
		xyseries.add(12D, 142.90000000000001D);
		xyseries.add(13D, 138.69999999999999D);
		xyseries.add(14D, 137.30000000000001D);
		xyseries.add(15D, 143.90000000000001D);
		xyseries.add(16D, 139.80000000000001D);
		xyseries.add(17D, 137D);
		xyseries.add(18D, 132.80000000000001D);
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xyseries);
		return xyseriescollection;
	}
	//just for testing
	private static XYDataset createDataset2() {
		XYSeries xyseries = new XYSeries("Random Data 2");
		xyseries.add(1.0D, 429.60000000000002D);
		xyseries.add(2D, 323.19999999999999D);
		xyseries.add(3D, 417.19999999999999D);
		xyseries.add(4D, 624.10000000000002D);
		xyseries.add(5D, 422.60000000000002D);
		xyseries.add(6D, 619.20000000000005D);
		xyseries.add(7D, 416.5D);
		xyseries.add(8D, 512.70000000000005D);
		xyseries.add(9D, 501.5D);
		xyseries.add(10D, 306.10000000000002D);
		xyseries.add(11D, 410.30000000000001D);
		xyseries.add(12D, 511.69999999999999D);
		xyseries.add(13D, 611D);
		xyseries.add(14D, 709.60000000000002D);
		xyseries.add(15D, 613.20000000000005D);
		xyseries.add(16D, 711.60000000000002D);
		xyseries.add(17D, 708.79999999999995D);
		xyseries.add(18D, 501.60000000000002D);
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xyseries);
		return xyseriescollection;
	}
	//just for testing
	private static DefaultCategoryDataset createDataset3(){
			  DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		        dataset.addValue(200, "Plan", "Qinghua");  
		        dataset.addValue(360, "Plan", "Tianjing");  
		        dataset.addValue(100, "Plan", "Peking");  
		        dataset.addValue(280, "Plan", "Fudan");  
		        return dataset;  
    }  
	//just for testing
	private  DefaultCategoryDataset createDataset4(){
		  DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		  dataset.addValue(1050D, "Plan", "wm-lh-bankssts");  
		  dataset.addValue(1000D, "Plan", "wm-lh-caudalanteriorcingulate");  
		  dataset.addValue(4000D, "Plan", "wm-lh-caudalmiddlefrontal");  
		  dataset.addValue(5200D, "Plan", "wm-lh-cuneus"); 
		// dataset.addValue(3500D, "Test", "wm-lh-bankssts");  
	        return dataset;  
	}  
		
	private Boolean geneDataChart1(ArrayList<WmparcStats> dataInput){
		
		DefaultKeyedValues keyChart1Vo = new DefaultKeyedValues();
		DefaultKeyedValues keyChart1Max = new DefaultKeyedValues();
		DefaultKeyedValues keyChart1Min = new DefaultKeyedValues();
		for(WmparcStats temp:dataInput){
			keyChart1Vo.addValue(temp.structName, temp.volume_mm3);
			keyChart1Max.addValue(temp.structName, temp.normMax);
			keyChart1Min.addValue(temp.structName, temp.normMin);
		}
		
		//keyChart1Vo.addValue("wm-lh-bankssts", 2500D);
		dataSetChart1Vo = DatasetUtilities.createCategoryDataset("Real Data", keyChart1Vo);
		dataSetChart1Max = DatasetUtilities.createCategoryDataset("Max Data", keyChart1Max);
		dataSetChart1Min = DatasetUtilities.createCategoryDataset("Min Data", keyChart1Min);
		//dataSetChart1Vo = new DefaultCategoryDataset();  
		 //dataSetChart1Vo.addValue(2050D, "Plan", "wm-lh-bankssts");  
		// dataSetChart1Vo.addValue(3000D, "Plan", "wm-lh-caudalanteriorcingulate");  
		// dataSetChart1Vo.addValue(2000D, "Plan", "wm-lh-caudalmiddlefrontal");  
		// dataSetChart1Vo.addValue(1200D, "Plan", "wm-lh-cuneus"); 
		 //dataSetChart1Vo.addValue(1500D, "Test", "wm-lh-bankssts");  
		 
		 
		
		return true;  
	}
	private Boolean geneDataChart2(ArrayList<WmparcStats> dataInput){ 
		DefaultKeyedValues keyChart2 = new DefaultKeyedValues();
		for(WmparcStats temp:dataInput){
			keyChart2.addValue(temp.structName, temp.zscore);
		}
		dataSetChart2 = DatasetUtilities.createCategoryDataset("zscore", keyChart2);

		 return true;  
	}
	
	private JFreeChart createChart2(ArrayList<WmparcStats> dataInput) {
		this.geneDataChart2(dataInput);
		JFreeChart jfreechart = ChartFactory.createLineChart(
				"Chart2", "wm-name","zscore", dataSetChart2,
				PlotOrientation.VERTICAL, true, true, false);
		
		jfreechart.setBackgroundPaint(Color.white);
		CategoryPlot xyplot = (CategoryPlot) jfreechart.getPlot();
		xyplot.setBackgroundPaint(new Color(218,216,167));
		//xyplot.setBackgroundPaint(new Color(255,255,255));
		xyplot.setRangeGridlinePaint(new Color(1,52,64));
		 xyplot.setBackgroundAlpha(0.5F);
		CategoryAxis categoryaxis = xyplot.getDomainAxis(); 
		//categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI/6));
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI/2));
		categoryaxis.setLabelFont(new Font("SansSerif",Font.PLAIN,3));
		categoryaxis.setLowerMargin(.01);
		categoryaxis.setCategoryMargin(0.2);
		categoryaxis.setUpperMargin(.01);  
		NumberAxis rangeAxis = (NumberAxis) xyplot.getRangeAxis(); 
		int range_max = (int)Math.round(globalObj.chart2_max*1.3);
		//rangeAxis.setTickUnit(new NumberTickUnit(50, new DecimalFormat("0"))); 
		rangeAxis.setAutoRange(false); 
		rangeAxis.setRange(-1.5, 1.5); 

		
		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) xyplot
				.getRenderer();
		
	    //lineandshaperenderer.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] { 10.0F, 6.0F }, 0.0F));
	    //lineandshaperenderer.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] { 6.0F, 6.0F }, 0.0F));
	    //lineandshaperenderer.setSeriesStroke(2, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] { 2.0F, 6.0F }, 0.0F));
	    lineandshaperenderer.setSeriesStroke(0, new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
	    lineandshaperenderer.setSeriesShape(0, new Ellipse2D.Double(-2D, -2D, 4D, 4D)); 
	   // lineandshaperenderer.setSeriesPaint(0, new Color(33,38,64));
	    lineandshaperenderer.setSeriesPaint(0, new Color(0,57,255));
	    //LineAndShapeRenderer localLineAndShapeRenderer = (LineAndShapeRenderer)xyplot.getRenderer();
	    lineandshaperenderer.setBaseShapesVisible(true);
	    lineandshaperenderer.setBaseItemLabelsVisible(true);
	    lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	    lineandshaperenderer.setBaseItemLabelFont(new Font("SansSerif",Font.BOLD,12));
	    lineandshaperenderer.setSeriesToolTipGenerator(1,new CustomToolTipGenerator());
	    //lineandshaperenderer.setSeriesShape(0, new Rectangle(0, 0));
	    
	    /*
	    LegendTitle legendtitle = new LegendTitle(lineandshaperenderer);
		BlockContainer blockcontainer = new BlockContainer(
				new BorderArrangement());
		blockcontainer.add(legendtitle, null);
		//blockcontainer.add(new EmptyBlock(2000D, 0.0D));
		CompositeTitle compositetitle = new CompositeTitle(blockcontainer);
		compositetitle.setPosition(RectangleEdge.BOTTOM);
		jfreechart.addSubtitle(compositetitle);*/
		return jfreechart;
	}
	
	private JFreeChart createChart1(ArrayList<WmparcStats> dataInput) {
		
		this.geneDataChart1(dataInput);
		//DefaultCategoryDataset xydataset =  geneDataChart1(dataInput);
		JFreeChart jfreechart = ChartFactory.createLineChart(
				"Chart1","wm-name","volume_mm3", dataSetChart1Max,
				PlotOrientation.VERTICAL, true, true, false);
		//jfreechart.setPadding(padding)
		jfreechart.setBackgroundPaint(Color.white);
		CategoryPlot xyplot = (CategoryPlot) jfreechart.getPlot();
		xyplot.setBackgroundPaint(new Color(218,216,167));
		//xyplot.setBackgroundPaint(new Color(255,255,255));
		xyplot.setRangeGridlinePaint(new Color(1,52,64));
		CategoryAxis categoryaxis = xyplot.getDomainAxis(); 
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI/2));
		categoryaxis.setLabelFont(new Font("SansSerif",Font.PLAIN,3));
		categoryaxis.setLowerMargin(.01);
		categoryaxis.setCategoryMargin(0.2);
		categoryaxis.setUpperMargin(.01);  
		//NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
		//numberaxis.setAutoRangeIncludesZero(false);
		//NumberAxis numberaxis1 = new NumberAxis("Secondary");
		//numberaxis1.setAutoRangeIncludesZero(false);
		//xyplot.setRangeAxis(1, numberaxis1);		
		//xyplot.mapDatasetToRangeAxis(1, 1);

		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) xyplot
				.getRenderer();

		 Shape[] arrayOfShape = new Shape[3];
		    int[] arrayOfInt1 = { -3, 3, -3 };
		    int[] arrayOfInt2 = { -3, 0, 3 };
		    arrayOfShape[0] = new Polygon(arrayOfInt1, arrayOfInt2, 3);
		    arrayOfShape[1] = new Rectangle2D.Double(-2.0D, -3.0D, 3.0D, 6.0D);
		    arrayOfInt1 = new int[] { -3, 3, 3 };
		    arrayOfInt2 = new int[] { 0, -3, 3 };
		    arrayOfShape[2] = new Polygon(arrayOfInt1, arrayOfInt2, 3);
		    DefaultDrawingSupplier localDefaultDrawingSupplier = new DefaultDrawingSupplier(DefaultDrawingSupplier.DEFAULT_PAINT_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE, DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE, arrayOfShape);
		    
		    //xyplot.setOrientation(PlotOrientation.HORIZONTAL);
		    xyplot.setBackgroundAlpha(0.5F);
		   // xyplot.setForegroundAlpha(0.5F);
		    xyplot.setDrawingSupplier(localDefaultDrawingSupplier);
		   // lineandshaperenderer.setSeriesStroke(0, new BasicStroke(2.0F));
		    lineandshaperenderer.setSeriesPaint(0, new Color(255,61,127));
		    //lineandshaperenderer.setSeriesStroke(0, new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		   //lineandshaperenderer.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] { 6.0F, 6.0F }, 0.0F));
		    lineandshaperenderer.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] { 2.0F, 6.0F }, 0.0F));
		    lineandshaperenderer.setSeriesShape(0, new Ellipse2D.Double(-2D, -2D, 4D, 4D)); 
		    //LineAndShapeRenderer localLineAndShapeRenderer = (LineAndShapeRenderer)xyplot.getRenderer();
		    lineandshaperenderer.setBaseShapesVisible(true);
		    lineandshaperenderer.setDrawOutlines(true);
		    //lineandshaperenderer.setBaseFillPaint(Color.white);
		    //lineandshaperenderer.setSeriesStroke(0, new BasicStroke(3.0F));
		    //lineandshaperenderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));
		    lineandshaperenderer.setBaseItemLabelsVisible(false);
		    lineandshaperenderer.setBaseItemLabelFont(new Font("SansSerif",Font.PLAIN,10));
		    //lineandshaperenderer.setSeriesShape(0, new Ellipse2D.Double(-5D, -5D, 10D, 10D)); 

		    lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		
//		xylineandshaperenderer.setItemLabelsVisible(true);//设置项标签显示
//		xylineandshaperenderer.setBaseItemLabelsVisible(true);//基本项标签显示
//                    //上面这几句就决定了数据点按照设定的格式显示数据值  
//		xylineandshaperenderer.setBaseShapesFilled(Boolean.TRUE);//在数据点显示实心的小图标
//		xylineandshaperenderer.setBaseShapesVisible(true);//设置显示小图标
		
//		XYPointerAnnotation xypointerannotation = new XYPointerAnnotation(
//				"Annotation 1 (2.0, 167.3)", 2D, 167.30000000000001D,
//				-0.78539816339744828D);
//		xypointerannotation.setTextAnchor(TextAnchor.BOTTOM_LEFT);
//		xypointerannotation.setPaint(Color.red);
//		xypointerannotation.setArrowPaint(Color.red);
//		xylineandshaperenderer.addAnnotation(xypointerannotation);
		xyplot.setDataset(1, dataSetChart1Min);
		LineAndShapeRenderer lineandshaperenderermin = new LineAndShapeRenderer();
		//lineandshaperenderermin.setSeriesStroke(0, new BasicStroke(2.0F));
		lineandshaperenderermin.setSeriesPaint(0, new Color(63,184,175));

		//lineandshaperenderermin.setSeriesPaint(0, new Color(63,184,175));
		//lineandshaperenderermin.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] { 6.0F, 6.0F }, 0.0F));
		lineandshaperenderermin.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] { 2.0F, 6.0F }, 0.0F));
		lineandshaperenderermin.setSeriesShape(0, new Ellipse2D.Double(-2D, -2D, 4D, 4D)); 
		lineandshaperenderermin.setBaseShapesVisible(true);
		lineandshaperenderermin.setBaseItemLabelsVisible(false);
		lineandshaperenderermin.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		lineandshaperenderermin.setBaseItemLabelFont(new Font("SansSerif",Font.PLAIN,10));
		lineandshaperenderermin.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator(
			    "Series {0}, Category {1}, Value {2}", NumberFormat.getInstance()));
		xyplot.setRenderer(1, lineandshaperenderermin);
		
		xyplot.setDataset(2, dataSetChart1Vo);

		BarRenderer barrender = new BarRenderer();
		
		// 设置柱子的边框是否显示 
		barrender.setDrawBarOutline(false); 
		// 设置柱体宽度 
		barrender.setMaximumBarWidth(0.03); 
		// 设置柱体颜色 
	    // set up gradient paints for series...
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, new Color(0,57,255), 
            0.0f, 0.0f, new Color(35,12,207)
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, new Color(255,216,13), 
            0.0f, 0.0f, new Color(204,173,10)
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, Color.lightGray
        );
		
		barrender.setShadowVisible(false);
		barrender.setBaseItemLabelsVisible(true);
		barrender.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barrender.setBaseItemLabelFont(new Font("SansSerif",Font.BOLD,8));
		//barrender.setBaseItemLabelPaint(new Color(0,0,61));
		//barrender.setBaseItemLabelPaint(new Color(255,255,255));
		barrender.setBarPainter(new StandardBarPainter());
		//barrender.setItemMargin(-0.29D);
		//barrender.setSeriesPaint(0,new GradientPaint(0.0F,0.0F,Color.green,0.0F,0.0F,new Color(0,64,0)));
		//barrender.setSeriesPaint(0,new GradientPaint(0.0F,0.0F,new Color(148,242,233),0F,0F,new Color(255,255,255)));
		//barrender.setSeriesPaint(0,new GradientPaint(0.0F,0.0F,Color.red,0.0F,0.0F,new Color(64,0,0)));
		//barrender.setSeriesPaint(0,new Color(0,57,255));
		barrender.setSeriesPaint(0,gp0);
		//barrender.setSeriesPaint(1,new Color(255,216,13));
		barrender.setSeriesPaint(1,gp1);
		//ItemLabelPosition itemlabelposition = new ItemLabelPosition(ItemLabelAnchor.INSIDE12, TextAnchor.CENTER_RIGHT, TextAnchor.CENTER_RIGHT,
		//		-1.5707963267948966D);
		//barrender.setBasePositiveItemLabelPosition(itemlabelposition);
		ItemLabelPosition itemlabelposition1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT,
				-1.5707963267948966D);
		barrender.setPositiveItemLabelPositionFallback(itemlabelposition1);
		//barrender.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		//String tooltip = new StandardCategoryToolTipGenerator().generateToolTip(dataSetChart1Vo, 1, 1);
		barrender.setSeriesToolTipGenerator(1,new CustomToolTipGenerator());
		
//		xylineandshaperenderer1.setSeriesPaint(0, new Color(198, 219, 248)); 
//		xylineandshaperenderer1.setSeriesPaint(0, Color.black);
//		XYPointerAnnotation xypointerannotation1 = new XYPointerAnnotation(
//				"Annotation 2 (15.0, 613.2)", 15D, 613.20000000000005D,
//				1.5707963267948966D);
//		xypointerannotation1.setTextAnchor(TextAnchor.TOP_CENTER);
//		xylineandshaperenderer1.addAnnotation(xypointerannotation1);
		NumberAxis rangeAxis = (NumberAxis) xyplot.getRangeAxis(); 
		int range_max = (int)Math.round(globalObj.chart1_max*1.2);
		//rangeAxis.setTickUnit(new NumberTickUnit(50, new DecimalFormat("0"))); 
		rangeAxis.setAutoRange(false); 
		rangeAxis.setRange(0, range_max); 

		
		xyplot.setRenderer(2, barrender);

	     // r.setSeriesToolTipGenerator(0, tt1);
	     // r.setSeriesToolTipGenerator(1, tt2);
	     // plot.setRenderer(r);
		
		//BarRenderer barrender2 = new BarRenderer();
		//xyplot.setDataset(3, dataSetChart1Vo2);
		//barrender2.setBarPainter(new StandardBarPainter());
		//barrender2.setSeriesPaint(0,new Color(255,216,13,255));
		//barrender2.SET
		//xyplot.setRenderer(3, barrender2);
		
		//custom legend
		/*
		LegendTitle legendtitle = new LegendTitle(lineandshaperenderer);
		LegendTitle legendtitle1 = new LegendTitle(barrender);
		LegendTitle legendtitle2 = new LegendTitle(lineandshaperenderermin);
		BlockContainer blockcontainer = new BlockContainer(
				new BorderArrangement());
		
		blockcontainer.add(legendtitle, RectangleEdge.LEFT);
		blockcontainer.add(legendtitle1, RectangleEdge.RIGHT);
		blockcontainer.add(legendtitle2, null);
		blockcontainer.setPadding(5, 0, 0, 5);
		CompositeTitle compositetitle = new CompositeTitle(blockcontainer);
		compositetitle.setPosition(RectangleEdge.BOTTOM);
		
		//blockcontainer2.add(legendtitle2, RectangleEdge.LEFT);
		//CompositeTitle compositetitle2 = new CompositeTitle(blockcontainer2);

		jfreechart.addSubtitle(compositetitle);
		*/
		return jfreechart;
	}

	
	
	  public ChartPanel createDemoPanel1(ArrayList<WmparcStats> dataInput,
			    Shell shell) {
			// GlobalHelper globalObj = GlobalHelper.getInstance();
			JFreeChart jfreechart = createChart1(dataInput);
			jfreechart.addSubtitle(new TextTitle("Volume by function area"));
			jfreechart.setPadding(new RectangleInsets(10D, 5D, 5D, 10D));

			 ChartPanel chartPanel = new ChartPanel(jfreechart, true, true, true,
			 false, true);
			 chartPanel.setPreferredSize(new java.awt.Dimension(1200, 800));
			 return chartPanel;

			// JFreeChart chart = createChart(createDataset());

			//ChartComposite frame = new ChartComposite(shell, SWT.RESIZE,
			//	jfreechart, true);
			//frame.setSize(800, 600);
			// frame.pack();
			// shell.open();
			// while (!shell.isDisposed()) {
			// if (!display.readAndDispatch())
			// display.sleep();
			// }
			// shell.open();
			//return frame;
		    }

	  public ChartPanel createDemoPanel2(ArrayList<WmparcStats> dataInput,
			    Shell shell) {
			// GlobalHelper globalObj = GlobalHelper.getInstance();
		  JFreeChart jfreechart = createChart2(dataInput);
			jfreechart.addSubtitle(new TextTitle("descriptions"));

			jfreechart.setPadding(new RectangleInsets(10D,5D,15D,10D));
			ChartPanel chartPanel = new ChartPanel(jfreechart, true, true, true, false, true);
			chartPanel.setPreferredSize(new java.awt.Dimension(1200, 800));
			return chartPanel;

		    }
	/*public JPanel createDemoPanel2(ArrayList<WmparcStats> dataInput) {
		//GlobalHelper globalObj = GlobalHelper.getInstance();
		JFreeChart jfreechart = createChart2(dataInput);
		jfreechart.addSubtitle(new TextTitle("descriptions"));

		jfreechart.setPadding(new RectangleInsets(10D,5D,15D,10D));
		ChartPanel chartPanel = new ChartPanel(jfreechart, true, true, true, false, true);
		chartPanel.setPreferredSize(new java.awt.Dimension(600, 600));
		return chartPanel;
	}*/
	
	static class CustomToolTipGenerator implements CategoryToolTipGenerator  {
	    public String generateToolTip(CategoryDataset dataset, int row, int column)   {
	           return "test";
	    }
	}

}
