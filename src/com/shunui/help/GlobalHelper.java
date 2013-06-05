//单例模式，用来提供全�?��量还有全�?��方法
package com.shunui.help;

import java.util.ArrayList;

import com.shunui.model.WmparcStats;

public class GlobalHelper {
	private static GlobalHelper m_instance = null;   
	public double chart2_max =0;
	public double chart1_max =0;
	public ArrayList<WmparcStats> dataChart;
	   
	  /** * 私有的默认构造子 */   
	  private GlobalHelper() {    
		  dataChart = new ArrayList<WmparcStats>();
	  }    
	   
	  /** 
	    * * 静�?工厂方法 
	    */   
	  public synchronized static GlobalHelper getInstance() {    
	    if (m_instance == null) {    
	       m_instance = new GlobalHelper();    
	     }    
	    return m_instance;    
	   }

	  
}
