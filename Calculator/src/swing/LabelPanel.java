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
	String operator = "";// operator 는 R나 x^같은 특수한 상황에서 R, x^ 저장하기 위한 변수
	boolean sq = false; // 제곱 포함 여부

	private CalculatorFrame Frame;

	public LabelPanel(CalculatorFrame frame) {
		Font font = new Font("Serif", Font.BOLD, 35);

		LayoutManager layoutManager = new GridLayout(2, 1); // 1,1 하면 숫자가 좀 더 위에 생김
		this.setLayout(layoutManager);

		this.process = new JLabel("", JLabel.RIGHT);
		this.result = new JLabel("0", JLabel.RIGHT);
		this.add(process);
		this.add(result);

		process.setAlignmentX(RIGHT_ALIGNMENT); // 오른쪽 정렬
		result.setAlignmentX(RIGHT_ALIGNMENT);

		process.setFont(font);
		result.setFont(font);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bt = (JButton) e.getSource(); // 버튼
		String nowText = this.result.getText();
		midOperator = bt.getText(); // 연산자 버튼

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
		if (midOperator.equals("←")) {
			String sOperand = this.result.getText();
			sOperand = sOperand.substring(0, sOperand.length() - 1);
			this.result.setText(sOperand);
			if (this.result.getText().equals("")) // 비어있게 되면 0으로 만들어주기..
				this.result.setText("0");
			return;
		}
		if (midOperator.equals("√")) {
			operator = "√";
			return;
		}
		if (midOperator.equals("x²")) {
			operator = "x²";
			return;
		}
		if (midOperator.equals("!")) {
			operator = "!";
			return;
		}
		if (!nowText.equals("0") && midOperator.equals("+/-")) { // (+/-) 전환
			double num = Double.parseDouble(nowText) * -1;
			if (num == (int) num) { // 정수일 때 소수점 ( .0 ) 표현하지 않기 위함
				int newNum = (int) num;
				nowText = Integer.toString(newNum);
			} else {
				nowText = Double.toString(num);
			}
			this.result.setText(nowText);
			return;
		}
		if (midOperator != "=") { // "=" 아닌 연산자일 때
			if (count != 0) { // 첫 수인가? 여부
				operand2 = Double.parseDouble(nowText);
				if (operator.equals("√")) {
					operand2 = Math.sqrt(operand2);
					operator = "";
				}
				if (operator.equals("x²")) {
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
				case "÷":
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

		} else { // "=" 받으면
			operand2 = Double.parseDouble(nowText);

			if (operator.equals("√")) {
				operator = "";
				operand2 = Math.sqrt(operand2);
			}
			if (operator.equals("x²")) {
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
				case "÷":
					operand1 = Double.parseDouble(b1.divide(b2).toString());
					break;
				case "%":
					BigDecimal b3 = new BigDecimal(Double.toString(0.01));
					operand1 = Double.parseDouble(b1.multiply(b2).multiply(b3).toString());
					break;
				}
			} catch (NullPointerException error) {
				operand1 = operand2; // lastOperator가 없을 때니까
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
