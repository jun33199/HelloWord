package sort;
import java.util.HashMap;
import java.util.LinkedList;
/**
 * 基数排序又叫位排序，待排序的序列有一定规律，关键字个数必须一致。基数排序有“最高位优先(MSD)”和“最低位优先(LSD)”两种方式。
 * 下面对10个三位数的排序用的时最高位优先规则（若用最低位优先，则排序后的数列不符合自然数的大小规则）
 * 对于n个记录（假设每个记录含d个关键字，每个关键字的取值范围位r个值）进行链式基数排序的时间复杂度位O(d(n+r)), 其中每一趟分配的时间复杂度为O(n),
 * 每一趟收集的时间复杂度为O(r),整个排序需进行d趟分配和收集。用c语言实现链式基数排序时，所需的辅助空间为2r个队列指针，当然，由于需要用链表作存储结构，
 * 则相对于其他以顺序结构存储记录的排序方法而言，还增加了n个指针域的空间。
 * 
 * 排序方法    平均时间     最坏情况     最好情况     辅助存储     稳定性     复杂性
 * 基数排序    O(d(n+r))  O(d(n+r))  O(d(n+r))   O(n+r)      稳定      较复杂
 * @author zhangjun
 *
 */
public class RadixSorting {
	public static void main(String [] arg) throws Exception{
		LinkedList<String> llt=new LinkedList<String>();
		initListData(llt);
		HashMap<Integer,LinkedList<String>> hMap=new HashMap<Integer,LinkedList<String>>();
		for(int i=0;i<=9;i++){
			hMap.put(i, new LinkedList<String>());
		}
		for(int i=2;i>=0;i--){
			for(String data:llt){
				hMap.get(Integer.parseInt(data.charAt(i)+"")).add(data);
			}
			llt.clear();
			for(int j=0;j<hMap.size();j++){
				llt.addAll(hMap.get(j));
				hMap.get(j).clear();
			}
		}
		System.out.println(llt);
	}
	private static void initListData(LinkedList<String> llt){
		llt.add("278");
		llt.add("109");
		llt.add("063");
		llt.add("930");
		llt.add("589");
		llt.add("184");
		llt.add("505");
		llt.add("269");
		llt.add("008");
		llt.add("083");
	}
}

