package p06_SimpleAnimation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class BallLauncher extends JFrame implements ActionListener, ChangeListener {

	private JPanel contentPane;
	private Ball ball;
	private JButton startStopButton;
	private JButton pauseResumeButton;

	private Thread th;
	private boolean started = false;
	private boolean paused;
	private JSlider slider;

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BallLauncher frame = new BallLauncher();
					frame.setVisible(true);
					frame.setTitle("Ball... (Available procs: " + Runtime.getRuntime().availableProcessors() + ")");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BallLauncher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 432);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);

		this.ball = new Ball();
		this.ball.setForeground(UIManager.getColor("CheckBoxMenuItem.background"));
		this.ball.setBorder(new LineBorder(new Color(0, 0, 0)));

		this.startStopButton = new JButton("START");
		this.startStopButton.addActionListener(this);

		this.pauseResumeButton = new JButton("PAUSE");
		this.pauseResumeButton.addActionListener(this);
		this.pauseResumeButton.setEnabled(false);

		this.slider = new JSlider();
		this.slider.addChangeListener(this);
		this.slider.setBorder(new TitledBorder(null, "Speed", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.slider.setPaintTicks(true);
		this.slider.setPaintLabels(true);
		GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addGap(125)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false).addGroup(gl_contentPane
								.createSequentialGroup()
								.addComponent(this.startStopButton, GroupLayout.PREFERRED_SIZE, 127,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(this.pauseResumeButton, GroupLayout.PREFERRED_SIZE, 124,
										GroupLayout.PREFERRED_SIZE))
								.addComponent(this.ball, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(180).addComponent(this.slider,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(128, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(33)
						.addComponent(this.ball, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE).addGap(33)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.startStopButton).addComponent(this.pauseResumeButton))
						.addGap(18).addComponent(this.slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(14, Short.MAX_VALUE)));
		this.contentPane.setLayout(gl_contentPane);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.startStopButton) {
			startStopButtonActionPerformed(e);
		}
		if (e.getSource() == this.pauseResumeButton) {
			pauseResumeButtonActionPerformed(e);
		}
	}

	protected void startStopButtonActionPerformed(ActionEvent e) {
		if (!started) {
			th = new Thread(ball);
			started = true;
			paused = false;
			pauseResumeButton.setEnabled(true);
			startStopButton.setText("STOP");
			th.start();
		} else {
			startStopButton.setEnabled(false);
			pauseResumeButton.setEnabled(false);
			th.stop();
		}
	}

	public void stateChanged(ChangeEvent arg0) {
		if (arg0.getSource() == this.slider) {
			sliderstateChanged(arg0);
		}
	}

	
	protected void pauseResumeButtonActionPerformed(ActionEvent e) {
		if (paused) {
			pauseResumeButton.setText("PAUSE");
			th.resume();
		} else {
			pauseResumeButton.setText("RESUME");
			th.suspend();
		}
		paused = !paused;
	}

	
	protected void sliderstateChanged(ChangeEvent arg0) {
		int value = ((JSlider) arg0.getSource()).getValue();
		ball.setTimeToSleep(100 - value);
	}
}
