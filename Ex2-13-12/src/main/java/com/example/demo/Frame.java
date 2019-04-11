package com.example.demo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class Frame extends JFrame {

	

	@Autowired
	private Model model;
	
	@Autowired
	private PseudoService service;

	/**
	 * Create the application.
	 */
	public Frame() {
				
	}

	/**
	 * Initialize the contents of the frame.
	 */
		public void start() {
		initialize();
	}
	
	private void initialize() {
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblLaListeDes = new JLabel("La liste des strings");
		lblLaListeDes.setFont(new Font("Source Sans Pro Black", Font.ITALIC, 14));
		lblLaListeDes.setBounds(72, 30, 187, 20);
		getContentPane().add(lblLaListeDes);
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(289, 104, 89, 23);
		getContentPane().add(btnDelete);
		
				JList<String> jlist = new JList<String>();
			//	getContentPane().add(jlist);
			//	jlist.setBounds(35, 56, 185, 155);
				jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // liste mono-selection
				jlist.setModel(model);
				
				JScrollPane jscrollPane = new JScrollPane(jlist);
				jscrollPane.setBounds(35, 56, 185, 65);
				getContentPane().add(jscrollPane);
		
				
				btnDelete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (jlist.getSelectedValue() == null) {
							return ;
						} else {
							
						int response = JOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir détruire cet élément ?", "Suppression", JOptionPane.YES_NO_OPTION);
							if (response != JOptionPane.YES_OPTION) {
								return;
							}
						int index = jlist.getSelectedIndex();
						try {
							service.delete(index);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						jlist.updateUI();
						}	
						
					}
				});
	}
}
