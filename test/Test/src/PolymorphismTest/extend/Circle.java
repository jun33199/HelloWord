package PolymorphismTest.extend;

public class Circle extends Shap {

	public int area = 200;
	
	public void print(){
		System.out.println("Circle area : " +area);
	}
	@Override
	public int getArea(){
		return area;
	}
	public static void printArea(){
		System.out.println("Circle area");
	}
}
