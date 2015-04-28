package yhs;

public class YhsBean {
  private int old ;
  private String name="xxx";
  private int age;
  //Access sample property
  public int getAge(){
    return age;
  }
  public void setAge(int newAge){
      age=newAge;
  }
  public String getName(){
    return name;
  }
  public int getOld() {
    old=age*2;
    return old;
  }
  //Access sample property
  public void setName(String newName){
    if(newName!=null){
      name=newName;
    }else{
      name="";
    }
  }
}