package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class ConfigButton extends JButton {
	private static final long serialVersionUID = -7788584278786674190L;
	private APanel panel;
	private JLabel configLabel;

	public ConfigButton(APanel panel, JLabel configLabel) {
		super("Load Config");
		this.panel = panel;
		this.configLabel = configLabel;
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadConfig();
			}
		});

	}

	private void loadConfig() {
		JFileChooser fileChooser = new JFileChooser(new File("."));
		int statusCode = fileChooser.showDialog(this, "OK");
		if (statusCode == JFileChooser.APPROVE_OPTION) {
			try {
				this.panel.getGui().loadConfig(fileChooser.getSelectedFile());
				this.setConfigPath(fileChooser.getSelectedFile().getName());
				this.panel.reloadConfig();
			} catch (IOException e) {
				e.printStackTrace();
				this.setConfigPath("Couldn't find the file: " + fileChooser.getSelectedFile());
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				this.setConfigPath("Config file has invalid syntax");
			}
		}
	}

	private void setConfigPath(String newPath) {
		this.configLabel.setText("Current Config: " + newPath);
	}
}
