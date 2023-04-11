package swing;

public class CalculatorSwing { // ½ºÀ® °è»ê±â
	public static void main(String[] args) {
		CalculatorFrame frame = new CalculatorFrame();
		LabelPanel labelPanel = new LabelPanel(frame);
		ButtonPanel ButtonPanel = new ButtonPanel(frame, labelPanel);

		frame.setVisible(true);

	}
}
