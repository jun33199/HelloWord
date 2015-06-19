package cn.edu.bupt.replace;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.bupt.conf.Configurations;

public class GenerateSetAction {
    Configurations cfg;
    String [] apiinfoSet;
    String apiPercent;
    public GenerateSetAction(){
        cfg = Configurations.getInstance ();
        apiinfoSet=cfg.getApiinfoSet();
        apiPercent=cfg.getApiPercent();
        
        for(int i=0;i<apiinfoSet.length;i++){
            selectApiSet(apiinfoSet[i]);
        }
    }
    
    public void selectApiSet(String apiinfo){
        
       
        try {                   
            String sql="select count(id) totalNum from "+apiinfo;
            PreparedStatement stmt = CreateConnect.getInstance().getConnect().prepareStatement(sql);                              
            ResultSet rs = stmt.executeQuery(sql);  
            String totalNum="0";
            while (rs.next()) { 
                totalNum=rs.getString("totalNum");
              } 
            System.out.println(apiinfo+"totalNum :"+totalNum);
            int selectNum=(int) Math.round(Integer.parseInt(totalNum)*Integer.parseInt(apiPercent)*0.01);
            sql="insert into apiinfo(apiname,remar,mc,bc,infogain,xm,xb,xmn,xbn) (select b.apiname,b.remar,b.mc,b.bc,b.infogain,b.xm,b.xb,b.xmn,b.xbn from "+
                 apiinfo+" b,(select a.id,a.infogain from "+
                 apiinfo+" a ORDER BY infogain DESC LIMIT 0,"+
                 selectNum+") c where b.id=c.id  and b.mc>b.bc)";
            System.out.println(sql);
            stmt = CreateConnect.getInstance().getConnect().prepareStatement(sql);            
            stmt.executeUpdate();
            
            
            sql="select count(b.id) mcNum from "+
                 apiinfo+" b,(select a.id,a.infogain from "+
                 apiinfo+" a ORDER BY infogain DESC LIMIT 0,"+
                 selectNum+") c where b.id=c.id  and b.mc>b.bc";
            stmt = CreateConnect.getInstance().getConnect().prepareStatement(sql); 
            rs = stmt.executeQuery(sql);  
            String mcNum="0";
            while (rs.next()) { 
                totalNum=rs.getString("mcNum");
              } 
            
            
            
            sql="insert into apiinfo(apiname,remar,mc,bc,infogain,xm,xb,xmn,xbn) (select b.apiname,b.remar,b.mc,b.bc,b.infogain,b.xm,b.xb,b.xmn,b.xbn from "+
                    apiinfo+" b,(select a.id,a.infogain from "+
                    apiinfo+" a ORDER BY infogain DESC LIMIT 0,"+
                    selectNum+") c where b.id=c.id  and b.mc<b.bc limit 0,"+
                    totalNum+")";
            System.out.println(sql);
            stmt = CreateConnect.getInstance().getConnect().prepareStatement(sql);            
            stmt.executeUpdate();
        } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
             } 
  
    }
}
