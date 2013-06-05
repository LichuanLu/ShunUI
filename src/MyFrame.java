import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jfree.ui.RefineryUtilities;

import Shellcommand.JavaShellUtil;

import com.shunui.database.ImportData;
import com.shunui.help.GlobalHelper;
import com.shunui.jchart.JchartBeta;
import com.shunui.model.WmparcStats;

public class MyFrame {

    protected Shell shlFreesurferGui;

    /**
     * Launch the application.
     * 
     * @param args
     */
    public static void main(String[] args) {
	try {
	    // just test data importing

	    // ImportData data = new ImportData();
	    // WmparcStats patientStats = new
	    // WmparcStats(0D,"wm-lh-bankssts",0D,0D,0D);
	    // data.ReadData("d:\\workspace\\aseg.stats",patientStats);

	    MyFrame window = new MyFrame();
	    window.open();

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    /**
     * Open the window.
     */
    public void open() {
	Display display = Display.getDefault();
	createContents();
	shlFreesurferGui.open();
	shlFreesurferGui.layout();
	while (!shlFreesurferGui.isDisposed()) {
	    if (!display.readAndDispatch()) {
		display.sleep();
	    }
	}
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
	shlFreesurferGui = new Shell();
	int x = 441;
	int y = 461;
	shlFreesurferGui.setSize(x, y);
	shlFreesurferGui.setText("Brain Corex Analyze System");

	final Label lblNewLabel_1 = new Label(shlFreesurferGui, SWT.BORDER
		| SWT.CENTER);
	lblNewLabel_1.setBackground(SWTResourceManager
		.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
	lblNewLabel_1.setFont(SWTResourceManager.getFont("Tahoma", 8,
		SWT.NORMAL));
	lblNewLabel_1.setBounds(115, 87, 295, 23);

	Button btnNewButton = new Button(shlFreesurferGui, SWT.NONE);
	btnNewButton.addSelectionListener(new SelectionAdapter() { // select
		    // patient's
		    // DICOM dir
		    @Override
		    public void widgetSelected(SelectionEvent e) {
			DirectoryDialog dialog = new DirectoryDialog(
				shlFreesurferGui);
			// 设置显示在窗口上方的提示信息
			dialog.setMessage("Select Patient's DICOM Directory");
			// 设置对话框的标题
			dialog.setText("Select Directory");
			// 设置打开时默认的文件目录
			dialog.setFilterPath("./");
			// 打开窗口，返回用户所选的文件目录
			String dir = dialog.open();
			if (dir != null) {
			    lblNewLabel_1.setText(dir);
			    File file = new File(dir);
			    // File[] array = file.listFiles();
			    int filenumber = getlist(file);
			    global.data_dir = file.getName();
			    // open file number dialog
			    // DICOM file number must be 192
			    if ((filenumber - 192) != 0) {
				MessageBox messageBox = new MessageBox(
					shlFreesurferGui, SWT.OK);
				messageBox
					.setMessage("The file number should be 192,plz check the directory for sure!");
				messageBox.open();
			    }
			    // find the file for pre-process of freesurfer
			    if (file.isDirectory()) {
				File[] array = file.listFiles();
				String file1 = array[filenumber - 3].getName();
				String file2 = array[filenumber - 4].getName();
				System.out.println(filenumber);
				System.out.println(file1);
				System.out.println(file2);
				// wrtie the shell file

				try {
				    FileWriter fw = new FileWriter(dir + "\\"
					    + "img.sh");
				    String str = "#!/bin/bash \r\n";
				    fw.write(str);
				    str = "recon-all -s " + global.data_dir
					    + " -i " + file1 + " -i " + file2
					    + "\r\n";
				    fw.write(str);
				    // System.out.println(str);
				    fw.close();
				} catch (IOException e1) {
				    // TODO Auto-generated catch block
				    e1.printStackTrace();
				}

				try {
				    FileWriter fw = new FileWriter(dir + "\\"
					    + "recon.sh");
				    String str = "#!/bin/bash \r\n";
				    fw.write(str);
				    str = "recon-all -s " + global.data_dir
					    + " -all " + "\r\n";
				    fw.write(str);
				    // System.out.println(str);
				    fw.close();
				} catch (IOException e1) {
				    // TODO Auto-generated catch block
				    e1.printStackTrace();
				}
			    }

			}

		    }

		    public int getlist(File f) {
			int size = 0;
			File flist[] = f.listFiles();
			size = flist.length;
			for (int i = 0; i < flist.length; i++) {
			    if (flist[i].isDirectory()) {
				size = size + getlist(flist[i]);
				size--;
			    }
			}
			return size;
		    }

		});

	btnNewButton.setBounds(20, 87, 77, 23);
	btnNewButton.setText("Select");

	Button btnProcess = new Button(shlFreesurferGui, SWT.NONE);
	btnProcess.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {// open confirm dialog
							  // for process
		MessageBox messageBox = new MessageBox(shlFreesurferGui, SWT.OK
			| SWT.CANCEL);
		messageBox
			.setMessage("This will take about 20 hours for 1 patient \n\n Do you really want to start?");
		if (messageBox.open() == SWT.OK) {
		    System.out.println("Starting program!");
		    // new JavaShellUtil("sh /tmp/ShellCommand.sh");
		    JavaShellUtil shell = new JavaShellUtil("cmd /c dir");
		    shell.startOut();
		}
	    }
	});
	btnProcess.setBounds(20, 135, 77, 23);
	btnProcess.setText("Process");

	final Label lblNewLabel = new Label(shlFreesurferGui, SWT.NONE);
	lblNewLabel
		.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
	lblNewLabel.setBounds(10, 68, 229, 13);
	lblNewLabel.setText("Step2. Select Patient's DICOM directory");

	Label lblStepStartFreesurfer = new Label(shlFreesurferGui, SWT.NONE);
	lblStepStartFreesurfer.setText("Step3. Start FreeSurfer Process");
	lblStepStartFreesurfer.setFont(SWTResourceManager.getFont("Tahoma", 8,
		SWT.NORMAL));
	lblStepStartFreesurfer.setBounds(10, 116, 229, 13);

	Label lblStepVisuallize = new Label(shlFreesurferGui, SWT.NONE);
	lblStepVisuallize.setText("Step 3. Visuallize patient's result");
	lblStepVisuallize.setFont(SWTResourceManager.getFont("Tahoma", 8,
		SWT.NORMAL));
	lblStepVisuallize.setBounds(10, 164, 229, 13);

	Button btnVisuallize = new Button(shlFreesurferGui, SWT.NONE);
	btnVisuallize.addSelectionListener(new SelectionAdapter() {// Visualize
								   // freesurfer
								   // result
		    @Override
		    public void widgetSelected(SelectionEvent e) {
			// new JavaShellUtil("sh visual.sh");
			JavaShellUtil shell = new JavaShellUtil(
				"cmd /k tasklist");
			shell.startOut();
		    }
		});
	btnVisuallize.setText("Table");
	btnVisuallize.setBounds(20, 183, 77, 23);

	final Label label = new Label(shlFreesurferGui, SWT.BORDER | SWT.CENTER);
	label.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
	label.setBackground(SWTResourceManager
		.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
	label.setBounds(115, 10, 295, 23);
	label.setText(global.fsf_home_dir);

	final Label label_1 = new Label(shlFreesurferGui, SWT.BORDER
		| SWT.CENTER);
	label_1.setFont(SWTResourceManager.getFont("Tahoma", 8, SWT.NORMAL));
	label_1.setBackground(SWTResourceManager
		.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
	label_1.setBounds(115, 39, 295, 23);
	label_1.setText(global.sub_dir);

	Menu menu = new Menu(shlFreesurferGui, SWT.BAR);
	shlFreesurferGui.setMenuBar(menu);

	MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
	mntmNewSubmenu.setText("Enviroment");

	Menu menu_1 = new Menu(mntmNewSubmenu);
	mntmNewSubmenu.setMenu(menu_1);

	MenuItem mntmNewSubmenu_2 = new MenuItem(menu_1, SWT.CASCADE);
	mntmNewSubmenu_2.setText("Directory set");

	Menu menu_2 = new Menu(mntmNewSubmenu_2);
	mntmNewSubmenu_2.setMenu(menu_2);

	MenuItem mntmNewItem = new MenuItem(menu_2, SWT.NONE);
	mntmNewItem.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {// select freesurfer
							  // home directory
		DirectoryDialog dialog = new DirectoryDialog(shlFreesurferGui);
		// 设置显示在窗口上方的提示信息
		dialog.setMessage("Select freesurfer home directory");
		// 设置对话框的标题
		dialog.setText("Select Directory");
		// 设置打开时默认的文件目录
		dialog.setFilterPath("C:\\");
		// 打开窗口，返回用户所选的文件目录
		global.fsf_home_dir = dialog.open();
		if (global.fsf_home_dir != null) {
		    label.setText(global.fsf_home_dir);
		}

	    }
	});
	mntmNewItem.setText("Freesurfer home dir");

	MenuItem mntmNewItem_1 = new MenuItem(menu_2, SWT.NONE);
	mntmNewItem_1.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
		DirectoryDialog dialog = new DirectoryDialog(shlFreesurferGui);
		// 设置显示在窗口上方的提示信息
		dialog.setMessage("Select subjects directory");
		// 设置对话框的标题
		dialog.setText("Select Directory");
		// 设置打开时默认的文件目录
		dialog.setFilterPath("C:\\");
		// 打开窗口，返回用户所选的文件目录
		global.sub_dir = dialog.open();
		if (global.sub_dir != null) {
		    label_1.setText(global.sub_dir);
		}
	    }
	});
	mntmNewItem_1.setText("Subjects dir");

	Label lblNewLabel_2 = new Label(shlFreesurferGui, SWT.NONE);
	lblNewLabel_2.setFont(SWTResourceManager.getFont("Tahoma", 8,
		SWT.NORMAL));
	lblNewLabel_2.setBounds(10, 9, 99, 23);
	lblNewLabel_2.setText("Freesurfer home");

	Label lblSubjectsDir = new Label(shlFreesurferGui, SWT.NONE);
	lblSubjectsDir.setFont(SWTResourceManager.getFont("Tahoma", 8,
		SWT.NORMAL));
	lblSubjectsDir.setText("Subjects dir");
	lblSubjectsDir.setBounds(20, 39, 89, 23);

	Label lblDesignByCtmr = new Label(shlFreesurferGui, SWT.NONE);
	lblDesignByCtmr.setAlignment(SWT.RIGHT);
	lblDesignByCtmr.setFont(SWTResourceManager.getFont("Tahoma", 8,
		SWT.NORMAL));
	lblDesignByCtmr.setBounds((x - 16 - 173), (y - 58 - 13), 173, 13);
	lblDesignByCtmr.setText("design by CTMR Team of FMMU");

	Button btnCure = new Button(shlFreesurferGui, SWT.NONE);
	btnCure.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
		GlobalHelper globalObj = GlobalHelper.getInstance();
		// DefaultKeyedValues defaultkeyedvalues = new
		// DefaultKeyedValues();
		// add the test data to global object
		//globalObj.dataChart.add(new WmparcStats(2645D,"wm-lh-bankssts", 2040.0D, 2878.0000D, 0.9D));
		//globalObj.dataChart.add(new WmparcStats(2608D,"wm-lh-caudalanteriorcingulate", 1900.0000D, 2960.0000,1D));
		//globalObj.dataChart.add(new WmparcStats(6227D,"wm-lh-caudalmiddlefrontal", 5700.12D,6429.0000, -0.4D));
		//globalObj.dataChart.add(new WmparcStats(1736D, "wm-lh-cuneus",1500D, 2000.0000D, -0.8D));
		
		ImportData data = new ImportData();
		//ArrayList<WmparcStats> p1 = new ArrayList<WmparcStats>(); 
		// WmparcStats patientStats = new
		// WmparcStats(2645D,"wm-lh-bankssts",2040.0D,2878.0000D,25D);
		
		data.ReadData("resource/aseg.stats",globalObj.dataChart);
		
		

		// new chart class and show the panel
		// JFrame.setDefaultLookAndFeelDecorated(true);

		Shell shell = new Shell(shlFreesurferGui);
		shell.setSize(1200, 600);
		shell.setLayout(new FillLayout());
		shell.setText("Cortex Volume ");

		JchartBeta shunbeta = new JchartBeta("Shun Chart Beta", "bar",
			shell);
		// for the line chart
		// JchartBeta shunbeta = new
		// JchartBeta("Shun Chart Beta","line");
		shunbeta.pack();
		RefineryUtilities.centerFrameOnScreen(shunbeta);
		shunbeta.setVisible(true);
		 //RefineryUtilities.centerFrameOnScreen(shunbeta);
		 //shunbeta.setVisible(true);
		 //frame.pack();
		//shell.open();
	    }
	});
	btnCure.setText("Cure");
	btnCure.setBounds(20, 229, 77, 23);

	Button btnZscore = new Button(shlFreesurferGui, SWT.NONE);
	btnZscore.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {

		GlobalHelper globalObj = GlobalHelper.getInstance();
		// DefaultKeyedValues defaultkeyedvalues = new
		// DefaultKeyedValues();
		// add the test data to global object
		globalObj.dataChart.add(new WmparcStats(2645D,"wm-lh-bankssts", 2040.0D, 2878.0000D, 0.9D));
		globalObj.dataChart.add(new WmparcStats(2608D,"wm-lh-caudalanteriorcingulate", 1900.0000D, 2960.0000,0.5D));
		globalObj.dataChart.add(new WmparcStats(6227D,"wm-lh-caudalmiddlefrontal", 5700.12D,6429.0000, -0.4D));
		globalObj.dataChart.add(new WmparcStats(1736D, "wm-lh-cuneus",1500D, 2000.0000D, 1D));

		Shell shell = new Shell(shlFreesurferGui);
		shell.setSize(600, 600);
		shell.setLayout(new FillLayout());
		shell.setText("Cortex volume Z-Score");
		// new chart class and show the panel
		// JFrame.setDefaultLookAndFeelDecorated(true);
		JchartBeta shunbeta = new JchartBeta("Shun Chart Beta", "line",
			shell);
		// for the line chart
		// JchartBeta shunbeta = new
		// JchartBeta("Shun Chart Beta","line");
		shunbeta.pack();
		RefineryUtilities.centerFrameOnScreen(shunbeta);
		shunbeta.setVisible(true);
		//shell.open();
	    }
	});
	btnZscore.setText("Z-Score");
	btnZscore.setBounds(20, 278, 77, 23);

	Button btndvisuallize = new Button(shlFreesurferGui, SWT.NONE);
	btndvisuallize.setText("3D-Visuallize");
	btndvisuallize.setBounds(20, 324, 89, 23);

	Label lblNewLabel_3 = new Label(shlFreesurferGui, SWT.WRAP);
	lblNewLabel_3.setToolTipText("Table");
	lblNewLabel_3.setFont(SWTResourceManager.getFont("Tahoma", 9,
		SWT.NORMAL));
	lblNewLabel_3.setBounds(115, 183, 295, 40);
	lblNewLabel_3
		.setText("Show a table of cortex data like thickness, volume with statics data");

	Label lblDrawA = new Label(shlFreesurferGui, SWT.WRAP);
	lblDrawA.setToolTipText("Table");
	lblDrawA.setText("Draw a histogram for each crotex function area ");
	lblDrawA.setFont(SWTResourceManager.getFont("Tahoma", 9, SWT.NORMAL));
	lblDrawA.setBounds(115, 229, 295, 40);

	Label lblZscoreAllThe = new Label(shlFreesurferGui, SWT.WRAP);
	lblZscoreAllThe.setToolTipText("Table");
	lblZscoreAllThe
		.setText("Calulate sample z-score maping to known model");
	lblZscoreAllThe.setFont(SWTResourceManager.getFont("Tahoma", 9,
		SWT.NORMAL));
	lblZscoreAllThe.setBounds(115, 278, 295, 40);

	Label lbldVisuallizeAll = new Label(shlFreesurferGui, SWT.WRAP);
	lbldVisuallizeAll.setToolTipText("Table");
	lbldVisuallizeAll.setText("3D visuallize all results");
	lbldVisuallizeAll.setFont(SWTResourceManager.getFont("Tahoma", 9,
		SWT.NORMAL));
	lbldVisuallizeAll.setBounds(115, 324, 295, 40);

    }
}
