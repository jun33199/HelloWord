package sort;

public class bubblingSort {

	public static void main(String [] arg) throws Exception{
		int [] array = {1,4,5,7,3,2,9,8,0};
		new bubblingSort().sort(array);
		for(int i:array){
			System.out.println(i);
		}
	}

	private void sort(int[] array) {

		for(int i=0;i<array.length;i++){
			for(int j=array.length-1;j>i;j--){
				if(array[j]<array[j-1]){
					int tmp = array[j];
					array[j] = array[j-1];
					array[j-1]= tmp;
				}
			}
		}
	}
	
	
}
