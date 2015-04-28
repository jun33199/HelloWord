package xmlTezt;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLTest {
	public static void main(String [] arg) throws Exception{
	new XMLTest();
	}
	public XMLTest(){
		System.out.println("start .....");
		readXmlNoName();
		System.out.println("end .....");
	}
	public void readXml(){
		long lasting =System.currentTimeMillis();
		File f=new File("E:\\报表文件.xml");
		SAXReader reader=new SAXReader();
		Document doc=null;
		try {
			doc = reader.read(f);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root= doc.getRootElement();
		Element foo;
		for(Iterator i=root.elementIterator("zmjz");i.hasNext();){
			foo=(Element)i.next();
			System.out.println("hc1_1"+foo.elementText("hc1_1"));
			System.out.println("hc1_2"+foo.elementText("hc1_2"));
		}
	}
	public void readXmlNoName(){
		long lasting =System.currentTimeMillis();
		File f=new File("E:\\报表文件.xml");
		SAXReader reader=new SAXReader();
		Document doc=null;
		String xmlstring ="<?xml version=\"1.0\" encoding=\"GB2312\"?>"
		+"<data><zmjz><hc1_1>1</hc1_1><hc1_2>2</hc1_2><hc1_3>3</hc1_3><hc1_4>4</hc1_4><hc1_5>5</hc1_5><hc1_6>6</hc1_6></zmjz>"
		+"<jsjc><hc1_1>21</hc1_1><hc1_2>22</hc1_2><hc1_3>23</hc1_3><hc1_4>24</hc1_4><hc1_5>25</hc1_5><hc1_6>26</hc1_6></jsjc>"
		+"<jyjg><hc1_1>31</hc1_1><hc1_2>32</hc1_2><hc1_3>33</hc1_3><hc1_4>34</hc1_4><hc1_5>35</hc1_5><hc1_6>36</hc1_6></jyjg>"
		+"<zcczxy><hc1_1>41</hc1_1><hc1_2>42</hc1_2><hc1_3>43</hc1_3><hc1_4>44</hc1_4><hc1_5>45</hc1_5><hc1_6>46</hc1_6></zcczxy></data>";
		 InputStream in=new ByteArrayInputStream(xmlstring.getBytes());
		 try {
			//doc = reader.read(f);
			doc=reader.read(in);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root= doc.getRootElement();
		Element foo;
		for(Iterator i=root.elementIterator();i.hasNext();){
			foo=(Element)i.next();
			//System.out.println(foo.);
			for(Iterator j=foo.elementIterator();j.hasNext();){
				Element e=(Element)j.next();
				System.out.println(e.getName()+"_"+e.getText());
			}
		}
	}
	
}
