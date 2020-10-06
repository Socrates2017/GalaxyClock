package com.zrzhen.clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

class ButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	public  String aUn = "关于我们";
	
	ButtonPanel() {
		
		JButton aboutUs = new JButton(aUn);
		this.add(aboutUs);
		aboutUs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(TimePanel.bap != false){
					TimePanel.bap= false;
				}else{
					TimePanel.bap=true;
				};
		}});;
	}
}
