import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setTitle("\u767B\u5F55");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setBounds(73, 79, 54, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(73, 118, 54, 15);
		panel.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(156, 76, 127, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(156, 115, 127, 21);
		panel.add(passwordField);
		
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int id=Integer.parseInt(textField.getText().trim());
					char[] values = passwordField.getPassword();
					String passwordinput = new String(values).trim(); 
					String password="";
					password = VoteService.GetPassword(id);
					if(password.equals(passwordinput))
					{
					    Object[] a=VoteService.QueryCandidateById(id);
						if(a==null)
						{
							VoteForm vf=new VoteForm(id);
						    vf.setVisible(true);
						    setVisible(false);
						}
						else
						{
							CandidateForm cf=new CandidateForm(id);
							cf.setVisible(true);
							setVisible(false);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "用户名或密码错误", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "用户名或密码错误", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(169, 192, 93, 23);
		panel.add(button);
	}
}
