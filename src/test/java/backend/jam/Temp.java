package backend.jam;

public class Temp {
	public static void main(String[] args) {

	}

	public int calc(int a, int b, char action){
		if(action == '+'){
			return this.plus(a, b);
		}else if(action == '-'){
			return this.minus(a, b);
		} else if (action == '*') {
			return this.multiply(a, b);
		} else if (action == '/') {
			if(b ==0){
				System.out.println("Cannot divide by zero");
				return 0;
			}
			return a /b;
		}else {
			System.out.println("Wrong action" + action);
			return action;
		}
	}


	private int plus(int a, int b){
		return a + b;
	}
	private int minus(int a, int b){
		return a - b;
	}

	private int multiply(int a, int b){
		return a * b;
	}

	private int divide(int number, int divider){
		if(divider == 0){
			return 0;
		}else{
			return number / divider;
		}
	}
}
