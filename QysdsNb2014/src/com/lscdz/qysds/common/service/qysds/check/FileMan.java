package com.lscdz.qysds.common.service.qysds.check;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * <p>Title: 自定义报表-文件操作</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author Ha Zhengze
 * @version 1.0
 */
public class FileMan {

    public FileMan() {
    }

    public static void generateFormulasSQLScript(String url) {
        String path = System.getProperty("user.dir") + url;
        System.out.println("操作文件路径=" + path);
        try {
            FileInputStream fis = new FileInputStream(path);
            File file = new File(path + ".sql");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            System.out.println(isr.getEncoding());
            BufferedReader br = new BufferedReader(isr);
            BufferedWriter bw = new BufferedWriter(osw);
            boolean flag = true;
            String str;
            int count = 0;
            
           
            
            
            
            bw.write("prompt PL/SQL Developer import file");
            bw.newLine();
            bw.write(" prompt Created on 2014年4月8日 by test");
            bw.newLine();
            bw.write(" set feedback off");
            bw.newLine();
            bw.write(" set define off");
            bw.newLine();
            bw.write(" prompt Deleting DMDB.DM_JL_CRP_FORMULACODE...");
            bw.newLine();
            bw.write("DELETE FROM DMDB.DM_JL_CRP_FORMULACODE where version='20140101' and appcode='001';");
            bw.newLine();
            
            bw.write(" commit;");
            bw.newLine();
            int i=0;
            while (flag) {
                str = br.readLine();
               
                if ("".equals(str)) {
                    continue;
                }
                if (str == null) {
                    flag = false;
                    break;
                }
                System.out.println(i);
                System.out.println(str);
                bw.write("INSERT INTO DMDB.DM_JL_CRP_FORMULACODE(VERSION,ACTIVITY,APPCODE,FORMULACODE,FORMULACONTENT,FORMULATYPE,FORMULALEVEL,REMARK1,REMARK2,AUTO_CALCULATE,CJR,CJRQ,LRR,LRRQ) VALUES ('20140101','1','001','");
                bw.write(String.valueOf(count++));
                bw.write("','");
                bw.write(str.substring(0, str.indexOf("说明")).trim());
                bw.write("','1','1','");
                
                //bw.write(str.substring(str.indexOf("说明"), str.length()).trim());                
                //bw.write("',null,'hazz',sysdate,'hazz',sysdate)\n/");
                

                bw.write(str.substring(str.indexOf("说明"), str.indexOf("提示转换")).trim().replaceAll(" ", ""));
                bw.write("','");
                if(str.indexOf("不自动计算")>0){
                	bw.write(str.substring(str.indexOf("提示转换")+5, str.indexOf("不自动计算")).trim().replaceAll(" ", ""));
                	bw.write("','0");
                }else{
                	bw.write(str.substring(str.indexOf("提示转换")+5, str.length()).trim().replaceAll(" ", ""));
                	bw.write("','1");
                }
                bw.write("','system',sysdate,'system',sysdate);");
                
                bw.newLine();
                if(i%200==0){
                	 bw.write("commit;");
                	 bw.newLine();
                }
                i++;
            }
            bw.write(" commit;");
            bw.newLine();
            bw.flush();
           
            System.out.println("file ok!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileMan fileman = new FileMan();
        fileman.generateFormulasSQLScript("\\formula\\formula.txt");
    }
}
