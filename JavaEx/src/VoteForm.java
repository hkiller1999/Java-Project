import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;

public class VoteForm extends JFrame {

	private JPanel contentPane;
	private JScrollPane jsp;
	private JTable table;
	private JPanel panel;
	private JPanel panel_1;
	private final JButton button = new JButton("\u67E5\u770B\u6240\u6709\u5019\u9009\u4EBA");
	private JButton button_1;
	private JTextField textField;
	private JLabel lblId;
	private JLabel label;
	private JButton button_2;
	private JButton button_3;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public VoteForm(int id) throws Exception {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Object [][] infor=VoteService.QueryAllCandidate();
		String[] title = {"ID","姓名","票数","简介"};
		table = new JTable(infor,title);
		this.jsp=new JScrollPane(table);
		getContentPane().add(jsp, BorderLayout.CENTER);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		button_1 = new JButton("\u6A21\u7CCA\u67E5\u8BE2");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object infor[][]=VoteService.QueryBlurry(textField.getText());
					String[] title = {"ID","姓名","票数","简介"};
					table.setModel(new DefaultTableModel(infor,title));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		panel.add(button_1);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Object infor[][]=VoteService.QueryAllCandidate();
					String[] title = {"ID","姓名","票数","简介"};
					table.setModel(new DefaultTableModel(infor,title));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		panel.add(button);
		
		button_3 = new JButton("\u6295\u7968");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()!=-1) {
					int row=table.getSelectedRow();
					int canid=(int)table.getValueAt(row, 0);
					try {
						Object [] vote=VoteService.QueryVoterById(id);
						if(Integer.valueOf(vote[3].toString())==0)
						{
							 int n1=JOptionPane.showConfirmDialog(null, "确认？", "确认", JOptionPane.YES_NO_OPTION); 
							    if(n1==JOptionPane.YES_OPTION) 
							    {
									VoteService.UpdateVoter(id, 1, canid);
									VoteService.UpdateCandidate(canid);
							    }
						}
						else if(Integer.valueOf(vote[4].toString())==0)
						{
							 int n1=JOptionPane.showConfirmDialog(null, "确认？", "确认", JOptionPane.YES_NO_OPTION); 
							    if(n1==JOptionPane.YES_OPTION) 
							    {
									VoteService.UpdateVoter(id, 2, canid);
									VoteService.UpdateCandidate(canid);
							    }
						}
						else
						{
							JOptionPane.showMessageDialog(null, "你的投票次数已用完", "ERROR", JOptionPane.ERROR_MESSAGE); 
						}
						try {
							Object infor[][]=VoteService.QueryAllCandidate();
							String[] title = {"ID","姓名","票数","简介"};
							table.setModel(new DefaultTableModel(infor,title));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); 
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); 
					}
				    
				}
			}
		});
		panel.add(button_3);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblId = new JLabel("ID:"+id+"  " );
		panel_1.add(lblId);
		
		String name=VoteService.QueryVoterById(id)[1].toString();
		label = new JLabel("姓名:"+name);
		panel_1.add(label);
		
		button_2 = new JButton("\u6211\u4E5F\u8981\u7ADE\u9009");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BeCandidate bc=new BeCandidate(id,name);
				bc.setVisible(true);
				setVisible(false);
			}
		});
		panel_1.add(button_2);
	}

}
