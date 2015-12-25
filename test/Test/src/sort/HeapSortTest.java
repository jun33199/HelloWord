package sort;

public class HeapSortTest {

	public static void main(String[] arg) throws Exception {

		int[] array = { 3, 5, 2, 1, 0, 8, 9, 7, 6 };

		HeapSortTest test = new HeapSortTest();
		test.buildMaxHeap(array);
		test.sort(array);

		for (int i : array) {
			System.out.println(i);
		}
	}

	private void sort(int[] array) {

	}

	private void buildMaxHeap(int[] array) {

		int startIndex = (array.length - 1 - 1) >> 1;
		for (int i = startIndex; i >= 0; i--) {
			maxHeap(array, array.length, startIndex);
		}
	}

	private void maxHeap(int[] array, int size, int index) {
		int leftIndex = (index << 1) + 1;
		int rightIndex = (index + 1) << 1;
		int largest = index;
		if (leftIndex < size && array[leftIndex] > array[largest]) {
			largest = leftIndex;
		}
		if (rightIndex < size && array[rightIndex] > array[largest]) {
			largest = rightIndex;
		}

		if (largest != index) {
			int tmp = array[index];
			array[index] = array[largest];
			array[largest] = tmp;
			this.maxHeap(array, array.length, largest);
		}
	}

}
