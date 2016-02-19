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
		this.add(this.generateButtonsBox());
		this.add(this.generateConfigBox());
		this.add(Box.createVerticalStrut(25));
		this.add(this.generateProgressBox());
		this.add(Box.createVerticalGlue());
		this.add(Box.createVerticalGlue());
		this.setMaximumSize(new Dimension(500, 500)); // actaully 500, 600??
		this.setPreferredSize(new Dimension(500, 500));
		this.setMinimumSize(new Dimension(500, 500));
		// this.umlWrapper=umlWrapper;

		this.setBorder(BorderFactory.createLineBorder(Color.black));
		// this.setAlignmentY(Component.CENTER_ALIGNMENT);
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

	private void loadConfig() {
		JFileChooser fileChooser = new JFileChooser(new File("."));
		int statusCode = fileChooser.showDialog(this, "OK");
		if (statusCode == JFileChooser.APPROVE_OPTION) {
			try {
				this.getGui().loadConfig(fileChooser.getSelectedFile());
				this.setConfigPath(fileChooser.getSelectedFile().getName());
			} catch (IOException e) {
				e.printStackTrace();
				this.setConfigPath("Couldn't find the file: " + fileChooser.getSelectedFile());
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException
					| InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
				this.setConfigPath("Config file has invalid syntax");
			}
		}
	}

	private void analyze() {
		System.out.println("TODO analyzing");
		this.setProgressText("Currently analyzing. Going to result Panel");
		this.progressBar.setVisible(true);
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		try {
			this.getGui().analyze();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			this.setProgressText("Something went wrong");
			this.progressBar.setVisible(false);
		}
		this.setProgressText("This text should not be here, did not go to the next panel");
		this.replacePanel(new ResultPanel(this.getGui()));
		// if (this.analying) {
		// this.setProgressText("Stopped Analyzes");
		// this.progressBar.setVisible(false);
		// } else {
		// this.setProgressText("Starting to Analyze");
		// this.progressBar.setVisible(true);
		// }
		// this.analying = !this.analying;
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
	}

	private void setConfigPath(String newPath) {
		this.configLabel.setText("Current Config: " + newPath);
	}

	private void setProgressText(String newText) {
		this.progressLabel.setText(newText);
	}
}
