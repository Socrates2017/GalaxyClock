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
	
	private static final int Width = 600;//ʱ���̵Ŀ��
	private static final int Height = 600;//ʱ���̵ĸ߶�
	
	static final int centerX = Width/2;//ʱ�����ĵ�x����
	static final int centerY = Height/2;//ʱ�����ĵ�y����
	
	/*
	 * ����ָ���ͼƬ
	 */
	ImageIcon clockNumber = new ImageIcon(TimePanel.class.getClassLoader().getResource(
			"com/zrzhen/images/glxRTS.png"));
	
	ImageIcon imageH = new ImageIcon(TimePanel.class.getClassLoader().getResource(
			"com/zrzhen/images/H.png"));
	
	ImageIcon imageM = new ImageIcon(TimePanel.class.getClassLoader().getResource(
			"com/zrzhen/images/M.png"));
	
	ImageIcon imageS = new ImageIcon(TimePanel.class.getClassLoader().getResource(
			"com/zrzhen/images/S.png"));
	
	//�����ά��
	ImageIcon zrzhenErweima = new ImageIcon(TimePanel.class.getClassLoader().getResource(
			"com/zrzhen/images/ZRZlogo.jpg"));
	
	final int RADIUS_OF_HOUR = 156;//ʱ��İ뾶	
	final int RADIUS_OF_MINUTE = RADIUS_OF_HOUR;//����뾶
	final int RADIUS_OF_SECOND = RADIUS_OF_HOUR;//����뾶
	
	final int WIDTH_OF_H = imageH.getIconWidth();
	final int HEIGHT_OF_H = imageH.getIconHeight();
	
	final int WIDTH_OF_M = imageM.getIconWidth();
	final int HEIGHT_OF_M = imageM.getIconHeight();
	
	final int WIDTH_OF_S = imageS.getIconWidth();
	final int HEIGHT_OF_S = imageS.getIconHeight();
	
	public static boolean bap = false;

	LocalDate DateNow = LocalDate.now();	//������ǰ���ڶ���
	public  String DATE_NOW = DateNow.toString();	//��ǰ�����ַ���
	LocalTime TimeNow = LocalTime.now();	//������ǰʱ�����
	public  String TIME_NOW = TimeNow.toString();	//��ǰʱ���ַ���
	
	public double hour = TimeNow.getHour();	//��õ�ǰСʱ��Ϣ
	
	public double hour12 (double hour) {	//��24Сʱ��ʱ��תΪ12Сʱ��ʱ��
		if(hour<=12){
			return hour + new TimePanel().getMinute(Minute)/60;
		}else
		return hour + new TimePanel().getMinute(Minute)/60 -12;		
	}
	
	/*
	 * ����Բ�ܽǶ�Ϊ2��
	 * ָ����12�㷽���γɵĽǶ�Ϊ2��*hour12/12������æ�*hour12/6��
	 * �������Ǻ�����ָ��ĩ�˵�X�����꼴Ϊsin(��*hour12/6)*ָ�볤��
	 */
	public double hourAngleX(double hour12){	//ʱ��x����ĽǶ�ϵ��
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
	
	public double hourAX(){//ʱ��X����ĽǶ�ϵ��,��������
		return (new TimePanel().hourAngleX(new TimePanel().hour12 (new TimePanel().hour)));
	}
	
	//ʱ���X����
	public int hourX(){
		return (int)(new TimePanel().hourAX()*RADIUS_OF_HOUR +centerX);		
	}
	
	public double hourAngleY(double hour12){	//ʱ��y����ĽǶ�ϵ��
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
	
	public double hourAY(){//ʱ��y����ĽǶ�ϵ������������
		return (new TimePanel().hourAngleY(new TimePanel().hour12 (new TimePanel().hour)));
	}
	
	//ʱ���y����
	public int hourY(){
		return (int)(new TimePanel().hourAY()*RADIUS_OF_HOUR +centerY);		
	}

	public double Minute = TimeNow.getMinute();	//��õ�ǰ������Ϣ
	public double getMinute(double minute){
		return minute;
	}

	public double minuteAngleX(double minute){	//��÷���x����ĽǶ�ϵ��
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
	
	public double minuteAX(){//��÷���X����ĽǶ�ϵ������������
		return (new TimePanel().minuteAngleX(new TimePanel().Minute));
	}
	
	//�����X������
	public int minuteX(){
		return (int)(new TimePanel().minuteAX()*RADIUS_OF_MINUTE+centerX);		
	}
	
	
	public double minuteAngleY(double minute){	//��÷�������y����ĽǶ�ϵ��
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
	
	public double minuteAY(){//��÷���Y����ĽǶ�ϵ������������
		return (new TimePanel().minuteAngleY(new TimePanel().Minute));
	}
	
	//�����Y������
	public int minuteY(){
		return (int)(new TimePanel().minuteAY()*RADIUS_OF_MINUTE + centerY);		
	}
	

	public double Second = TimeNow.getSecond();	//��õ�ǰ������Ϣ

	public double secondAngleX(double second){	//����x����ĽǶ�ϵ��
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
		
	public double secondAX(){//����X����ĽǶ�ϵ������������
		return (new TimePanel().secondAngleX(new TimePanel().Second));
	}
	
	//�����X������
	public int secondX(){
		return (int)(new TimePanel().secondAX() * RADIUS_OF_SECOND + centerX);		
	}
	
	
	public double secondAngleY(double second){	//����y����ĽǶ�ϵ��
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
	
	public double secondAY(){//����X����ĽǶ�ϵ������������
		return (new TimePanel().secondAngleY(new TimePanel().Second));
	}
	
	//�����X������
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