package PolymorphismTest.extend;

public class Shap {

	int area = 100;
	
	public void print(){
		System.out.println("Shap area: "+area);
	}
	
	public static void printArea(){
		System.out.println("Shap area");
	}
	public int getArea(){
		return area;
	}
}
