package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RadixSortTest {

	public static void main(String [] arg) throws Exception{
		String [] array = {"123","456","998","009","002","768","567","324"};
		new RadixSortTest().sort(array);
	}

	private void sort(String[] array) {
		
		List<String> list = new LinkedList<String>();
		list.addAll(Arrays.asList(array));
		Map<Integer,List<String>> listMap = new HashMap<Integer,List<String>>();
		for(int i=0;i<=9;i++){
			listMap.put(i, new LinkedList<String>());
		}
		
		for(int i=2;i>=0;i--){
			for(String value : list){
				listMap.get(Integer.parseInt(value.charAt(i)+"")).add(value);
			}
			
			list.clear();
			
			for(List<String> tmpList : listMap.values()){
				list.addAll(tmpList);
				tmpList.clear();
			}
		}
		
		System.out.println(list);
		
	}
}
