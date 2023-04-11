package swing;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CalculatorFrame extends JFrame implements ActionListener {
	LabelPanel labelPanel;
	ButtonPanel buttonPanel;

	public CalculatorFrame() {
		this.setSize(450, 500);
		this.setTitle("Calculator");
		// this.setResizable(true);

		LayoutManager layoutManager = new GridLayout(2, 1);
		this.setLayout(layoutManager);

		labelPanel = new LabelPanel(this);
		this.add(labelPanel);

		buttonPanel = new ButtonPanel(this, labelPanel);
		this.add(buttonPanel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); // 실행 시 중앙 배치
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton) e.getSource();
		String labelText = labelPanel.result.getText();
		boolean dot = false;
		if (labelPanel.result.getText().equals("0")) { // 첫 수 입력 시 0이 있을테니.. / 0만 있을 때
			if (bt.getText().equals(".")) {// 0일 때 .찍으면 ..
				labelPanel.result.setText(labelText + bt.getText());
			} else // 0일 때 다른 숫자 누르면 ..
				labelPanel.result.setText(bt.getText());
		} else {// 두번째 입력 시
			labelPanel.result.setText(labelText + bt.getText());
		}
	}
}
