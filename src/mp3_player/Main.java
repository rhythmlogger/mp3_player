package mp3_player;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Main extends JFrame {
	private JPanel northPanel;
	private JButton pauseButton;
	private JButton resumeButton;
	private JButton randomButton;
	private CustomPlayer customPlayer;
	private DefaultListModel model;
	private JList list;
	
	public void randomSelect() {
		int r = (int) (Math.random() * 4);
		customPlayer.setPath((r + 1) + ".mp3");
		list.setSelectedIndex(r);
	}

	public Main() {
		setTitle("mp3 player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		model = new DefaultListModel();
		model.addElement("1.mp3");
		model.addElement("2.mp3");
		model.addElement("3.mp3");
		model.addElement("4.mp3");
		list = new JList(model);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					customPlayer.pause();
					JList source = (JList) e.getSource();
					String selected = source.getSelectedValue().toString();
					System.out.println(selected);
					customPlayer.setPath(selected);
					customPlayer.play(0);
				}
			}
			
		});
		customPlayer = new CustomPlayer();
		northPanel = new JPanel();
		pauseButton = new JButton("pause");
		resumeButton = new JButton("resume");
		randomButton = new JButton("ranndom");
		northPanel.setLayout(new GridLayout(1, 4));
		northPanel.add(pauseButton);
		northPanel.add(resumeButton);
		northPanel.add(randomButton);

		pauseButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				customPlayer.pause();
			}

		});
		resumeButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				customPlayer.resume();
			}

		});
		randomButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				randomSelect();
			}

		});
		setLayout(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		add(list, BorderLayout.CENTER);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}
}
