package algorithm;

import java.util.ArrayList;
import java.util.List;

public class FindMedian {

	List<Integer> list = new ArrayList<Integer>();

	public static void main(String[] arg) throws Exception {

		FindMedian findMedian = new FindMedian();
		findMedian.addNum(6);
		findMedian.addNum(2);
		findMedian.addNum(10);
		findMedian.addNum(6);

		System.out.print(findMedian.findMedian());
	}

	// Adds a number into the data structure.
	public void addNum(int num) {
		if (list.isEmpty()) {
			list.add(num);
			return;
		}

		int start = 0;
		int end = list.size() - 1;
		int target = 0;
		while (start < end) {
			int middle = (start + end) / 2;
			int midVal = list.get(middle);
			if (midVal < num) {
				start = middle + 1;
			} else if (midVal > num) {
				end = middle - 1;
			} else {
				target = middle;
				break;
			}
		}
		target = start != end ? target : list.get(start) < num ? start + 1 : start;
		list.add(target, num);
	}

	public double findMedian() {
		if (list.isEmpty()) {
			return 0;
		}

		int middle = list.size() / 2;

		if (list.size() % 2 == 0) {
			return (list.get(middle) + list.get(middle - 1)) * 1.0 / 2;
		} else {
			return list.get(middle);
		}
	}
}
