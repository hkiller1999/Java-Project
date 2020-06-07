import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CandidateForm extends JFrame {

	private JPanel contentPane;
	private JScrollPane jsp;
	private JTable table;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public CandidateForm(int id) throws Exception {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Object[] candi=VoteService.QueryCandidateById(id);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JButton button_2 = new JButton("\u6295\u7968");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()!=-1) {
					int row=table.getSelectedRow();
					int canid=(int)table.getValueAt(row, 0);
					if(canid!=id)
					{
					try {
						Object [] vote=VoteService.QueryVoterById(id);
						if(Integer.valueOf(vote[3].toString())==0)
							{
							 int n1=JOptionPane.showConfirmDialog(null, "确认？", "确认", JOptionPane.YES_NO_OPTION); 
							    if(n1==JOptionPane.YES_OPTION) {
									VoteService.UpdateVoter(id, 1, canid);
									VoteService.UpdateCandidate(canid);
							    }
							}
						else if(Integer.valueOf(vote[4].toString())==0)
							{
							 int n1=JOptionPane.showConfirmDialog(null, "确认？", "确认", JOptionPane.YES_NO_OPTION); 
							    if(n1==JOptionPane.YES_OPTION) {
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
					else
					{
						JOptionPane.showMessageDialog(null, "不能给自己投票哦！", "ERROR", JOptionPane.ERROR_MESSAGE); 
					}
				}
			}
		});
		panel_2.add(button_2);
		button_2.setVisible(false);
		JButton button_1 = new JButton("\u770B\u6211\u81EA\u5DF1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object infor[][]=VoteService.QueryVoterByCanId(id);
					String[] title = {"ID","姓名","给我投票数"};
					table.setModel(new DefaultTableModel(infor,title));
					button_2.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		panel_2.add(button_1);
		
	
		JButton button = new JButton("\u67E5\u770B\u6240\u6709\u5019\u9009\u4EBA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object infor[][]=VoteService.QueryAllCandidate();
					String[] title = {"ID","姓名","票数","简介"};
					table.setModel(new DefaultTableModel(infor,title));
					button_2.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		panel_2.add(button);
		
	
		Object [][] infor=VoteService.QueryVoterByCanId(id);
		String[] title = {"ID","姓名","给我投票数"};
		table = new JTable(infor,title);
		this.jsp=new JScrollPane(table);
		getContentPane().add(jsp, BorderLayout.CENTER);
		JLabel label = new JLabel("姓名： "+candi[1].toString()+"  ");
		panel.add(label);
		
		JLabel label_1 = new JLabel("当前票数:"+candi[2].toString());
		panel.add(label_1);
		
	}

}
