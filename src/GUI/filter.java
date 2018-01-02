package GUI;

import javax.swing.JPanel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class filter extends JPanel {
	private JTextField id_txt;

	/**
	 * Create the panel.
	 */
	public filter() {
		setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(161, 108, 95, 22);
		add(dateChooser);
		
		id_txt = new JTextField();
		id_txt.setColumns(10);
		id_txt.setBounds(161, 168, 116, 22);
		add(id_txt);
		
		JButton btnId = new JButton("id");
		btnId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = id_txt.getText();
				
			}
		});
		btnId.setBounds(296, 167, 97, 25);
		add(btnId);

	}
}
