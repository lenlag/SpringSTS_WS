package com.example.demo;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class CreateDialog extends JDialog {

	
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldId;
	private JTextField textFieldText;
	private JTextField textFieldValue;
	private boolean isUpdate;
	private PseudoService service;
	private MyObject myobject;
	private int index;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the dialog.
	 */
	public CreateDialog(PseudoService service) {
		this.service = service;
		isUpdate = false;
		init();
	}
	
	public CreateDialog(MyObject myobject, int index, PseudoService service) {
		this.service = service;
		this.myobject = myobject;
		this.index = index;
		isUpdate = true;
		init();
	}
	
	public void init() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 20, 434, 208);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			textFieldId = new JTextField();
			textFieldId.setBounds(174, 31, 117, 25);
			contentPanel.add(textFieldId);
			textFieldId.setColumns(10);
		}
		
		textFieldText = new JTextField();
		textFieldText.setColumns(10);
		textFieldText.setBounds(174, 80, 117, 25);
		contentPanel.add(textFieldText);
		
		textFieldValue = new JTextField();
		textFieldValue.setColumns(10);
		textFieldValue.setBounds(174, 130, 117, 25);
		contentPanel.add(textFieldValue);
		
		if (myobject != null) {
			textFieldId.setText(String.valueOf(myobject.getId()));
			textFieldText.setText(myobject.getChaine());
			textFieldValue.setText(String.valueOf(myobject.getValue()));
		}
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(95, 36, 46, 14);
		contentPanel.add(lblId);
		
		JLabel lblText = new JLabel("Text");
		lblText.setBounds(95, 85, 46, 14);
		contentPanel.add(lblText);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setBounds(95, 135, 46, 14);
		contentPanel.add(lblValue);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					String newId = textFieldId.getText();
					String newText = textFieldText.getText();
					String newValue = textFieldValue.getText();
					
					if (isUpdate) {
						myobject.setId(Integer.parseInt(newId));
						myobject.setChaine(newText);
						myobject.setValue(Integer.parseInt(newValue));
						
						service.updateByIndex(index, myobject);
						
					} else {
					
					MyObject object = new MyObject(Integer.parseInt(newId), newText, Integer.parseInt(newValue));
					service.create(object);	
					
					}
										
					dispose ();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
			}
			
			
		}
	}
}
