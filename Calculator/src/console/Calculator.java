package console;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class Calculator { // �߰����
	int operator;

	public double readNumber() { // �� �Է¹޴� �޼ҵ� ASCII -> Integer / Float
		int intOperand = 0; // �����κ�
		double decimalOperand = 0; // �Ҽ��κ�
		double operand = 0; // ���� ���� (����) intOperand + decimalOperand
		int count = 0; // ó�� �Է��ϴ� ������ ����
		boolean minus = false; // ���̳ʽ��� ��츦 ����
		int input;
		try {
			input = System.in.read();
			while (input >= '0' && input <= '9' || count == 0 && input == '-') {
				count++;
				if (input == '-') { // ù �� ���̳ʽ�
					minus = true;
					input = System.in.read();
				}
				intOperand = intOperand * 10 + (input - '0'); // ASCII to integer
				input = System.in.read();
			}

			if (input == '.') { // ���� �Ҽ��� ��
				int dot = input; // .������ �Ҽ� ����̹Ƿ� �Ҽ��� �Ʒ� �� ��ȯ�ؾ���
				double i = 1;
				input = System.in.read();
				while (input >= '0' && input <= '9') {
					int a = (input - '0');
					String sA = Integer.toString(a);
					BigDecimal b01 = new BigDecimal("0.1");
					BigDecimal b02 = new BigDecimal(Double.toString(i));

					String pow = (b01.multiply(b02).toString()); // 0.1���ϱ�� �Ҽ��κ� ä���ֱ�
					i = Double.parseDouble(b01.multiply(b02).toString()); // �ŵ����� ���� i��ȭ

					BigDecimal b1 = new BigDecimal(sA);
					BigDecimal b2 = new BigDecimal(pow);
					decimalOperand = decimalOperand + Double.parseDouble(b1.multiply(b2).toString()); // ASCII to Float
					input = System.in.read();
				}

			}

			this.operator = input; // ������

			if (decimalOperand == 0) {
				operand = intOperand;
			} else {
				operand = intOperand + decimalOperand;
			}
			if (minus)
				operand *= -1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return operand;
	}

	public void readEnter() { // ���� �Է� �޾��ִ� �޼ҵ�
		try {
			int input = System.in.read();
			input = System.in.read();
			if (this.operator == '!') // ���丮�� �� ȣ�� ��
				input = System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> addArr(ArrayList<Integer> arr, int num) { // �� �ڸ��� ��� �迭�����ֱ�
		int i = 0;
		while (num != 0) {
			arr.add(num % 10);
			num /= 10;
			i++;
		}
		return arr;
	}

	public void printArr(ArrayList<Integer> arr) { // ���� �迭 ��� (ASCII)
		for (int i = 0; i < arr.size(); i++) {
			char a = (char) (arr.get(i) + 48);
			System.out.print(a);
		}
		System.out.println();
	}

	public void compute() { // ���� �޼ҵ忡�� ȣ��
		int operator = 0; // ���� ������ �ʱⰪ
		while (operator != 13 || operator != 'x') { // 13�� ����. / �Է� �� ����
			ArrayList<Integer> arr = new ArrayList<>();
			double result = 0;
			int resultFact = 1; // ���丮�� ��� ����
			String sResult = "";
			int input;

			double operand1 = this.readNumber(); // operand1

			if (this.operator == 'x' || this.operator == 13) // x, enter �Է� �� ����
				break;

			operator = this.operator; // readNumber���� �����鼭 �Է��� ������ ����

			if (operator == '!') { // factorial
				int op1 = (int) operand1;
				if (op1 == 0) { // 0! == 1
					char a = (char) (op1 + '1');
					System.out.println(a);
					this.readEnter();
					continue;
				}
				// 0!�ƴ� ���
				int i = 0;
				for (int j = op1; j > 0; j--) {
					resultFact *= j;
				}
				arr = addArr(arr, resultFact); // �� ���ھ� �� �迭�� ����ֱ�
				Collections.reverse(arr); // �迭 ������
				printArr(arr); // �ϳ��� ��� / -> ASCII
				this.readEnter();
				continue;
			}

			double operand2 = this.readNumber(); // = �Է¹޾ƾ� ���.

			if (operator == 'R') { // ��Ʈ ���
				int i = 0;
				result = Math.sqrt(operand2); // ��Ʈ �޼ҵ�
				int intResult = (int) result; // �����κ�
				if (result == intResult) { // Integer -> ASCII
					arr = addArr(arr, intResult);

					if (result == 0) { // ��� ����� 0�� ���
						char a = (char) (result + 48); // + 48 or + '0'
						System.out.println(a);
					} else { // �Ի� ����� 0�� �ƴ� ���
						Collections.reverse(arr); // �迭 ������
						printArr(arr);
					}
				} else { // Float -> ASCII / BigDecimal�� ��Ȯ�� �Ҽ� ��� ����
					double dResult = result;
					String dOperand = Double.toString(dResult);
					String intOperand = Double.toString(intResult);
					BigDecimal b1 = new BigDecimal(dOperand);
					BigDecimal b2 = new BigDecimal(intOperand);
					double decimalResult = Double.parseDouble(b1.subtract(b2).toString()); // �Ҽ� �κ� / .xxx

					arr = addArr(arr, intResult);

					Collections.reverse(arr); // �迭 ������
					arr.add('.' - 48);

					while ((decimalResult * 10 != (int) (decimalResult)) && decimalResult != 0) {
						int deci;
						String sDeciResult = Double.toString(decimalResult);
						BigDecimal b3 = new BigDecimal(sDeciResult);
						BigDecimal b4 = new BigDecimal("10");
						decimalResult = Double.parseDouble(b3.multiply(b4).toString());

						deci = (int) decimalResult;
						sDeciResult = Double.toString(decimalResult);
						String sDeci = Double.toString(deci);
						b3 = new BigDecimal(sDeciResult);
						b4 = new BigDecimal(sDeci);
						decimalResult = Double.parseDouble(b3.subtract(b4).toString());
						arr.add(deci);
					}
					printArr(arr);
				} // ��Ʈ ��� �Ҽ���� ��
				this.readEnter();
				continue;
			} // ��Ʈ ��� ��

			this.readEnter(); // read enter

			// ��Ģ����
			if (operator == '+') {
				result = operand1 + operand2;
			} else if (operator == '-') {
				result = operand1 - operand2;
			} else if (operator == '*') {
				result = operand1 * operand2;
			} else if (operator == '/') {
				result = operand1 / operand2;
				if (Double.isInfinite(result)) { // 0���� ���� ��� ó��
					System.out.println("0���δ� ���� �� �����ϴ�. ����� �ٽ� �����ϼ���");
					continue;
				}
			} else if (operator == '^') // �ŵ�����
				result = Math.pow(operand1, operand2);
			else if (operator == '%') // �ۼ�Ʈ. / 100 % 2 => 100�� 2�ۼ�Ʈ / 0.02
				result = operand1 * operand2 * 0.01;

			// ��� ��� --> ASCII

			if (result == (int) result) { // ������ ��� integer -> ASCII
				int intResult = (int) result;
				int i = 0;

				if (intResult < 0)
					intResult *= -1;

				arr = addArr(arr, intResult);

				if (result < 0) // ���� �� '-'�� ���� ��ġ ( �ڿ��� �迭 ������ ���̴� )
					arr.add('-' - 48);

				if (result == 0) { // ��� ����� 0�� ���
					char a = (char) (result + 48);
					System.out.println(a);
				} else { // �Ի� ����� 0�� �ƴ� ���
					Collections.reverse(arr); // �迭 ������
					printArr(arr);
				}
			} else { // �Ҽ��� ��� --> �Ҽ��� ����Ͽ� char�� ��� ���� ��� ����..
				String sOperand1 = Double.toString(operand1);
				String sOperand2 = Double.toString(operand2);
				BigDecimal b1 = new BigDecimal(sOperand1);
				BigDecimal b2 = new BigDecimal(sOperand2);
				BigDecimal bPer = new BigDecimal("0.01");

				if (operator == '+') {
					sResult = b1.add(b2).toString();
				} else if (operator == '-') {
					sResult = b1.subtract(b2).toString();
				} else if (operator == '*') {
					sResult = b1.multiply(b2).toString();
				} else if (operator == '/') {
					sResult = b1.divide(b2, 4, BigDecimal.ROUND_HALF_UP).toString(); // �Ҽ��� �Ʒ� 4�ڸ����� ǥ��, 5�ڸ����� �ݿø�
				} else if (operator == '^') {
					sResult = b1.pow((int) operand2).toString();
				} else if (operator == '%') {
					sResult = b1.multiply(b2).multiply(bPer).toString();
				}

				// Float --> ASCII
				int i = 0;
				result = Double.parseDouble(sResult); // ���� Float --> ASCII
				double dResult = result;
				if (dResult < 0) {
					dResult *= -1;
				}
				int intResult = (int) dResult;
				String dOperand = Double.toString(dResult);
				String intOperand = Double.toString(intResult);
				b1 = new BigDecimal(dOperand);
				b2 = new BigDecimal(intOperand);
				double decimalResult = Double.parseDouble(b1.subtract(b2).toString()); // �Ҽ� �κ� / .xxx

				while (intResult != 0 || i == 0) { // intResult == 0 && i != 0 �� �� �������
					arr.add(intResult % 10);
					intResult /= 10;
					i++;
				}

				if (result < 0) { // ���� �� - ���� ��ġ ( �ڿ��� �迭 ������ ���̴� )
					arr.add('-' - 48);
				}

				Collections.reverse(arr); // �迭 ������
				arr.add('.' - 48);

				while ((decimalResult * 10 != (int) (decimalResult)) && decimalResult != 0) { // �Ҽ��κ��� 0�� �ǰų�, �� �̻� ���� ��
																								// ����
					int deci;
					String sDeciResult = Double.toString(decimalResult);
					BigDecimal b3 = new BigDecimal(sDeciResult);
					BigDecimal b4 = new BigDecimal("10");
					decimalResult = Double.parseDouble(b3.multiply(b4).toString());

					deci = (int) decimalResult; // �迭�� ���� �κ�
					sDeciResult = Double.toString(decimalResult);
					String sDeci = Double.toString(deci);
					b3 = new BigDecimal(sDeciResult);
					b4 = new BigDecimal(sDeci);
					decimalResult = Double.parseDouble(b3.subtract(b4).toString()); // �迭�� �ְ� ���� �κ� ���� ����
					arr.add(deci);
				}
				printArr(arr);
			}
		}
	}
}
