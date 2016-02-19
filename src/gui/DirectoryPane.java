package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
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
	private JTree tree;
	private Component scrollPane;

	public DirectoryPane(Gui gui) {
		super(gui);
	}

	@Override
	protected void setUp() {
//		this.setBorder(BorderFactory.createLineBorder(Color.red, 5));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setUpTree();
		this.setBackground(Color.white);
	}

	private void setUpTree() {
		final DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

		final DefaultMutableTreeNode accessibility = add(root, "Accessibility", true);
		add(accessibility, "Move system caret with focus/selection changes", false);
		add(accessibility, "Always expand alt text for images", true);
		root.add(accessibility);

		final DefaultMutableTreeNode browsing = new DefaultMutableTreeNode("Browsing");
		add(browsing, "Notify when downloads complete", true);
		add(browsing, "Disable script debugging", true);
		add(browsing, "Use AutoComplete", true);
		add(browsing, "Browse in a new process", false);
		root.add(browsing);

		final DefaultTreeModel treeModel = new DefaultTreeModel(root);
		this.tree = new JTree(treeModel);

		final CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
		tree.setCellRenderer(renderer);

		final CheckBoxNodeEditor editor = new CheckBoxNodeEditor(tree);
		tree.addTreeExpansionListener(new TreeExpansionListener() {

			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				System.out.println("tree expanded");
				resize();
			}

			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				System.out.println("tree collapsed");
				resize();
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
//		this.scrollPane = new JLabel("Hello");
//		this.scrollPane.set
//		this.scrollPane.setBackground(Color.cyan);
//		this.scrollPane.setForeground(Color.cyan);
//		this.scrollPane.set
//		 this.scrollPane = new JScrollPane(tree);
//		 this.scrollPane.setBackground(Color.cyan);
//		 this.scrollPane.setLocation(new Point(0, 0));
//		 this.tree.setPreferredSize(new Dimension(100, 1000));
		 Dimension size = new Dimension(100, 1017);
//		 this.scrollPane.setPreferredSize(size);
//		 this.setMinimumSize(size);
//		 this.tree.setPreferredSize(size);
//		 this.scrollPane.setPreferredSize(size);
		 size = new Dimension(100, 1017);
//		 this.setPreferredSize(size);
		 this.setMaximumSize(size);
//		this.add(this.scrollPane);
		System.out.println(this.getComponentCount());
		 this.add(tree);
	}

	private static DefaultMutableTreeNode add(final DefaultMutableTreeNode parent, final String text,
			final boolean checked) {
		final CheckBoxNodeData data = new CheckBoxNodeData(text, checked);
		final DefaultMutableTreeNode node = new DefaultMutableTreeNode(data);
		parent.add(node);
		return node;
	}
	
	private void resize() {
//		 this.scrollPane.setLocation(new Point(0, 0));
		System.out.println("gui");
		System.out.println("width: " + this.getGui().getContentPane().getWidth());
		System.out.println("height: " + this.getGui().getContentPane().getHeight());
		System.out.println();
		System.out.println("tree");
		System.out.println(this.tree.getWidth());
		System.out.println(this.tree.getHeight());

		System.out.println();
		System.out.println("scroll pane");
//		System.out.println(this.scrollPane.getWidth());
//		System.out.println(this.scrollPane.getHeight());
		System.out.println(this.getLocation());
//		System.out.println(this.scrollPane.getLocation());
		System.out.println(this.tree.getLocation());

//		System.out.println();
//		System.out.println(this.tree.getWidth());
//		System.out.println(this.tree.getHeight());
//		this.scrollPane.setMaximumSize(new Dimension(this.getGui().getWidth() / 5, this.getGui().getHeight()));
//		this.setPreferredSize(this.scrollPane.getPreferredSize());
//		 this.setSize(500, 1080);
		System.out.println();
		System.out.println("direcotyr pane");
		System.out.println(this.getWidth());
		System.out.println(this.getHeight());
//		revalidate();
//		this.getGui().updatePaint();
	}

}
