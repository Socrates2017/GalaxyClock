package com.zrzhen.clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class TimePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int Width = 600;//时钟盘的宽度
	private static final int Height = 600;//时钟盘的高度
	
	static final int centerX = Width/2;//时钟中心的x坐标
	static final int centerY = Height/2;//时钟中心的y坐标
	
	/*
	 * 载入指针的图片
	 */
	ImageIcon clockNumber = new ImageIcon(TimePanel.class.getClassLoader().getResource(
			"com/zrzhen/images/glxRTS.png"));
	
	ImageIcon imageH = new ImageIcon(TimePanel.class.getClassLoader().getResource(
			"com/zrzhen/images/H.png"));
	
	ImageIcon imageM = new ImageIcon(TimePanel.class.getClassLoader().getResource(
			"com/zrzhen/images/M.png"));
	
	ImageIcon imageS = new ImageIcon(TimePanel.class.getClassLoader().getResource(
			"com/zrzhen/images/S.png"));
	
	//载入二维码
	ImageIcon zrzhenErweima = new ImageIcon(TimePanel.class.getClassLoader().getResource(
			"com/zrzhen/images/ZRZlogo.jpg"));
	
	final int RADIUS_OF_HOUR = 156;//时针的半径	
	final int RADIUS_OF_MINUTE = RADIUS_OF_HOUR;//分针半径
	final int RADIUS_OF_SECOND = RADIUS_OF_HOUR;//秒针半径
	
	final int WIDTH_OF_H = imageH.getIconWidth();
	final int HEIGHT_OF_H = imageH.getIconHeight();
	
	final int WIDTH_OF_M = imageM.getIconWidth();
	final int HEIGHT_OF_M = imageM.getIconHeight();
	
	final int WIDTH_OF_S = imageS.getIconWidth();
	final int HEIGHT_OF_S = imageS.getIconHeight();
	
	public static boolean bap = false;

	LocalDate DateNow = LocalDate.now();	//创建当前日期对象
	public  String DATE_NOW = DateNow.toString();	//当前日期字符串
	LocalTime TimeNow = LocalTime.now();	//创建当前时间对象
	public  String TIME_NOW = TimeNow.toString();	//当前时间字符串
	
	public double hour = TimeNow.getHour();	//获得当前小时信息
	
	public double hour12 (double hour) {	//将24小时制时间转为12小时制时间
		if(hour<=12){
			return hour + new TimePanel().getMinute(Minute)/60;
		}else
		return hour + new TimePanel().getMinute(Minute)/60 -12;		
	}
	
	/*
	 * 整个圆周角度为2π
	 * 指针与12点方向形成的角度为2π*hour12/12，化简得π*hour12/6。
	 * 根据三角函数，指针末端的X轴坐标即为sin(π*hour12/6)*指针长度
	 */
	public double hourAngleX(double hour12){	//时针x坐标的角度系数
		if(hour12 <= 3){
			return Math.sin(Math.PI * hour12/6);
		}
		if(hour12>3 & hour12<6){
			return Math.sin(Math.PI - (Math.PI * hour12/6));
		}
		if(hour12>=6 & hour12<9){
			return - Math.sin((Math.PI * hour12/6) - Math.PI);
		}else{
			return - Math.sin(2 * Math.PI - (Math.PI * hour12/6));
		}	
	}
	
	public double hourAX(){//时间X坐标的角度系数,不带参数
		return (new TimePanel().hourAngleX(new TimePanel().hour12 (new TimePanel().hour)));
	}
	
	//时针的X坐标
	public int hourX(){
		return (int)(new TimePanel().hourAX()*RADIUS_OF_HOUR +centerX);		
	}
	
	public double hourAngleY(double hour12){	//时针y坐标的角度系数
		if(hour12<=3){
			return - Math.cos(Math.PI * hour12/6);
		}
		if(hour12>3 & hour12<=6){
			return Math.cos(Math.PI - (Math.PI * hour12/6));
		}
		if(hour12>6 & hour12<=9){
			return Math.cos((Math.PI * hour12/6) - Math.PI);
		}else{
			return - Math.cos(2 * Math.PI - (Math.PI * hour12/6));
		}	
	}
	
	public double hourAY(){//时针y坐标的角度系数，不带参数
		return (new TimePanel().hourAngleY(new TimePanel().hour12 (new TimePanel().hour)));
	}
	
	//时针的y坐标
	public int hourY(){
		return (int)(new TimePanel().hourAY()*RADIUS_OF_HOUR +centerY);		
	}

	public double Minute = TimeNow.getMinute();	//获得当前分钟信息
	public double getMinute(double minute){
		return minute;
	}

	public double minuteAngleX(double minute){	//获得分针x坐标的角度系数
		if(minute <= 15){
			return Math.sin(Math.PI * minute/30);
		}
		if(minute>15 & minute < 30){
			return Math.sin(Math.PI - (Math.PI * minute/30));
		}
		if(minute>=30 & minute < 45){
			return - Math.sin((Math.PI * minute/30) - Math.PI);
		}else{
			return - Math.sin(2 * Math.PI - (Math.PI * minute/30));
		}	
	}
	
	public double minuteAX(){//获得分针X坐标的角度系数，不带参数
		return (new TimePanel().minuteAngleX(new TimePanel().Minute));
	}
	
	//分针的X轴坐标
	public int minuteX(){
		return (int)(new TimePanel().minuteAX()*RADIUS_OF_MINUTE+centerX);		
	}
	
	
	public double minuteAngleY(double minute){	//获得分钟星球y坐标的角度系数
		if(minute <= 15){
			return - Math.cos(Math.PI * minute/30);
		}
		if(minute>15 & minute < 30){
			return Math.cos(Math.PI - (Math.PI * minute/30));
		}
		if(minute>=30 & minute < 45){
			return Math.cos((Math.PI * minute/30) - Math.PI);
		}else{
			return - Math.cos(2 * Math.PI - (Math.PI * minute/30));
		}	
	}
	
	public double minuteAY(){//获得分针Y坐标的角度系数，不带参数
		return (new TimePanel().minuteAngleY(new TimePanel().Minute));
	}
	
	//分针的Y轴坐标
	public int minuteY(){
		return (int)(new TimePanel().minuteAY()*RADIUS_OF_MINUTE + centerY);		
	}
	

	public double Second = TimeNow.getSecond();	//获得当前秒钟信息

	public double secondAngleX(double second){	//秒针x坐标的角度系数
		if(second <= 15){
			return Math.sin(Math.PI * second/30);
		}
		if(second>15 & second < 30){
			return Math.sin(Math.PI - (Math.PI * second/30));
		}
		if(second >=30 & second < 45){
			return - Math.sin((Math.PI * second/30) - Math.PI);
		}else{
			return - Math.sin(2 * Math.PI - (Math.PI * second/30));
		}	
	}
		
	public double secondAX(){//秒针X坐标的角度系数，不带参数
		return (new TimePanel().secondAngleX(new TimePanel().Second));
	}
	
	//秒针的X轴坐标
	public int secondX(){
		return (int)(new TimePanel().secondAX() * RADIUS_OF_SECOND + centerX);		
	}
	
	
	public double secondAngleY(double second){	//秒针y坐标的角度系数
		if(second <= 15){
			return - Math.cos(Math.PI * second/30);
		}
		if(second>15 & second < 30){
			return Math.cos(Math.PI - (Math.PI * second/30));
		}
		if(second>=30 & second < 45){
			return Math.cos((Math.PI * second/30) - Math.PI);
		}else{
			return - Math.cos(2 * Math.PI - (Math.PI * second/30));
		}	
	}
	
	public double secondAY(){//秒针X坐标的角度系数，不带参数
		return (new TimePanel().secondAngleY(new TimePanel().Second));
	}
	
	//秒针的X轴坐标
	public int secondY(){
		return (int)(new TimePanel().secondAY() * RADIUS_OF_SECOND + centerY);		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.setSize(Width, Height);
		this.setBackground(Color.black);				
		
		g.drawImage(clockNumber.getImage(), (this.getWidth() - clockNumber.getIconWidth())/2, 
				(this.getHeight() - clockNumber.getIconHeight())/2, this);
		
		g.drawImage(imageH.getImage(), new TimePanel().hourX() - WIDTH_OF_H/2, 
				new TimePanel().hourY() - HEIGHT_OF_H/2, this);		

		g.drawImage(imageM.getImage(), new TimePanel().minuteX() - WIDTH_OF_M/2,
				new TimePanel().minuteY() - HEIGHT_OF_M/2, this);
		
		g.drawImage(imageS.getImage(), new TimePanel().secondX() - WIDTH_OF_S/2, 
				new TimePanel().secondY() - HEIGHT_OF_S/2, this);	
				
		if(bap !=false){
			g.drawImage(zrzhenErweima.getImage(), (Width - 100)/2, (Height - 100)/2, 100,100,this);
			
			g.setColor(Color.blue);
			Font f = new Font(Font.MONOSPACED,Font.BOLD,52 );
			g.setFont(f);
			g.drawString("www.zrzhen.com", 100, 550);						
		}
	}	
}