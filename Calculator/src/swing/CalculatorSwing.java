package swing;

public class CalculatorSwing { // ���� ����
	public static void main(String[] args) {
		CalculatorFrame frame = new CalculatorFrame();
		LabelPanel labelPanel = new LabelPanel(frame);
		ButtonPanel ButtonPanel = new ButtonPanel(frame, labelPanel);

		frame.setVisible(true);

	}
}
