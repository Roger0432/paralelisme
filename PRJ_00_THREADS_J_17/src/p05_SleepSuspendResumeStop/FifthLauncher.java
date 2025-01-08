package p05_SleepSuspendResumeStop;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class FifthLauncher extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JLabel lblThreadA;
	private JButton suspendButton_A;
	private JButton resumeButton_A;
	private JTextField msTextField_A;
	private JLabel lblMs;
	private JButton sleepButton_A;
	private JButton stopButton_A;
	private JScrollPane scrollPane_1;
	private JTextField msTextField_B;
	private JLabel label;
	private JButton suspendButton_B;
	private JButton sleepButton_B;
	private JButton resumeButton_B;
	private JButton stopButton_B;
	private JLabel lblThreadB;
	private JLabel lblLimit;
	private JTextField limitTextField;
	private JButton goButton;
	private JTextArea display1;
	private JTextArea display2;
	
	private DisplayPrimeCalculator a,b;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FifthLauncher frame = new FifthLauncher();
					frame.setVisible(true);
					frame.setTitle("Sleep, Suspend, Resume & Stop (Available procs: "+Runtime.getRuntime().availableProcessors()+")");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FifthLauncher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 503);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		
		this.scrollPane = new JScrollPane();
		
		this.suspendButton_A = new JButton("SUSPEND");
		this.suspendButton_A.setEnabled(false);
		this.suspendButton_A.addActionListener(this);
		
		this.resumeButton_A = new JButton("RESUME");
		this.resumeButton_A.setEnabled(false);
		this.resumeButton_A.addActionListener(this);
		
		this.msTextField_A = new JTextField();
		this.msTextField_A.setEnabled(false);
		this.msTextField_A.setText("1000");
		this.msTextField_A.setColumns(10);
		
		this.lblMs = new JLabel("ms.");
		
		this.sleepButton_A = new JButton("SLEEP");
		this.sleepButton_A.setEnabled(false);
		this.sleepButton_A.addActionListener(this);
		
		this.stopButton_A = new JButton("STOP");
		this.stopButton_A.setEnabled(false);
		this.stopButton_A.addActionListener(this);
		
		this.scrollPane_1 = new JScrollPane();
		
		this.msTextField_B = new JTextField();
		this.msTextField_B.setEnabled(false);
		this.msTextField_B.setText("1000");
		this.msTextField_B.setColumns(10);
		
		this.label = new JLabel("ms.");
		
		this.suspendButton_B = new JButton("SUSPEND");
		this.suspendButton_B.setEnabled(false);
		this.suspendButton_B.addActionListener(this);
		
		this.sleepButton_B = new JButton("SLEEP");
		this.sleepButton_B.setEnabled(false);
		this.sleepButton_B.addActionListener(this);
		
		this.resumeButton_B = new JButton("RESUME");
		this.resumeButton_B.setEnabled(false);
		this.resumeButton_B.addActionListener(this);
		
		this.stopButton_B = new JButton("STOP");
		this.stopButton_B.setEnabled(false);
		this.stopButton_B.addActionListener(this);
		
		this.lblLimit = new JLabel("Limit: ");
		
		this.limitTextField = new JTextField();
		this.limitTextField.setText("1000000");
		this.limitTextField.setColumns(10);
		
		this.goButton = new JButton("GO");
		this.goButton.addActionListener(this);
		GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(46)
							.addComponent(this.scrollPane, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(this.resumeButton_A, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(this.msTextField_A, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(this.lblMs))
								.addComponent(this.sleepButton_A, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(this.suspendButton_A, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(96)
							.addComponent(this.stopButton_A, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(this.scrollPane_1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(this.msTextField_B, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(this.label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
								.addComponent(this.suspendButton_B, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
								.addComponent(this.resumeButton_B, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(this.sleepButton_B, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(50)
							.addComponent(this.stopButton_B, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)))
					.addGap(59))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(229)
					.addComponent(this.lblLimit)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.limitTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(this.goButton)
					.addContainerGap(291, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(this.scrollPane_1, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(this.suspendButton_B)
									.addGap(18)
									.addComponent(this.resumeButton_B)
									.addGap(13)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(this.msTextField_B, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(3)
											.addComponent(this.label)))
									.addGap(18)
									.addComponent(this.sleepButton_B)))
							.addGap(18)
							.addComponent(this.stopButton_B))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(this.suspendButton_A)
									.addGap(18)
									.addComponent(this.resumeButton_A)
									.addGap(13)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(this.msTextField_A, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(this.lblMs))
									.addGap(18)
									.addComponent(this.sleepButton_A))
								.addComponent(this.scrollPane, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(this.stopButton_A)))
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblLimit)
						.addComponent(this.limitTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.goButton))
					.addGap(48))
		);
		
		this.lblThreadB = new JLabel("Thread B");
		this.lblThreadB.setHorizontalAlignment(SwingConstants.CENTER);
		this.scrollPane_1.setColumnHeaderView(this.lblThreadB);
		
		this.display2 = new JTextArea();
		this.scrollPane_1.setViewportView(this.display2);
		
		this.lblThreadA = new JLabel("Thread A");
		this.lblThreadA.setHorizontalAlignment(SwingConstants.CENTER);
		this.scrollPane.setColumnHeaderView(this.lblThreadA);
		
		this.display1 = new JTextArea();
		this.scrollPane.setViewportView(this.display1);
		this.contentPane.setLayout(gl_contentPane);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.stopButton_B) {
			stopButton_BActionPerformed(arg0);
		}
		if (arg0.getSource() == this.stopButton_A) {
			stopButton_AActionPerformed(arg0);
		}
		if (arg0.getSource() == this.sleepButton_B) {
			sleepButton_BActionPerformed(arg0);
		}
		if (arg0.getSource() == this.sleepButton_A) {
			sleepButton_AActionPerformed(arg0);
		}
		if (arg0.getSource() == this.resumeButton_B) {
			resumeButton_BActionPerformed(arg0);
		}
		if (arg0.getSource() == this.resumeButton_A) {
			resumeButton_AActionPerformed(arg0);
		}
		if (arg0.getSource() == this.suspendButton_B) {
			suspendButton_BActionPerformed(arg0);
		}
		if (arg0.getSource() == this.suspendButton_A) {
			suspendButton_AActionPerformed(arg0);
		}
		if (arg0.getSource() == this.goButton) {
			goButtonActionPerformed(arg0);
		}
	}
	protected void goButtonActionPerformed(ActionEvent arg0) {
		
		int limit = 0;
        try {
            limit = Integer.parseInt(this.limitTextField.getText());
        }
        catch (NumberFormatException ex) {
            this.limitTextField.setText("100000");
            return;
        }
        
        this.goButton.setEnabled(false);
        this.limitTextField.setEnabled(false);
        
        a = new DisplayPrimeCalculator(this.display1, limit);
        b = new DisplayPrimeCalculator(this.display2, limit);
        
        a.start();
        b.start();
        
        this.suspendButton_A.setEnabled(true);
        this.suspendButton_B.setEnabled(true);
        this.msTextField_A.setEnabled(true);
        this.msTextField_B.setEnabled(true);
        this.sleepButton_A.setEnabled(true);
        this.sleepButton_B.setEnabled(true);
        this.stopButton_A.setEnabled(true);
        this.stopButton_B.setEnabled(true);
	}
	
	protected void suspendButton_AActionPerformed(ActionEvent arg0) {
		this.suspendButton_A.setEnabled(false);
        this.sleepButton_A.setEnabled(false);
        this.msTextField_A.setEnabled(false);
        this.a.suspend();
        this.resumeButton_A.setEnabled(true);
	}
	protected void suspendButton_BActionPerformed(ActionEvent arg0) {
		this.suspendButton_B.setEnabled(false);
        this.sleepButton_B.setEnabled(false);
        this.msTextField_B.setEnabled(false);
        this.b.suspend();
        this.resumeButton_B.setEnabled(true);
	}
	protected void resumeButton_AActionPerformed(ActionEvent arg0) {
		this.resumeButton_A.setEnabled(false);
        this.a.resume();
        this.suspendButton_A.setEnabled(true);
        this.sleepButton_A.setEnabled(true);
        this.msTextField_A.setEnabled(true);
	}
	protected void resumeButton_BActionPerformed(ActionEvent arg0) {
		this.resumeButton_B.setEnabled(false);
        this.b.resume();
        this.suspendButton_B.setEnabled(true);
        this.sleepButton_B.setEnabled(true);
        this.msTextField_B.setEnabled(true);
	}
	protected void sleepButton_AActionPerformed(ActionEvent arg0) {
		long ms = 0;
        try {
            ms = Long.parseLong(this.msTextField_A.getText());
        }
        catch (NumberFormatException ex) {
            this.msTextField_A.setText("1000");
            return;
        }
        this.a.setTimeToSleep(ms);
	}
	protected void sleepButton_BActionPerformed(ActionEvent arg0) {
		long ms = 0;
        try {
            ms = Long.parseLong(this.msTextField_B.getText());
        }
        catch (NumberFormatException ex) {
            this.msTextField_B.setText("1000");
            return;
        }
        this.b.setTimeToSleep(ms);
	}
	protected void stopButton_AActionPerformed(ActionEvent arg0) {
		this.suspendButton_A.setEnabled(false);
        this.msTextField_A.setEnabled(false);
        this.sleepButton_A.setEnabled(false);
        this.stopButton_A.setEnabled(false);
        this.resumeButton_A.setEnabled(false);
        this.a.stop();
        this.display1.append("\nSTOPPED");
        this.display1.setCaretPosition(
                                     this.display1.getDocument().getLength());
	}
	protected void stopButton_BActionPerformed(ActionEvent arg0) {
		this.suspendButton_B.setEnabled(false);
        this.msTextField_B.setEnabled(false);
        this.sleepButton_B.setEnabled(false);
        this.stopButton_B.setEnabled(false);
        this.resumeButton_B.setEnabled(false);
        this.b.stop();
        this.display2.append("\nSTOPPED");
        this.display2.setCaretPosition(
                                     this.display2.getDocument().getLength());
	}
}
