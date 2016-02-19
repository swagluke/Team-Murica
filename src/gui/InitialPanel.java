package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class InitialPanel extends APanel {
	private static final long serialVersionUID = 6677489823561856215L;
	private JLabel configLabel;
	private JLabel progressLabel;
	private JProgressBar progressBar;
	private boolean analying;
	// private UmlWrapper umlWrapper;

	public InitialPanel(Gui gui) {
		super(gui);
	}

	protected void setUp() {
		this.analying = false;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.add(Box.createVerticalGlue());
		this.add(this.generateDankTitle());
		this.add(Box.createVerticalStrut(25));
		Box configBox = this.generateConfigBox();
		Box buttonsBox = this.generateButtonsBox();
		this.add(buttonsBox);
		this.add(configBox);
		this.add(Box.createVerticalStrut(25));
		this.add(this.generateProgressBox());
		this.add(Box.createVerticalGlue());
		this.add(Box.createVerticalGlue());
		this.setMaximumSize(new Dimension(500, 500));
		this.setPreferredSize(new Dimension(500, 500));
		this.setMinimumSize(new Dimension(500, 500));

		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	private Box generateDankTitle() {
		Box titleBox = Box.createHorizontalBox();
		JLabel titleLabel = new JLabel("Title");
		titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		titleBox.add(titleLabel);
		return titleBox;
	}

	private Box generateButtonsBox() {
		Box buttonsBox = Box.createHorizontalBox();
		buttonsBox.add(Box.createHorizontalGlue());
		JButton loadConfigButton = new ConfigButton(this, this.configLabel);
		loadConfigButton.setPreferredSize(new Dimension(150, 30));
		buttonsBox.add(loadConfigButton);
		buttonsBox.add(Box.createHorizontalStrut(10));

		JButton analyzeButton = new JButton("Analyze");
		analyzeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						analyze();
					}
				}).start();
			}
		});
		analyzeButton.setPreferredSize(new Dimension(150, 30));
		buttonsBox.add(analyzeButton);
		buttonsBox.add(Box.createHorizontalGlue());
		return buttonsBox;
	}

	private Box generateConfigBox() {
		Box configBox = Box.createHorizontalBox();
		this.configLabel = new JLabel("Current Config: N/A");
		configBox.add(Box.createHorizontalStrut(20));
		configBox.add(this.configLabel);
		configBox.add(Box.createHorizontalGlue());
		return configBox;
	}

	private Box generateProgressBox() {
		Box progressBox = Box.createVerticalBox();

		Box progressLabelBox = Box.createHorizontalBox();
		this.progressLabel = new JLabel("Analyzing class");
		progressLabelBox.add(this.progressLabel);
		progressBox.add(progressLabelBox);
		progressBox.add(Box.createVerticalStrut(10));

		Box progressBarBox = Box.createHorizontalBox();
		progressBarBox.add(Box.createHorizontalGlue());
		this.progressBar = new JProgressBar();
		this.progressBar.setVisible(false);
		this.progressBar.setIndeterminate(true);

		progressBarBox.add(this.progressBar);
		progressBarBox.add(Box.createHorizontalGlue());
		progressBox.add(progressBarBox);
		return progressBox;
	}

	private void analyze() {
		this.setProgressText("Currently analyzing.");
		this.progressBar.setVisible(true);
		try {
			this.getGui().analyze();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			this.setProgressText("Something went wrong");
			this.progressBar.setVisible(false);
		}
		this.replacePanel(new ResultPanel(this.getGui()));
	}

	private void setProgressText(String newText) {
		this.progressLabel.setText(newText);
	}
}
