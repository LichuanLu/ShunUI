package com.shunui.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.shunui.model.*;

public class ImportData {
    public ImportData() {

    }

    public void ReadData(String fileName, ArrayList<WmparcStats> dataChart) {
	
	if ( dataChart == null)
	     dataChart = new ArrayList<WmparcStats>();
	
	File file = new File(fileName);
	if (file.exists() && file.canRead()) {
	    FileReader fr;
	    try {
		fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		// String str;
		// "#" ACII 35
		String str = null;
		while ((str = br.readLine()) != null) {
		    if (str.charAt(0) != 35) {
			break;
		    }
		}
		
		//String s = "\\d+|\\d+.\\d+|[0-9a-zA-Z\\-\\_]+";
		String s = "[A-Za-z0-9\\-\\_\\.]+|[0-9\\.]+$";
		
		
		
		while(str != null){
		
		    Pattern pattern = Pattern.compile(s);
		    Matcher ma = pattern.matcher(str);

		    String[] dataStr = new String[10];
		    int i = 0;
		    while (ma.find()) {
		    dataStr[i] = ma.group();
		    //System.out.println(ma.group());
		    i++;
		    }
		
		    
		    if(i == 0 && i > 10 ){
			break;
		    }
		    
		    WmparcStats dataLine = new WmparcStats(0D, "null", 0D, 0D, 0D);
		    
		    dataLine.index = Integer.valueOf(dataStr[0]);
		    dataLine.segId = Integer.valueOf(dataStr[1]);
		    dataLine.nvoxels = Integer.valueOf(dataStr[2]);
		    dataLine.volume_mm3 = Double.valueOf(dataStr[3]);
		    dataLine.structName = dataStr[4];
		    dataLine.normMean = Double.valueOf(dataStr[5]);
		    dataLine.normStdDev = Double.valueOf(dataStr[6]);
		    dataLine.normMin = Double.valueOf(dataStr[7]);
		    dataLine.normMax = Double.valueOf(dataStr[8]);
		    dataLine.normRange = Double.valueOf(dataStr[9]);
		
		    dataChart.add(dataLine);
		    str = br.readLine();
		    
		}
		
		br.close();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

}
