package sort;

/**
 * 快速排序分为两个过程：第一步：一次排序，先取数组的首位元素作为中轴进行比较，小于中轴的放到中轴左边，大于中轴的放到中轴右边；并返回最终中轴位置
 * 第二步：以中轴为中心将数组分为两部分，分别对两部分数组进行递归调用 排序方法 平均时间 最坏情况 最好情况 辅助存储 稳定性 复杂性 快速排序
 * O(nlogn) O(n平方) O(nlogn) O(nlogn) 不稳定 较复杂
 * 
 * @author zhangjun
 *
 */
public class QuickSorting {
	public static void main(String[] arg) throws Exception {
		int[] array = { 49, 38, 65, 97, 76, 13, 27 };
		new QuickSorting().sort(array, 0, array.length - 1);
		for (int i : array) {
			System.out.println(i);
		}
	}

	private void sort(int[] array, int low, int high) {
		int middle;
		if (low < high) {
			middle = portition(array, low, high);
			sort(array, low, middle - 1);
			sort(array, middle + 1, high);
		}
	}

	//交换方式的一次排序
	private int portition(int[] array, int low, int high) {
		int temp = array[low];
		while (low < high) {
			while (low < high && array[high] >= temp) {
				--high;
			}
			swap(array, low, high);
			while (low < high && array[low] <= temp) {
				++low;
			}
			swap(array, low, high);
		}
		return low;

	}

	//直接赋值的一次排序,一次排序结束之后需要将给中轴赋值
	//	private int portition(int [] array,int low,int high){
	//		int temp=array[low];
	//		while(low<high){
	//			while(low<high&&array[high]>=temp){
	//				--high;
	//			}
	//			array[low]=array[high];
	//			while(low<high&&array[low]<=temp){
	//				++low;
	//			}
	//			array[high]=array[low];
	//		}
	//		array[low]=temp;
	//		return low;		
	//	}
	private void swap(int[] array, int low, int high) {
		int temp = array[low];
		array[low] = array[high];
		array[high] = temp;

	}
}
