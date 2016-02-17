package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class InitialPanel extends JPanel {
	private String configPath;
	private JLabel configLabel;
	private JLabel progressLabel;
	private JProgressBar progressBar;
	private boolean analying;

	public InitialPanel() {
		this.analying = false;
		this.configPath = "N/A";
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.add(Box.createVerticalGlue());
		this.add(this.generateDankTitle());
		this.add(Box.createVerticalStrut(25));
		this.add(this.generateButtonsBox());
		this.add(this.generateConfigBox());
		this.add(Box.createVerticalStrut(25));
		this.add(this.generateProgressBox());
		this.add(Box.createVerticalGlue());
		this.add(Box.createVerticalGlue());
		this.setMaximumSize(new Dimension(600, 600));
		this.setPreferredSize(new Dimension(400, 400));

		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setAlignmentY(Component.CENTER_ALIGNMENT);
	}

	private Box generateDankTitle() {
		Box titleBox = Box.createHorizontalBox();
		JLabel titleLabel = new JLabel("Dank Title");
		titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		titleBox.add(titleLabel);
		return titleBox;
	}

	private Box generateButtonsBox() {
		Box buttonsBox = Box.createHorizontalBox();
		buttonsBox.add(Box.createHorizontalGlue());
		JButton loadConfigButton = new JButton("Load Config");
		loadConfigButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadConfig();
			}
		});
		loadConfigButton.setPreferredSize(new Dimension(150, 30));
		buttonsBox.add(loadConfigButton);
		buttonsBox.add(Box.createHorizontalStrut(10));

		JButton analyzeButton = new JButton("Analyze");
		analyzeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				analyze();
			}
		});
		analyzeButton.setPreferredSize(new Dimension(150, 30));
		buttonsBox.add(analyzeButton);
		buttonsBox.add(Box.createHorizontalGlue());
		return buttonsBox;
	}

	private Box generateConfigBox() {
		Box configBox = Box.createHorizontalBox();
		this.configLabel = new JLabel("Current Config: " + this.configPath);
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

	private void loadConfig() {
		System.out.println("TODO loading config");
		this.setConfigPath("other stuff");
		this.configPath = "other stuff";
	}

	private void analyze() {
		System.out.println("TODO analyzing");
		if (this.analying) {
			this.setProgressText("Stopped Analyzes");
			this.progressBar.setVisible(false);
		} else {
			this.setProgressText("Starting to Analyze");
			this.progressBar.setVisible(true);
		}
		this.analying = !this.analying;
	}

	private void setConfigPath(String newPath) {
		this.configPath = newPath;
		this.configLabel.setText("Current Config: " + this.configPath);
	}
	
	private void setProgressText(String newText) {
		this.progressLabel.setText(newText);
	}
}
