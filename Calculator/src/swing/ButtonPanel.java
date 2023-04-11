package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	JButton bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btPer, btDiv, btDel, btBack, btAdd, btSub, btMul, btSign,
			btDot, btEqual, btRoot, btFactorial, btSquared, btAc;

	String operand1, operand2, operator, result;

	public ButtonPanel(CalculatorFrame frame, LabelPanel label) {
		Color gray = new Color(204, 204, 204);
		Color white = new Color(255, 255, 255);
		Color lightGreen = new Color(210, 212, 253);

		Font font = new Font("Serif", Font.BOLD, 20);

		LayoutManager layoutManager = new GridLayout(6, 4, 2, 2); // 버튼 배치, 칸 사이 간격까지
		this.setLayout(layoutManager);

		// 버튼

		btPer = new JButton("%");
		btPer.addActionListener(label);
		btAc = new JButton("AC");
		btAc.addActionListener(label);
		btDel = new JButton("C");
		btDel.addActionListener(label);
		btBack = new JButton("←");
		btBack.addActionListener(label);

		bt1 = new JButton("1");
		bt1.addActionListener(frame);
		bt2 = new JButton("2");
		bt2.addActionListener(frame);
		bt3 = new JButton("3");
		bt3.addActionListener(frame);
		btAdd = new JButton("+");
		btAdd.addActionListener(label);

		bt4 = new JButton("4");
		bt4.addActionListener(frame);
		bt5 = new JButton("5");
		bt5.addActionListener(frame);
		bt6 = new JButton("6");
		bt6.addActionListener(frame);
		btSub = new JButton("-");
		btSub.addActionListener(label);

		bt7 = new JButton("7");
		bt7.addActionListener(frame);
		bt8 = new JButton("8");
		bt8.addActionListener(frame);
		bt9 = new JButton("9");
		bt9.addActionListener(frame);
		btMul = new JButton("*");
		btMul.addActionListener(label);

		btSign = new JButton("+/-");
		btSign.addActionListener(label);
		bt0 = new JButton("0");
		bt0.addActionListener(frame);
		btDot = new JButton(".");
		btDot.addActionListener(frame);
		btDiv = new JButton("÷");
		btDiv.addActionListener(label);

		btFactorial = new JButton("!");
		btFactorial.addActionListener(label);
		btRoot = new JButton("√");
		btRoot.addActionListener(label);
		btSquared = new JButton("x²");
		btSquared.addActionListener(label);
		btEqual = new JButton("=");
		btEqual.addActionListener(label);

		// 패널에 버튼 추가

		this.add(btPer);
		this.add(btAc);
		this.add(btDel);
		this.add(btBack);

		this.add(bt1);
		this.add(bt2);
		this.add(bt3);
		this.add(btAdd);

		this.add(bt4);
		this.add(bt5);
		this.add(bt6);
		this.add(btSub);

		this.add(bt7);
		this.add(bt8);
		this.add(bt9);
		this.add(btMul);

		this.add(btSign);
		this.add(bt0);
		this.add(btDot);
		this.add(btDiv);

		this.add(btFactorial);
		this.add(btRoot);
		this.add(btSquared);
		this.add(btEqual);

		// 버튼 폰트

		bt1.setFont(font);
		bt2.setFont(font);
		bt3.setFont(font);
		bt4.setFont(font);
		bt5.setFont(font);
		bt6.setFont(font);
		bt7.setFont(font);
		bt8.setFont(font);
		bt9.setFont(font);
		bt0.setFont(font);

		btPer.setFont(font);
		btAc.setFont(font);
		btDel.setFont(font);
		btBack.setFont(font);
		btAdd.setFont(font);
		btSub.setFont(font);
		btMul.setFont(font);
		btSign.setFont(font);
		btDot.setFont(font);
		btDiv.setFont(font);

		btFactorial.setFont(font);
		btRoot.setFont(font);
		btSquared.setFont(font);
		btEqual.setFont(font);

		// 버튼 배경 색

		bt1.setBackground(white);
		bt2.setBackground(white);
		bt3.setBackground(white);
		bt4.setBackground(white);
		bt5.setBackground(white);
		bt6.setBackground(white);
		bt7.setBackground(white);
		bt8.setBackground(white);
		bt9.setBackground(white);
		bt0.setBackground(white);

		btPer.setBackground(gray);
		btAc.setBackground(gray);
		btDel.setBackground(gray);
		btBack.setBackground(gray);
		btAdd.setBackground(gray);
		btSub.setBackground(gray);
		btMul.setBackground(gray);
		btSign.setBackground(gray);
		btDot.setBackground(gray);
		btDiv.setBackground(gray);

		btFactorial.setBackground(gray);
		btRoot.setBackground(gray);
		btSquared.setBackground(gray);
		btEqual.setBackground(lightGreen);

	}

}
