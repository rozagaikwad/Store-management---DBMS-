import java.awt.EventQueue;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class display {

	private JFrame frame;
	private JTable table_3;
	protected AbstractButton tbl;

	//Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					display window = new display();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application.
	
	public display() {
		initialize();
	}

	//Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 762, 577);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("display ");
		btnNewButton.addActionListener(new ActionListener() {
		

			public void actionPerformed(ActionEvent e) {
				String id,name,address,phoneno;
				
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/store?autoReconnect=true&useSSL=false","root","nvc@2002");
						Statement st=conn.createStatement();
						String query="select * from customer";
						ResultSet rs=st.executeQuery(query);
						ResultSetMetaData rsmd=rs.getMetaData();

						DefaultTableModel model=(DefaultTableModel) table_3.getModel();
						int cols=rsmd.getColumnCount();
						String[] colName=new String[cols];
						for(int i=0;i<cols;i++)
						{
							colName[i]=rsmd.getColumnName(i+1);
							model.setColumnIdentifiers(colName);
						}
						while(rs.next())
						{
						id=rs.getString(1);
						name=rs.getString(2);
						address=rs.getString(3);
						phoneno=rs.getString(4);
						String [] row= {id,name,address,phoneno};
						model.addRow(row);
						}
						st.close();
						conn.close();
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(54, 164, 286, 79);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(366, 100, 372, 430);
		frame.getContentPane().add(scrollPane);
		
		table_3 = new JTable();
		scrollPane.setViewportView(table_3);
		
		JButton btnNewButton_1 = new JButton("clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_3.setModel(new DefaultTableModel());
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(54, 279, 286, 79);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("CUSTOMER DISPLAY");
		lblNewLabel.setBackground(Color.CYAN);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Italic", Font.BOLD, 40));
		lblNewLabel.setBounds(134, 10, 554, 69);
		frame.getContentPane().add(lblNewLabel);
	}


}
