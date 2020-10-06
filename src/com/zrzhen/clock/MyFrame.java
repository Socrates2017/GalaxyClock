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
		this.setTitle("GalaxyClock");// 设置窗口标题
		
		// 设置窗口图标		
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(getClass().getResource("glxRTS.png")));
		
		this.setLocation(0, 0);
		this.setSize(Width, Height);// 设置窗口大小
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭按钮
		this.setVisible(true);// 设置窗口可见
		
		this.add(pp);		
		this.add(bp, BorderLayout.SOUTH);
			
		new PaintThread().start();  //启动重画线程

	}
		
	
	/**
	 * 定义一个重画窗口的线程类，以实现动画效果，是一个内部类。
	 *
	 */
	class PaintThread extends Thread {
		
		public void run(){
			while(true){
				repaint();
				try {
					Thread.sleep(40); //1s = 1000ms，每400毫秒重画一次
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





