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
import javax.swing.JTextField;
import javax.swing.JComboBox;

@Component
public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private Model model;

	@Autowired
	private PseudoService service;
	private JTextField textFieldSearch;
	private JTextField textFieldSearchResults;
	private String search;
	

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
				
		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(152, 41, 86, 20);
		getContentPane().add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(260, 41, 89, 23);
		getContentPane().add(btnSearch);
		
		String [] options = {"Starts with", "Contains", "Ends with"};
		JComboBox comboBox = new JComboBox(options);
		comboBox.setBounds(30, 41, 100, 20);
		getContentPane().add(comboBox);
		setBounds(100, 100, 610, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		/*
		JLabel lblSearchResults = new JLabel("Search Results");
		lblSearchResults.setFont(new Font("Source Sans Pro Black", Font.ITALIC, 14));
		lblSearchResults.setBounds(72, 51, 187, 20);
		getContentPane().add(lblSearchResults);
		
		textFieldSearchResults = new JTextField();
		textFieldSearchResults.setBounds(35, 71, 385,55);
		getContentPane().add(textFieldSearchResults);
		textFieldSearchResults.setColumns(10);
		
		*/
		
		JList<MyObject> jlist = new JList<MyObject>();
		// getContentPane().add(jlist); // inutile => définis dans JScrollPane
	
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // liste mono-selection
		jlist.setModel(model);

		JScrollPane jscrollPane = new JScrollPane(jlist);
		jscrollPane.setBounds(35, 156, 385, 135);
		getContentPane().add(jscrollPane);

		
		
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textFieldSearch.getText() == null) {
					return;
				} else {
					if (comboBox.getSelectedIndex() == 1) {
						textFieldSearchResults.setText(service.findByString(textFieldSearch.getText()).toString());
					} else {
						
					}
				}
				
			}
		});
		
		
		JLabel lblLaListeDes = new JLabel("La liste des objets");
		lblLaListeDes.setFont(new Font("Source Sans Pro Black", Font.ITALIC, 14));
		lblLaListeDes.setBounds(72, 130, 187, 20);
		getContentPane().add(lblLaListeDes);
				
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(450, 144, 89, 23);
		getContentPane().add(btnDelete);

		

		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ((jlist.getSelectedValue() == null) || (jlist.getSelectedIndex() < 0)) {
					return;
				} else {
					int response = JOptionPane.showConfirmDialog(null,
							"Etes-vous sur de vouloir détruire cet élément ?", "Suppression",
							JOptionPane.YES_NO_OPTION);
					if (response != JOptionPane.YES_OPTION) {
						return;
					}
					int index = jlist.getSelectedIndex();

					try {
						service.delete(index);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					jlist.clearSelection(); // pour que quand il ne reste plus d'éléments dans la jlist,
											// getSelectedValue soit à 0. Sinon meme après la suppréssion jlist garde en
											// mémoire la selectedValue qu'on a supprimé
					jlist.updateUI();
				}

			}
		});

		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(450, 184, 89, 23);
		getContentPane().add(btnCreate);
		
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				CreateDialog dialog = new CreateDialog(service);
				dialog.setModal(true);
				dialog.setVisible(true);
				
				jlist.updateUI();
				
			}
		});
		
		JButton btnModify = new JButton("Modify");
		btnModify.setBounds(450, 224, 89, 23);
		getContentPane().add(btnModify);
		
		btnModify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jlist.getSelectedValue() == null) {
					return ;
					
				} else {
				
				int index = jlist.getSelectedIndex();
				int id = jlist.getSelectedValue().getId();
				String chaine = jlist.getSelectedValue().getChaine();
				int value = jlist.getSelectedValue().getValue();
				MyObject myobject = new MyObject(id, chaine, value);
				
					
				CreateDialog dialog = new CreateDialog(myobject, index, service);
				dialog.setModal(true);
				dialog.setVisible(true);
				
				jlist.updateUI();
				}
			}
		});
	}
}
