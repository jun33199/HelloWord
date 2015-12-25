package PolymorphismTest.extend;

public class Main {

	public static void main(String [] arg) throws Exception{
		Shap shap = new Circle();
		shap.print();
		System.out.println(shap.area);
		System.out.println(shap.getArea());
		
		shap.printArea();
	}
}
