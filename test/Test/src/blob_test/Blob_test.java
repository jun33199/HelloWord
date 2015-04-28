package blob_test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.sql.BLOB;

public class Blob_test {
public static int i=0;
	/**
	 * @param args
	 */
	public Blob_test(){
//		boolean saved=save();
//		System.out.println(saved);
//			doQuery();
			savePage();

	}
	private boolean savePage(){
	    Connection con=null ;       

        long start=System.currentTimeMillis();          //count runtime

        CallableStatement pstmt=null;  


        OutputStream outStream=null;
        String xmlstring ="<context-param><param-name>weblogic.httpd.inputCharset.</param-name><param-value>GBK</param-value></context-param><filter><filter-name>BusinessPermission</filter-name>";

     try{

        con = getConnect();

        con.setAutoCommit(false);

        String sql="insert into blob_test values(?,?,?)";

//insert database 

        pstmt = con.prepareCall(sql);

        for(int i=0;i<10;i++)

        {  

        String name = "Young_"+i;

        int id = 1;

        pstmt.setInt(1,id+i);

        pstmt.setString(2, name);

        Blob word = BLOB.empty_lob();

        pstmt.setBlob(3, word); 

       // pstmt.execute();
        pstmt.addBatch();

        }
        pstmt.executeBatch();
        System.out.print("instert ok\n");

        Statement stmt = con.createStatement();

        ResultSet rs =stmt.executeQuery("select id,word from blob_test for update");

        //get specially columns and rows for update

        while(rs.next()) {

                    //System.out.print(rs.getInt(1)+rs.getString(2)+"\n");//print select sql for debug

            BLOB blob = (BLOB) rs.getBlob("word");

            outStream = blob.getBinaryOutputStream();


            byte[] b = new byte[blob.getBufferSize()];

            int len = 0;

            InputStream fin=new ByteArrayInputStream(xmlstring.getBytes());
            while ((len = fin.read(b)) != -1) {

                outStream.write(b, 0, len);

            }

        

            fin.close();

            outStream.flush();

            outStream.close(); 

        }

            System.out.print("\nupdate ok\n");

        

            con.commit();  

     }

    

      catch (Exception e) {

        e.printStackTrace();

    }

     

     try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    

     long end=System.currentTimeMillis();

     System.out.println(end-start);
     return true;
	}
	private void getData(){
		
	}
	private boolean save(){
	    Connection con=null ;       

        long start=System.currentTimeMillis();          //count runtime

        CallableStatement pstmt=null;  

        InputStream fin=null;

        OutputStream outStream=null;

        String path = "E:\\web.xml";//"E:\\test.xml";

        File file = new File(path);

     try{

        con = getConnect();

        con.setAutoCommit(false);

        String sql="insert into blob_test values(?,?,?)";

//insert database 

        pstmt = con.prepareCall(sql);

        for(int i=0;i<10;i++)

        {  

        String name = "Young_"+i;

        int id = 1;

        pstmt.setInt(1,id+i);

        pstmt.setString(2, name);

        Blob word = BLOB.empty_lob();

        pstmt.setBlob(3, word); 

        pstmt.execute();

        }

        System.out.print("instert ok\n");

        Statement stmt = con.createStatement();

        ResultSet rs =stmt.executeQuery("select id,word from blob_test for update");

        //get specially columns and rows for update

        while(rs.next()) {

                    //System.out.print(rs.getInt(1)+rs.getString(2)+"\n");//print select sql for debug

            BLOB blob = (BLOB) rs.getBlob("word");

            outStream = blob.getBinaryOutputStream();

            fin = new FileInputStream(file);  //put file into stream

            byte[] b = new byte[blob.getBufferSize()];

            int len = 0;

            while ((len = fin.read(b)) != -1) {

                outStream.write(b, 0, len);

            }

        

            fin.close();

            outStream.flush();

            outStream.close(); 

        }

            System.out.print("\nupdate ok\n");

        

            con.commit();  

     }

    

      catch (Exception e) {

        e.printStackTrace();

    }

     

     try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    

     long end=System.currentTimeMillis();

     System.out.println(end-start);
     return true;

	}
	private boolean doSave(){
		Connection connect;
        InputStream fin=null;

        OutputStream outStream=null;

        String path ="E:\\BJDS-2013年企业所得税系统升级改造项目-企业所得税清算申报-需求分析报告.doc";//"E:\\test.txt";

        File file = new File(path);

		try {
			

			connect = getConnect();

	      Statement stmt = connect.createStatement(); 
		  ResultSet rs = stmt.executeQuery("select t.*,t.rowid from blob_test t where t.id=1 for update"); 
		 
		      if (rs.next())
		      {
		    	  BLOB blob = (BLOB) rs.getBlob("word");

	                outStream = blob.getBinaryOutputStream();

	                fin = new FileInputStream(file);  //put file into stream

	                byte[] b = new byte[blob.getBufferSize()];

	                int len = 0;

	                while ((len = fin.read(b)) != -1) {

	                    outStream.write(b, 0, len);
	                }
	                fin.close();

	                outStream.flush();

	                outStream.close(); 
	                return true;

		      }
		      else
		      {
		    	  Blob xmlString = BLOB.empty_lob();
			      PreparedStatement statement=connect.prepareStatement("INSERT INTO blob_test VALUES(?,?,?)"); 
			      statement.setInt(1,i++); //
			      statement.setString(2,"xml"); 
			      statement.setBlob(3, xmlString); 
			      statement.executeUpdate(); 
			      return true;
		    }
		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	private boolean doQuery(){
		 Connection con=null ;   
		try {
			con=getConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		              

           long start=System.currentTimeMillis();               //count runtime  

           String path = "E:\\web1.xml";

           File file = new File(path);

        try{



                con.setAutoCommit(false);

                Statement stmt = con.createStatement();

                ResultSet rs =stmt.executeQuery("select id,word from blob_test where id=10");

                //get blob form your table

                if(rs.next()) {

                         BLOB blob = (BLOB) rs.getBlob("word");

//get word column

    FileOutputStream output = new FileOutputStream(file);

//define a file output stream

    InputStream input = blob.getBinaryStream();//put blob into input

    byte[] buffer = new byte[blob.getBufferSize()];

//if use 1024 it will lose some bytes

    int len = 0;

    while ((len = input.read(buffer)) != -1) {

    //get all input stream into output file stream

    output.write(buffer, 0, len);
                             }
    input.close();

    output.flush();

    output.close();
    System.out.print("\ndownload ok\n");

                 

                }

        }

      

         catch (Exception e) {

                e.printStackTrace();

       }

        

        try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      

        long end=System.currentTimeMillis();

        System.out.println(end-start);

		return true;
	}
	public static  Connection getConnect()throws Exception{
		 try { 
//		      Class.forName("oracle.jdbc.driver.OracleDriver");     //加载MYSQL JDBC驱动程序    
//		      //Class.forName("org.gjt.mm.mysql.Driver"); 
//		     System.out.println("Success loading Mysql Driver!"); 	
//		      Connection connect = DriverManager.getConnection( 
//		          "jdbc:mysql://localhost:1521/mysql","root","admin"); 
		        Connection con = null;

		        String driver = "oracle.jdbc.driver.OracleDriver";//database driver

		        String url = "jdbc:oracle:thin:@localhost:1521:zhangj";//database URL

		        String user = "system";           //database name        

		        String password = "zhangj123";       //database Password

		        Class.forName(driver);

		        con = DriverManager.getConnection(url, user, password);
		        System.out.print("CONNECTED。。。。。。。。。。!"); 
		        return con;     

		    } 
		    catch (Exception e1) { 
		      System.out.print("Error loading Oracle Driver!"); 
		      e1.printStackTrace(); 
		    } 
		    return null;
	}
	public static void main(String[] args)throws Exception {
		new Blob_test();

	}

}
