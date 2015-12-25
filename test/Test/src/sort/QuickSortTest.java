package sort;

public class QuickSortTest {

	public static void main(String [] arg) throws Exception{
		
		int [] array ={3,1,2,6,5,7,9,8,0,4};
		new QuickSortTest().sort(array, 0, array.length-1);
		for(int i : array){
			System.out.println(i);
		}
	}
	
	public void sort(int [] array, int low, int high){
		if(low<high){
			int middle = this.onceSort(array, low, high);
			this.sort(array, low, middle-1);
			this.sort(array, middle+1, high);
		}
	}

//	private int onceSort(int[] array, int low, int high) {
//		while(low<high){
//			int tmp = array[low];
//			while(low<high&&array[high]>tmp){
//				--high;
//			}
//			this.swap(array,low,high);
//			while(low<high&&array[low]<tmp){
//				++low;
//			}
//			this.swap(array, low, high);
//		}
//		return low;
//	}

	private int onceSort(int [] array, int low, int high){
		int tmp= array[low];
		while(low<high){
			while(low<high&&array[high]>tmp){
				--high;
			}
			array[low]=array[high];
			while(low<high&&array[low]<tmp){
				++low;
			}
			array[high]=array[low];
		}
		array[low]=tmp;
		return low;
	}
	private void swap(int[] array, int low, int high) {

		int tmp = array[low];
		array[low]=array[high];
		array[high]= tmp;
	}
}
