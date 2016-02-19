package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import checkboxtree.CheckBoxNodeData;
import checkboxtree.CheckBoxNodeEditor;
import checkboxtree.CheckBoxNodeRenderer;

public class DirectoryPane extends APanel {
	private static final long serialVersionUID = -1869406409371334949L;
	private DefaultTreeModel treeModel;
	private ImagePanel imagePanel;

	public DirectoryPane(Gui gui, ImagePanel imagePanel) {
		super(gui);
		this.imagePanel = imagePanel;
	}

	@Override
	protected void setUp() {
		// this.setBorder(BorderFactory.createLineBorder(Color.red, 5));
		// this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setLayout(new BorderLayout());
		this.add(this.generateTree());
		// this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(this.generateButton(), BorderLayout.PAGE_END);
		this.setBackground(Color.white);
		this.setMinimumSize(new Dimension(200, 500));
	}

	private JTree generateTree() {
		String path = this.getGui().getProperty("Input-Folder");
		File dir = new File("").getAbsoluteFile();
		File next = new File(dir, path);
		System.out.println("generating tree with root \"" + dir + "\"");
		final CheckBoxNodeData data = new CheckBoxNodeData(path, true);
		final DefaultMutableTreeNode root = new DefaultMutableTreeNode(data);
		this.walk(root, next);

		this.treeModel = new DefaultTreeModel(root);
		JTree tree = new JTree(treeModel);

		final CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
		tree.setCellRenderer(renderer);

		final CheckBoxNodeEditor editor = new CheckBoxNodeEditor(tree);
		tree.addTreeExpansionListener(new TreeExpansionListener() {

			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				System.out.println("tree expanded");
			}

			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				System.out.println("tree collapsed");
			}
		});
		tree.setCellEditor(editor);
		tree.setEditable(true);

		// listen for changes in the selection
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(final TreeSelectionEvent e) {
				System.out.println(System.currentTimeMillis() + ": selection changed");
			}
		});

		// listen for changes in the model (including check box toggles)
		treeModel.addTreeModelListener(new TreeModelListener() {
			@Override
			public void treeNodesChanged(final TreeModelEvent e) {
				System.out.println(System.currentTimeMillis() + ": nodes changed");
				Object[] children = e.getChildren();
				if (children == null) {
					// root
					DefaultMutableTreeNode blah = (DefaultMutableTreeNode) e.getPath()[0];
					CheckBoxNodeData data = (CheckBoxNodeData) blah.getUserObject();
					setChecked(blah, data.isChecked());
					blah.breadthFirstEnumeration();
				} else {
					// non root
					for (Object obj : children) {
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) obj;
						CheckBoxNodeData directoryData = (CheckBoxNodeData) node.getUserObject();
						setChecked(node, directoryData.isChecked());
					}
				}
				repaint();
			}

			@Override
			public void treeNodesInserted(final TreeModelEvent e) {
				System.out.println(System.currentTimeMillis() + ": nodes inserted");
			}

			@Override
			public void treeNodesRemoved(final TreeModelEvent e) {
				System.out.println(System.currentTimeMillis() + ": nodes removed");
			}

			@Override
			public void treeStructureChanged(final TreeModelEvent e) {
				System.out.println(System.currentTimeMillis() + ": structure changed");
			}
		});
		return tree;
	}

	private void setChecked(DefaultMutableTreeNode parent, boolean checked) {
		Enumeration<DefaultMutableTreeNode> en = parent.breadthFirstEnumeration();
		while (en.hasMoreElements()) {
			DefaultMutableTreeNode node = en.nextElement();
			CheckBoxNodeData data = (CheckBoxNodeData) node.getUserObject();
			data.setChecked(checked);
		}

	}

	private Box generateButton() {
		Box box = Box.createHorizontalBox();
		JButton button = new JButton("Reanalyze");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						button.setEnabled(false);
						String initialText = button.getText();
						reanalyze(button);
						button.setText(initialText);
						button.setEnabled(true);
					}
				}).start();
			}
		});
		button.setMaximumSize(new Dimension(197, 25));
		box.add(button);
		return box;
	}

	public void reanalyze(JButton button) {
		this.imagePanel.loading();
		button.setText("TODO I'm analyzing");
		System.out.println(this.getCheckedClasses());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.imagePanel.changeImage("SDout.png");
	}

	private ArrayList<String> getCheckedClasses() {
		ArrayList<String> strs = new ArrayList<String>();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) this.treeModel.getRoot();
		Enumeration<DefaultMutableTreeNode> en = root.depthFirstEnumeration();
		while (en.hasMoreElements()) {
			DefaultMutableTreeNode node = en.nextElement();
			CheckBoxNodeData data = (CheckBoxNodeData) node.getUserObject();

			if (node.getChildCount() == 0 && data.isChecked()) {
				// checked leafs
				strs.add(buildPath(node));
			}
//			System.out.println(node.getChildCount() + ": " + node);
		}
		return strs;
	}

	private String buildPath(DefaultMutableTreeNode node) {
		CheckBoxNodeData data = (CheckBoxNodeData) node.getUserObject();
		if (node.getParent() != this.treeModel.getRoot()) {
			return this.buildPath((DefaultMutableTreeNode) node.getParent()) + "." + data.getText();
		} else {
			return data.getText();
		}
	}

	private void walk(DefaultMutableTreeNode parent, File file) {
		File[] files = file.listFiles();
		if (files == null) {
			return;
		}
		for (File f : files) {
			String fileName = f.getName();
			if (!f.isDirectory()) {
				fileName = fileName.substring(0, fileName.length() - 5);
			}
			DefaultMutableTreeNode child = add(parent, fileName, true);
			this.walk(child, new File(file, f.getName()));
		}
		// final DefaultMutableTreeNode accessibility = add(root, "Accessibility", true);
		// add(accessibility, "Move system caret with focus/selection changes", false);
		// add(accessibility, "Always expand alt text for images", true);
		// root.add(accessibility);

	}

	private static DefaultMutableTreeNode add(final DefaultMutableTreeNode parent, final String text,
			final boolean checked) {
		final CheckBoxNodeData data = new CheckBoxNodeData(text, checked);
		final DefaultMutableTreeNode node = new DefaultMutableTreeNode(data);
		parent.add(node);
		return node;
	}
}
