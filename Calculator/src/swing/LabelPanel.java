package swing;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabelPanel extends JPanel implements ActionListener {

	JLabel process, result;
	double operand1, operand2;
	int count = 0;
	String midOperator, lastOperator;
	String operator = "";// operator �� R�� x^���� Ư���� ��Ȳ���� R, x^ �����ϱ� ���� ����
	boolean sq = false; // ���� ���� ����

	private CalculatorFrame Frame;

	public LabelPanel(CalculatorFrame frame) {
		Font font = new Font("Serif", Font.BOLD, 35);

		LayoutManager layoutManager = new GridLayout(2, 1); // 1,1 �ϸ� ���ڰ� �� �� ���� ����
		this.setLayout(layoutManager);

		this.process = new JLabel("", JLabel.RIGHT);
		this.result = new JLabel("0", JLabel.RIGHT);
		this.add(process);
		this.add(result);

		process.setAlignmentX(RIGHT_ALIGNMENT); // ������ ����
		result.setAlignmentX(RIGHT_ALIGNMENT);

		process.setFont(font);
		result.setFont(font);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton) e.getSource(); // ��ư
		String nowText = this.result.getText();
		midOperator = bt.getText(); // ������ ��ư

		if (midOperator.equals("AC")) {
			operand1 = 0;
			operand2 = 0;
			count = 0;
			this.result.setText("0");
			return;
		}
		if (midOperator.equals("C")) {
			this.result.setText("0");
			return;
		}
		if (midOperator.equals("��")) {
			String sOperand = this.result.getText();
			sOperand = sOperand.substring(0, sOperand.length() - 1);
			this.result.setText(sOperand);
			if (this.result.getText().equals("")) // ����ְ� �Ǹ� 0���� ������ֱ�..
				this.result.setText("0");
			return;
		}
		if (midOperator.equals("��")) {
			operator = "��";
			return;
		}
		if (midOperator.equals("x��")) {
			operator = "x��";
			return;
		}
		if (midOperator.equals("!")) {
			operator = "!";
			return;
		}
		if (!nowText.equals("0") && midOperator.equals("+/-")) { // (+/-) ��ȯ
			double num = Double.parseDouble(nowText) * -1;
			if (num == (int) num) { // ������ �� �Ҽ��� ( .0 ) ǥ������ �ʱ� ����
				int newNum = (int) num;
				nowText = Integer.toString(newNum);
			} else {
				nowText = Double.toString(num);
			}
			this.result.setText(nowText);
			return;
		}
		if (midOperator != "=") { // "=" �ƴ� �������� ��
			if (count != 0) { // ù ���ΰ�? ����
				operand2 = Double.parseDouble(nowText);
				if (operator.equals("��")) {
					operand2 = Math.sqrt(operand2);
					operator = "";
				}
				if (operator.equals("x��")) {
					BigDecimal bNow = new BigDecimal(nowText);
					operand2 = Double.parseDouble(bNow.multiply(bNow).toString());
					operator = "";
				}
				if (operator.equals("!")) {
					int factOperand = 1;
					for (int i = (int) operand2; i > 0; i--) {
						factOperand *= i;
					}
					operand2 = factOperand;
					operator = "";
				}
				BigDecimal b1 = new BigDecimal(Double.toString(operand1));
				BigDecimal b2 = new BigDecimal(Double.toString(operand2));
				switch (lastOperator) {
				case "+":
					operand1 = Double.parseDouble(b1.add(b2).toString());
					break;
				case "-":
					operand1 = Double.parseDouble(b1.subtract(b2).toString());
					break;
				case "*":
					operand1 = Double.parseDouble(b1.multiply(b2).toString());
					break;
				case "��":
					operand1 = Double.parseDouble(b1.divide(b2).toString());
					;
					break;
				case "%":
					BigDecimal b3 = new BigDecimal(Double.toString(0.01));
					operand1 = Double.parseDouble(b1.multiply(b2).multiply(b3).toString());
					break;
				}
			} else {
				operand1 = Double.parseDouble(nowText);
			}
			lastOperator = midOperator;
			this.result.setText("0");
			count++;

		} else { // "=" ������
			operand2 = Double.parseDouble(nowText);

			if (operator.equals("��")) {
				operator = "";
				operand2 = Math.sqrt(operand2);
			}
			if (operator.equals("x��")) {
				operator = "";
				BigDecimal bNow = new BigDecimal(nowText);
				operand2 = Double.parseDouble(bNow.multiply(bNow).toString());

			}
			if (operator.equals("!")) {
				operator = "";
				int factOperand = 1;
				for (int i = (int) operand2; i > 0; i--) {
					factOperand *= i;
				}
				operand2 = factOperand;
			}
			try {
				BigDecimal b1 = new BigDecimal(Double.toString(operand1));
				BigDecimal b2 = new BigDecimal(Double.toString(operand2));
				switch (lastOperator) {
				case "+":
					operand1 = Double.parseDouble(b1.add(b2).toString());
					break;
				case "-":
					operand1 = Double.parseDouble(b1.subtract(b2).toString());
					break;
				case "*":
					operand1 = Double.parseDouble(b1.multiply(b2).toString());
					break;
				case "��":
					operand1 = Double.parseDouble(b1.divide(b2).toString());
					break;
				case "%":
					BigDecimal b3 = new BigDecimal(Double.toString(0.01));
					operand1 = Double.parseDouble(b1.multiply(b2).multiply(b3).toString());
					break;
				}
			} catch (NullPointerException error) {
				operand1 = operand2; // lastOperator�� ���� ���ϱ�
			}
			if (operand1 == (int) operand1) {
				int intOperand1 = (int) operand1;
				this.result.setText(Integer.toString(intOperand1));
			} else {
				this.result.setText(Double.toString(operand1));
			}
			operand1 = 0;
			count = 0;
		}
	}
}
