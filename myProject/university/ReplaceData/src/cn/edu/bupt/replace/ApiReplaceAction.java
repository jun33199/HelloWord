package cn.edu.bupt.replace;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cn.edu.bupt.conf.Configurations;
/**
 * 
 *  如果想重新计算则将apiReplace();注释符放开，如果只想输出贝叶斯相关内容，则将apiReplace();注释
 *
 * */

public class ApiReplaceAction {
    Configurations cfg;
    String apiinfo;
    String fileapi;
    public ApiReplaceAction(){
        cfg = Configurations.getInstance ();
        apiinfo=cfg.getApiinfo();
        fileapi=cfg.getFileapi();
        boolean apiReplaceIsRun=cfg.isApireplaceIsRun();
        boolean bayesIsRun=cfg.isApiBayesIsRun();
        if(true==apiReplaceIsRun){
         apiReplace();
        }
        if(true==bayesIsRun){
        bayesOutPut_Batch();
        }
    }
    
    /**
     * 批量输出贝叶斯数据
     */
    public void bayesOutPut_Batch(){
        cfg = Configurations.getInstance ();
        String [] fileidArray=cfg.getFileid();
        for(int i=0;i<fileidArray.length ;i++){

         int fileid=Integer.parseInt(fileidArray[i]);
          bayesOutput(fileid);
        }
    }
    /**
     * 
     *  将数据库中所有的apiinfo中的记录更新一遍
     *
     * */
    public void apiReplace(){
        
          try { 
//给apiifo中的remar赋值
            Statement stmt = CreateConnect.getInstance().getConnect().createStatement(); 
           // Statement stmt1 = connect.createStatement();
            String sql="";
            sql="select apiname from "+apiinfo;
            ResultSet rs = stmt.executeQuery(sql); 
            while (rs.next()) { 
                updateApiRemar(rs.getString("apiname"));
               // System.out.println(rs.getString("id")+rs.getString("remar")); 
              } 
//给fileifo中的remark赋值          
            sql="select id,remar from "+apiinfo;
             rs = stmt.executeQuery(sql); 
            while (rs.next()) { 
            	updateFileRemark(rs.getInt("id"),rs.getString("remar"));
               // System.out.println(rs.getString("id")+rs.getString("remar")); 
              } 
//给apiifo中的其他信息赋值      
            rs = stmt.executeQuery(sql); 
            while (rs.next()) { 
            	updateApi(rs.getInt("id"),rs.getString("remar"));
               // System.out.println(rs.getString("id")+rs.getString("remar")); 
              } 
            
          } 
          catch (Exception e2) { 
            System.out.print("get data error!"); 
            e2.printStackTrace(); 
          } 
          
    }
    /**
     * 
     *  给apiifo中的其他信息赋值   
     *
     * */ 
    public void updateApi(int apiid,String remar){
            int mc=countMc(remar);
            int bc=countBc(remar);
            double[] computeData=compute(mc,bc);
           
            try {                   
                String sql="update "+apiinfo+"  set  mc=?,bc=?,s1=?,s2=?,s3=?,s4=?,infogain=?,xm=?,xb=?,xmn=?,xbn=? where id=? and remar=?";
                PreparedStatement stmt = CreateConnect.getInstance().getConnect().prepareStatement(sql);                   
                
                stmt.setInt(1, mc);
                stmt.setInt(2, bc);
                stmt.setDouble(3, computeData[0]);
                stmt.setDouble(4, computeData[1]);
                stmt.setDouble(5, computeData[2]);
                stmt.setDouble(6, computeData[3]);
                stmt.setDouble(7, computeData[4]);
                stmt.setDouble(8, computeData[5]);
                stmt.setDouble(9, computeData[6]);
                stmt.setDouble(10, computeData[7]);
                stmt.setDouble(11, computeData[8]);
                stmt.setInt(12,apiid); 
                stmt.setString(13,remar);
                stmt.executeUpdate();                   
            } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                 } 
      
    }
    /**
     * 
     *  给apiifo中的remar赋值
     *
     * */ 
    public void updateApiRemar(String apiname){
        String remar=getRemar(apiname);     
        try {                   
            String sql="update "+apiinfo+"  set  remar=? where apiname=?";
            PreparedStatement stmt = CreateConnect.getInstance().getConnect().prepareStatement(sql);                   
            stmt.setString(1,remar);
            stmt.setString(2,apiname); 
            stmt.executeUpdate();                   
        } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
             }         
    }
    /**
     * 
     * 给fileifo中的remark赋值       
     *
     * */ 
     
    public void updateFileRemark(int apiid,String remark){
        try {                   
            String sql="update "+fileapi+"  set  remark=? where apiid=?";
            PreparedStatement stmt = CreateConnect.getInstance().getConnect().prepareStatement(sql);                   
            stmt.setString(1,remark);
            stmt.setInt(2,apiid); 
            stmt.executeUpdate();                   
        } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
             }         
    }
    /**
     * 
     *  获取remar的值，如果数据库中已经存在，则直接返回，反之新建remar
     *
     * */ 
    public String getRemar(String apiname){
        String [] apinameArray=apiname.split(",");
        String remarRep="";
        String sql="";
        for(int i=0;i<apinameArray.length;i++){
            sql="select remar from apiinfo1 where apiname='"+apinameArray[i]+"';";
            try {
                PreparedStatement stmt = CreateConnect.getInstance().getConnect().prepareStatement(sql);     
                //stmt.setString(1,apiname);
                ResultSet rs = stmt.executeQuery(sql); 
                String remar="";
                    if(rs.next()){
                        if("".equals(rs.getString("remar"))||rs.getString("remar")==null){
                            remar=newRemar(apiname);
                        }else{
                            remar=rs.getString("remar");
                        }
                        remarRep+=","+remar;
                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }  
        if(remarRep.length()>0){
            remarRep=remarRep.substring(1); 
        }      
        return remarRep;
        
    }

/**
 * 
 *  新建remar，remar为数据库中最大的remar值加一
 *
 * */ 
    public String  newRemar(String apiname){
       
        int max=0;
        String sql="";
        String apinameRep="";
        sql="select remar  from apiinfo1";      
        
        try { 

            PreparedStatement stmt = CreateConnect.getInstance().getConnect().prepareStatement(sql);     
           // stmt.setString(1,apiname);
            ResultSet rs = stmt.executeQuery(sql); 

            while (rs.next()) {
              if("".equals(rs.getString("remar"))||rs.getString("remar")==null){
                  continue;
              }
              int remar= Integer.parseInt(rs.getString("remar"));
            
              if (max<remar){
                 max=remar;
              }
                
              }            
          } 
          catch (Exception e2) { 
            System.out.print("get data error!"); 
            e2.printStackTrace(); 
          } 
        return ++max+"";
        
      
        
    }
    /**
     * 
     *  统计mc
     *
     * */ 
    public int countMc(String remar){
          
        int mc=0;
        String sql="";
 
        String apinameRep="";
        
        sql="select count(id)  from "+fileapi+" where fileid in(2,13,24,25,32,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,83,94,105,43) and remark='"+remar+"';";      
        
        try { 

            PreparedStatement stmt = CreateConnect.getInstance().getConnect().prepareStatement(sql);     
           // stmt.setString(1,apiname);
            ResultSet rs = stmt.executeQuery(sql); 

            if (rs.next()) {
                mc=rs.getInt(1);
                
              }            
          } 
          catch (Exception e2) { 
            System.out.print("get data error!"); 
            e2.printStackTrace(); 
          } 
        return mc;
    }
    /**
     * 
     *  统计bc
     *
     * */ 
    public int countBc(String remar){
        
        int bc=0;
        String sql="";
        String apinameRep="";
        sql="select count(id)  from "+fileapi+" where fileid in(3,4,5,6,7,8,9,10,11,12,14,15,16,17,18,19,20,21,22,23,26,27,28,29,30,31,82,84,85,86,87,88,89,90,91,92,93,95,96,97,98,99,100,101,102,103,104,106,107,108,109,110,111,112,113,114,115) and remark='"+remar+"';";      
        
        try { 

            PreparedStatement stmt = CreateConnect.getInstance().getConnect().prepareStatement(sql);     
           // stmt.setString(1,apiname);
            ResultSet rs = stmt.executeQuery(sql); 

            if (rs.next()) {
                bc=rs.getInt(1);
                
              }            
          } 
          catch (Exception e2) { 
            System.out.print("get data error!"); 
            e2.printStackTrace(); 
          } 
        return bc;
    }
    /**
     * 
     *  输出结果
     *
     * */ 
    public void bayesOutput(int fileid){
       
        double  m=1;
        double  b=1;
        String sql="";
        String apinameRep="";
        sql="select * from "+apiinfo+" ORDER BY infogain DESC LIMIT 0,10000;";      
        
        try { 

            PreparedStatement stmt = CreateConnect.getInstance().getConnect().prepareStatement(sql);     
           // stmt.setString(1,apiname);
            ResultSet rs = stmt.executeQuery(sql); 
                while (rs.next()) {
                    if(isExisted(rs.getString("remar"),fileid)){
                        m=m*rs.getDouble("xm");
                        b=b*rs.getDouble("xb");
                    }else{
                        m=m*rs.getDouble("xmn");
                        b=b*rs.getDouble("xbn");
                    }
                
                }   
                System.out.println(fileid+"， "+apiinfo+"恶意："+m*0.452+"，白:"+b*0.548);

          } 
          catch (Exception e2) { 
            System.out.print("get data error!"); 
            e2.printStackTrace(); 
          } 
    }
    /**
     * 
     *  判断remar是否已经存在
     *
     * */ 
    public boolean isExisted(String remar,int fileid){
        String sql="";
       
        sql="select * from "+fileapi+" where remark='"+remar+"' and fileid="+fileid+";";      
        
        try { 

            PreparedStatement stmt = CreateConnect.getInstance().getConnect().prepareStatement(sql);     
           // stmt.setString(1,apiname);
            ResultSet rs = stmt.executeQuery(sql); 

            if (rs.next()) {
                return true;
                
              }            
          } 
          catch (Exception e2) { 
            System.out.print("get data error!"); 
            e2.printStackTrace(); 
          } 
       // System.out.println("fileid为："+fileid);
        return false;
    }
    /**
     * 
     *  计算相关数据
     *
     * */ 
   
  public  double[] compute(int mc,int bc){
        double  s1=0;
        double  s2=0;
        double  s3=0;
        double  s4=0;
        double  infogain=0;
        double  xm=0;
        double  xb=0;
        double  xmn=0;
        double  xbn=0;
        
        if(mc!=0){
            s1=((mc*1.0)/47)*Math.log((10816*mc*1.0)/(2209*(mc+bc)));
        }
        if(mc<47 || mc>47){
            s2=(((47-mc)*1.0)/47)*Math.log((10816*(47-mc)*1.0)/(2209*(104-mc-bc)*1.0));
        }
        if(bc<0 || bc>0){
            s3=((bc*1.0)/57)*Math.log((10816*bc*1.0)/(3249*(mc+bc)*1.0)) ;
        }
        if(bc<57 || bc>57){
            s4=(((57-bc)*1.0)/57)*Math.log((10816*(57-bc)*1.0)/(3249*(104-mc-bc)*1.0));
        }
        infogain=s1+s2+s3+s4;
        xm=((mc+1)*1.0)/49;
        xb=((bc+1)*1.0)/59;
        xmn=((48-mc)*1.0)/49;
        xbn=((58-bc)*1.0)/59;
        double[] result={s1,s2,s3,s4,infogain,xm,xb,xmn,xbn};
        System.out.println("s1:"+s1+"\ns2:"+s2+"\ns3:"+s3+"\ns4:"+s4+"\ninfogain:"+infogain+"\nxm:"+xm+"\nxb:"+xb+"\nxmn:"+xmn+"\nxbn:"+xbn);
        return result;
    } 


}

