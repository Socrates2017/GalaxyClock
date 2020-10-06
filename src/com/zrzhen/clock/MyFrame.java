package com.zrzhen.clock;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MyFrame extends JFrame {

	/**
	 * @author ChenAnLian
	 */
	private static final long serialVersionUID = 1L;
	private final int Width = 600;
	private final int Height = 665;
	
	public TimePanel pp = new TimePanel();
	ButtonPanel bp = new ButtonPanel();

	public MyFrame() {
		this.setTitle("GalaxyClock");// ���ô��ڱ���
		
		// ���ô���ͼ��		
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("glxRTS.png")));
		
		this.setLocation(0, 0);
		this.setSize(Width, Height);// ���ô��ڴ�С
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ùرհ�ť
		this.setVisible(true);// ���ô��ڿɼ�
		
		this.add(pp);		
		this.add(bp, BorderLayout.SOUTH);
			
		new PaintThread().start();  //�����ػ��߳�

	}
		
	
	/**
	 * ����һ���ػ����ڵ��߳��࣬��ʵ�ֶ���Ч������һ���ڲ��ࡣ
	 *
	 */
	class PaintThread extends Thread {
		
		public void run(){
			while(true){
				repaint();
				try {
					Thread.sleep(40); //1s = 1000ms��ÿ400�����ػ�һ��
				} catch (InterruptedException e) {
					e.printStackTrace();
				}   
			}
		}	
	}
	

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MyFrame();;
			}
		});
		
	}
}





