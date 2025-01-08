package p04_GUIExample;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;

public class FourthLauncher extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTextArea display1;
	private JTextArea display2;
	private JButton goButton;
	private JTextField limitTextField;
	private JLabel lblLimit;
	private JLabel lblThreadA;
	private JLabel lblThreadB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FourthLauncher frame = new FourthLauncher();
					frame.setVisible(true);
					frame.setTitle("Prime calculators. Available procs: "+Runtime.getRuntime().availableProcessors());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FourthLauncher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 424);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		
		this.scrollPane = new JScrollPane();
		
		this.scrollPane_1 = new JScrollPane();
		
		this.goButton = new JButton("GO");
		this.goButton.addActionListener(this);
		
		this.limitTextField = new JTextField();
		this.limitTextField.setText("100000");
		this.limitTextField.setColumns(10);
		
		this.lblLimit = new JLabel("Limit:");
		GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(this.scrollPane, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(this.scrollPane_1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(115)
							.addComponent(this.lblLimit)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(this.limitTextField, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(this.goButton)))
					.addContainerGap(161, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(this.scrollPane, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.scrollPane_1, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.goButton)
						.addComponent(this.limitTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblLimit))
					.addContainerGap(91, Short.MAX_VALUE))
		);
		
		this.display2 = new JTextArea();
		this.scrollPane_1.setViewportView(this.display2);
		
		this.lblThreadB = new JLabel("Thread B");
		this.lblThreadB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.lblThreadB.setHorizontalAlignment(SwingConstants.CENTER);
		this.scrollPane_1.setColumnHeaderView(this.lblThreadB);
		
		this.display1 = new JTextArea();
		this.scrollPane.setViewportView(this.display1);
		
		this.lblThreadA = new JLabel("Thread A");
		this.lblThreadA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.lblThreadA.setHorizontalAlignment(SwingConstants.CENTER);
		this.scrollPane.setColumnHeaderView(this.lblThreadA);
		this.contentPane.setLayout(gl_contentPane);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.goButton) {
			goButtonActionPerformed(e);
		}
	}
	
	protected void goButtonActionPerformed(ActionEvent e) {
		int limit = 0;
        try {
            limit = Integer.parseInt(this.limitTextField.getText());
        }
        catch (NumberFormatException ex) {
            this.limitTextField.setText("100000");
            return;
        }
        
        this.goButton.setEnabled(false);
        
        DisplayPrimeCalculator a,b;
        
        a = new DisplayPrimeCalculator(this.display1, limit);
        b = new DisplayPrimeCalculator(this.display2, limit);
        
        a.start();
        b.start();
	}
}
