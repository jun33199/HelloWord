package serializableTest;

import java.io.Serializable;

public class DataObject implements Serializable{
	static int i=0;
	String word="";
	public void setWord(String word){
	this.word=word;
	}
	public void setI(int i){
	DataObject.i =i;
	}
	}
