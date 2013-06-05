package com.shunui.model;

public class WmparcStats {
	
	public WmparcStats(double vo,String sn,double nmin,double nmax,double zs){
		this.volume_mm3 = vo;
		this.structName = sn;
		this.normMin = nmin;
		this.normMax = nmax;
		this.zscore = zs;
		
	}
	
	public int index;
	public int segId;
	public int nvoxels;
	public double volume_mm3;
	public String structName;
	public double normMean;
	public double normStdDev;
	public double normMin;
	public double normMax;
	public double normRange;
	public double zscore;
}

