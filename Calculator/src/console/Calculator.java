package console;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class Calculator { // 중간고사
	int operator;

	public double readNumber() { // 수 입력받는 메소드 ASCII -> Integer / Float
		int intOperand = 0; // 정수부분
		double decimalOperand = 0; // 소수부분
		double operand = 0; // 최종 숫자 (리턴) intOperand + decimalOperand
		int count = 0; // 처음 입력하는 수인지 구분
		boolean minus = false; // 마이너스일 경우를 위함
		int input;
		try {
			input = System.in.read();
			while (input >= '0' && input <= '9' || count == 0 && input == '-') {
				count++;
				if (input == '-') { // 첫 수 마이너스
					minus = true;
					input = System.in.read();
				}
				intOperand = intOperand * 10 + (input - '0'); // ASCII to integer
				input = System.in.read();
			}

			if (input == '.') { // 수가 소수일 때
				int dot = input; // .받으면 소수 계산이므로 소수점 아래 수 변환해야함
				double i = 1;
				input = System.in.read();
				while (input >= '0' && input <= '9') {
					int a = (input - '0');
					String sA = Integer.toString(a);
					BigDecimal b01 = new BigDecimal("0.1");
					BigDecimal b02 = new BigDecimal(Double.toString(i));

					String pow = (b01.multiply(b02).toString()); // 0.1곱하기로 소수부분 채워주기
					i = Double.parseDouble(b01.multiply(b02).toString()); // 거듭제곱 위한 i변화

					BigDecimal b1 = new BigDecimal(sA);
					BigDecimal b2 = new BigDecimal(pow);
					decimalOperand = decimalOperand + Double.parseDouble(b1.multiply(b2).toString()); // ASCII to Float
					input = System.in.read();
				}

			}

			this.operator = input; // 연산자

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

	public void readEnter() { // 엔터 입력 받아주는 메소드
		try {
			int input = System.in.read();
			input = System.in.read();
			if (this.operator == '!') // 팩토리얼 후 호출 시
				input = System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> addArr(ArrayList<Integer> arr, int num) { // 한 자리씩 뜯어 배열어담아주기
		int i = 0;
		while (num != 0) {
			arr.add(num % 10);
			num /= 10;
			i++;
		}
		return arr;
	}

	public void printArr(ArrayList<Integer> arr) { // 담은 배열 출력 (ASCII)
		for (int i = 0; i < arr.size(); i++) {
			char a = (char) (arr.get(i) + 48);
			System.out.print(a);
		}
		System.out.println();
	}

	public void compute() { // 메인 메소드에서 호출
		int operator = 0; // 지역 연산자 초기값
		while (operator != 13 || operator != 'x') { // 13은 엔터. / 입력 시 종료
			ArrayList<Integer> arr = new ArrayList<>();
			double result = 0;
			int resultFact = 1; // 팩토리얼 계산 위함
			String sResult = "";
			int input;

			double operand1 = this.readNumber(); // operand1

			if (this.operator == 'x' || this.operator == 13) // x, enter 입력 시 종료
				break;

			operator = this.operator; // readNumber에서 나오면서 입력한 연산자 저장

			if (operator == '!') { // factorial
				int op1 = (int) operand1;
				if (op1 == 0) { // 0! == 1
					char a = (char) (op1 + '1');
					System.out.println(a);
					this.readEnter();
					continue;
				}
				// 0!아닌 경우
				int i = 0;
				for (int j = op1; j > 0; j--) {
					resultFact *= j;
				}
				arr = addArr(arr, resultFact); // 한 숫자씩 뜯어서 배열에 담아주기
				Collections.reverse(arr); // 배열 뒤집기
				printArr(arr); // 하나씩 출력 / -> ASCII
				this.readEnter();
				continue;
			}

			double operand2 = this.readNumber(); // = 입력받아야 계산.

			if (operator == 'R') { // 루트 계산
				int i = 0;
				result = Math.sqrt(operand2); // 루트 메소드
				int intResult = (int) result; // 정수부분
				if (result == intResult) { // Integer -> ASCII
					arr = addArr(arr, intResult);

					if (result == 0) { // 계산 결과가 0인 경우
						char a = (char) (result + 48); // + 48 or + '0'
						System.out.println(a);
					} else { // 게산 결과가 0이 아닌 경우
						Collections.reverse(arr); // 배열 뒤집기
						printArr(arr);
					}
				} else { // Float -> ASCII / BigDecimal은 정확한 소수 계산 위함
					double dResult = result;
					String dOperand = Double.toString(dResult);
					String intOperand = Double.toString(intResult);
					BigDecimal b1 = new BigDecimal(dOperand);
					BigDecimal b2 = new BigDecimal(intOperand);
					double decimalResult = Double.parseDouble(b1.subtract(b2).toString()); // 소수 부분 / .xxx

					arr = addArr(arr, intResult);

					Collections.reverse(arr); // 배열 뒤집기
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
				} // 루트 결과 소수계산 끝
				this.readEnter();
				continue;
			} // 루트 계산 끝

			this.readEnter(); // read enter

			// 사칙연산
			if (operator == '+') {
				result = operand1 + operand2;
			} else if (operator == '-') {
				result = operand1 - operand2;
			} else if (operator == '*') {
				result = operand1 * operand2;
			} else if (operator == '/') {
				result = operand1 / operand2;
				if (Double.isInfinite(result)) { // 0으로 나눌 경우 처리
					System.out.println("0으로는 나눌 수 없습니다. 계산을 다시 진행하세요");
					continue;
				}
			} else if (operator == '^') // 거듭제곱
				result = Math.pow(operand1, operand2);
			else if (operator == '%') // 퍼센트. / 100 % 2 => 100의 2퍼센트 / 0.02
				result = operand1 * operand2 * 0.01;

			// 결과 출력 --> ASCII

			if (result == (int) result) { // 정수인 경우 integer -> ASCII
				int intResult = (int) result;
				int i = 0;

				if (intResult < 0)
					intResult *= -1;

				arr = addArr(arr, intResult);

				if (result < 0) // 음수 시 '-'를 끝에 배치 ( 뒤에서 배열 뒤집을 것이니 )
					arr.add('-' - 48);

				if (result == 0) { // 계산 결과가 0인 경우
					char a = (char) (result + 48);
					System.out.println(a);
				} else { // 게산 결과가 0이 아닌 경우
					Collections.reverse(arr); // 배열 뒤집기
					printArr(arr);
				}
			} else { // 소수인 경우 --> 소수점 고려하여 char로 출력 시의 경우 생각..
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
					sResult = b1.divide(b2, 4, BigDecimal.ROUND_HALF_UP).toString(); // 소수점 아래 4자리까지 표현, 5자리에서 반올림
				} else if (operator == '^') {
					sResult = b1.pow((int) operand2).toString();
				} else if (operator == '%') {
					sResult = b1.multiply(b2).multiply(bPer).toString();
				}

				// Float --> ASCII
				int i = 0;
				result = Double.parseDouble(sResult); // 이제 Float --> ASCII
				double dResult = result;
				if (dResult < 0) {
					dResult *= -1;
				}
				int intResult = (int) dResult;
				String dOperand = Double.toString(dResult);
				String intOperand = Double.toString(intResult);
				b1 = new BigDecimal(dOperand);
				b2 = new BigDecimal(intOperand);
				double decimalResult = Double.parseDouble(b1.subtract(b2).toString()); // 소수 부분 / .xxx

				while (intResult != 0 || i == 0) { // intResult == 0 && i != 0 일 때 멈춰야함
					arr.add(intResult % 10);
					intResult /= 10;
					i++;
				}

				if (result < 0) { // 음수 시 - 끝에 배치 ( 뒤에서 배열 뒤집을 것이니 )
					arr.add('-' - 48);
				}

				Collections.reverse(arr); // 배열 뒤집기
				arr.add('.' - 48);

				while ((decimalResult * 10 != (int) (decimalResult)) && decimalResult != 0) { // 소수부분이 0이 되거나, 더 이상 없을 때
																								// 종료
					int deci;
					String sDeciResult = Double.toString(decimalResult);
					BigDecimal b3 = new BigDecimal(sDeciResult);
					BigDecimal b4 = new BigDecimal("10");
					decimalResult = Double.parseDouble(b3.multiply(b4).toString());

					deci = (int) decimalResult; // 배열에 넣을 부분
					sDeciResult = Double.toString(decimalResult);
					String sDeci = Double.toString(deci);
					b3 = new BigDecimal(sDeciResult);
					b4 = new BigDecimal(sDeci);
					decimalResult = Double.parseDouble(b3.subtract(b4).toString()); // 배열에 넣고 빠질 부분 빼는 과정
					arr.add(deci);
				}
				printArr(arr);
			}
		}
	}
}
