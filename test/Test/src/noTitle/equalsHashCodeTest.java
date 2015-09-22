package noTitle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class equalsHashCodeTest {
	
	public static void main(String [] a)throws Exception{
		class Person {
			private String name;
			private int age;
			public Person(String name, int age){
				this.name=name;
				this.age=age;
			}
			
			public String toString(){
				String str = "name:"+name+" age:"+age;
				return str;
			}
			
			@Override
			public int hashCode() {
				return 1;
			}

			@Override
			public boolean equals(Object obj) {
				Person person = (Person) obj;
				if(this.name.equals(person.name)&&this.age==person.age){
					return true;
				}
				return false;
			}
					
		}
		Person person1 = new Person("张1",1);
		Person person2 = new Person("张2",2);
		Person person3 = new Person("张3",3);
		Map<Person,String> personMap = new HashMap<Person,String>();
		for(int i=0;i<3;i++){
			personMap.put(person1, "person1");
			personMap.put(person2, "person2");
			personMap.put(person3, "person3");

		}
		personMap.put(new Person("张1",1), "person1");
		List<Person> list  = new ArrayList<Person>();
		Collections.addAll(list, person1,person2,person3);
		list.retainAll(Collections.singletonList(new Person("张2",2)));
		System.out.println(list);
		System.out.println(personMap);

	}
	
}
