package cn.edu.bupt.replace;

import cn.edu.bupt.conf.Configurations;

public class ReplaceMain {
    
    public static void main(String [] arg)throws Exception{
        Configurations.initConfigurations ();
        Configurations cfg = Configurations.getInstance ();
        boolean apireplaceActionIsRun=cfg.isApireplaceActionIsRun();
        boolean generateSetActionIsRun=cfg.isGenerateSetActionIsRun();
        if(true==generateSetActionIsRun){
            new GenerateSetAction();
       }
        if(true==apireplaceActionIsRun){
            new ApiReplaceAction();
        }
    
    }
    
}
