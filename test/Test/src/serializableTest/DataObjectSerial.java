package serializableTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DataObjectSerial {

    public static void main(String[] args) throws Exception {
    	DataObject object = new DataObject();
    	 object.setWord("123");
    	 object.setI(2);
        File file = new File("data.out");

//        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
//        DataObject obj = new DataObject();
//        obj.setI(2);
//        obj.setWord("hello");
//
//        oout.writeObject(obj);
//        oout.close();

        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
        DataObject newPerson = (DataObject) oin.readObject(); // 没有强制转换到Person类型
        oin.close();
        System.out.println(newPerson.i);
        System.out.println(newPerson.word);
    }
}
