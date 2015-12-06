package weblog;

public class test {
	
	public static void main(String[] args) {
		int a = Integer.MIN_VALUE;
		int b = 123;
		swap1(a, b);
		swap2(a, b);
	}
	
	public static void swap1(int a,int b) {
		a = a-b;
		System.out.println(a +"  -  "+b);
		b = a+b;
		a = b-a;
		System.out.println(a +"  -  "+b);
	}
	
	public static void swap2(int a,int b) {
		a = a^b;
		b = a^b;
		a = a^b;
		System.out.println(a +"  -  "+b);
	}

}
