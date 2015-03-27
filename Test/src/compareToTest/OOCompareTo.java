package compareToTest;

import java.util.Arrays;

public class OOCompareTo{  
	   public static void main(String []args){  
	    Person p[] = new Person[5];  
	    p[0] = new Person("张三 ",23,96);  
	    p[1] = new Person("张三2",24,96);  
	    p[2] = new Person("张三3",21,94);  
	    p[3] = new Person("张三4",22,98);  
	    p[4] = new Person("张三5",20,89);  

	    Arrays.sort(p);                 //对数组p内的元素进行了遍历操作（排序） 默认的方法是compareTo  
	    for(int x=0;x<p.length;x++){  
	     System.out.println(p[x]);           //输出数组元素时默认的是toString（）方法  
	     }     
	    }
	   
	   } 
