package compareToTest;


import java.util.*;  
class Person implements Comparable{    //实现了comparable接口，必须重写compareTo方法！！！  
    private String name;  
    private int age;  
    private float score;  
      
    public Person(String name,int age,float score){  
       this.name=name;  
       this.age=age;  
       this.score=score;  
       }  
      
   public String toString(){  
    return "姓名："+this.name+", 年龄："+this.age+", 成绩："+this.score;  
    }  
     
    public int compareTo(Object obj){   //compareTo方法是实现  
     Person p = (Person)obj;  
                 if(p.score>this.score){  
                        return 1;  
                  }  
                  else if(p.score<this.score){  
                        return -1;  
                   }  
                   else{  
                        if(p.age>this.age){  
                         return 1;  
                        }  
                         else if(p.age<this.age){  
                         return -1;  
                         }  
                         else{  
                             return 0;  
                            }  
                    }  
     }                            //compareTo方法结束  
}                               //类结束  
 

