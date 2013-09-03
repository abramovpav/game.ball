package by.bsuir.iit.abramov.game.ball.view;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import by.bsuir.iit.abramov.game.ball.Util.ExtJButton;

public class NewUnitDialog extends JDialog {

	private static final String	TITLE	= "New ID";
	private final JTextField	input;
	private final ExtJButton	button;
	private final List<Integer>	IDs;

	public NewUnitDialog(/* final Set<Integer> IDs */) {

		super();
		setTitle(NewUnitDialog.TITLE);
		this.setBounds(0, 0, 200, 100);
		setResizable(false);
		final JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout());
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		input = new JTextField("sd");
		input.setColumns(15);
		contentPane.add(input);// , BorderLayout.CENTER);
		button = new ExtJButton("Create", this);
		contentPane.add(button);// , BorderLayout.SOUTH);
		IDs = new ArrayList<Integer>();
		IDs.addAll(IDs);
	}
}
