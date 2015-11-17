/**
 * A small stack class with basic stack methods for a pre order walk of the
 * Approximate MST tree
 * 
 * @author Nikhil
 * 
 */

public class Stack {

	int limit;
	int[] stack;
	int top = -1;

	public Stack(int limit) {
		this.limit = limit;
		stack = new int[limit];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public void push(int i) {
		top++;
		stack[top] = i;
	}

	public int pop() {
		int popped = 0;
		if (!isEmpty()) {
			popped = stack[top--];
			
		}
		return popped;
	}
	
	public int peek(){
		return !isEmpty()? stack[top]: -1;
	}

	public static void main(String[] args) {
		Stack stack = new Stack(5);
		for(int i=0; i<5;i++){
			stack.push(i);
		}
		for(int i=0; i<5;i++){
			System.out.println(stack.pop());
		}
	}

}
