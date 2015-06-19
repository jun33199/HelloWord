

package cn.edu.bupt.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class Configurations {

    private Properties prop = null;

    private static Configurations INSTANCE;

//    private long firstImsi = 208200000000000l;
//
//    private long imsiRange = 35000;

    private String apiinfo;
    private String fileapi;
    private String[] fileid;
    private String mc_area;
    private String bc_area;
    private String db_driver;
    private String db_url;
    private String db_user;
    private String db_password;
    private String[] apiinfoSet;
    private String apiPercent;
    private boolean apireplaceIsRun;
    private boolean apiBayesIsRun;
    private boolean apireplaceActionIsRun;
    private boolean generateSetActionIsRun;
    
    private Configurations () throws IOException {

        InputStream inputSteam = null;
        try {
            prop = new Properties ();
            inputSteam = getClass ().getResourceAsStream ("/injector.properties");
            prop.load (inputSteam);
            apiinfo = prop.getProperty ("apiinfo");
            fileapi = prop.getProperty ("fileapi");
            
            String fileid_s = prop.getProperty ("fileid");
            if(fileid_s!=null &&fileid_s!=""){
                fileid=fileid_s.split(",");
            }
            mc_area = prop.getProperty ("mc_area");
            bc_area = prop.getProperty ("bc_area");
            db_driver = prop.getProperty ("db_driver");
            db_url = prop.getProperty ("db_url");
            db_user = prop.getProperty ("db_user");
            db_password = prop.getProperty ("db_password");
            String apiinfoSet_s = prop.getProperty ("apiinfoSet");
            if(apiinfoSet_s!=null &&apiinfoSet_s!=""){
                apiinfoSet=apiinfoSet_s.split(",");
            }
            apiPercent= prop.getProperty ("apiPercent");
            apireplaceIsRun="true".equals(prop.getProperty("apireplaceIsRun"))?true:false;
            apiBayesIsRun="true".equals(prop.getProperty("apiBayesIsRun"))?true:false;
            apireplaceActionIsRun="true".equals(prop.getProperty("apireplaceActionIsRun"))?true:false;
            generateSetActionIsRun="true".equals(prop.getProperty("generateSetActionIsRun"))?true:false;
        } catch (final Exception e) {
            e.printStackTrace ();
        } finally {
            if (inputSteam != null) {
                inputSteam.close ();
            }
        }

    }


    public static void initConfigurations () throws IOException {
        INSTANCE = new Configurations ();
    }


    public static Configurations getInstance () {
        return INSTANCE;
    }


    public Properties getProperties () {
        return prop;
    }


    public String getApiinfo() {
        return apiinfo;
    }


    public void setApiinfo(String apiinfo) {
        this.apiinfo = apiinfo;
    }


    public String getFileapi() {
        return fileapi;
    }


    public void setFileapi(String fileapi) {
        this.fileapi = fileapi;
    }


    public String[] getFileid() {
        return fileid;
    }


    public void setFileid(String[] fileid) {
        this.fileid = fileid;
    }


    public String getMc_area() {
        return mc_area;
    }


    public void setMc_area(String mc_area) {
        this.mc_area = mc_area;
    }


    public String getBc_area() {
        return bc_area;
    }


    public void setBc_area(String bc_area) {
        this.bc_area = bc_area;
    }


    public String getDb_driver() {
        return db_driver;
    }


    public void setDb_driver(String db_driver) {
        this.db_driver = db_driver;
    }


    public String getDb_url() {
        return db_url;
    }


    public void setDb_url(String db_url) {
        this.db_url = db_url;
    }


    public String getDb_user() {
        return db_user;
    }


    public void setDb_user(String db_user) {
        this.db_user = db_user;
    }


    public String getDb_password() {
        return db_password;
    }


    public void setDb_password(String db_password) {
        this.db_password = db_password;
    }




    public String[] getApiinfoSet() {
        return apiinfoSet;
    }


    public void setApiinfoSet(String[] apiinfoSet) {
        this.apiinfoSet = apiinfoSet;
    }


    public String getApiPercent() {
        return apiPercent;
    }


    public void setApiPercent(String apiPercent) {
        this.apiPercent = apiPercent;
    }


    public boolean isApireplaceIsRun() {
        return apireplaceIsRun;
    }


    public void setApireplaceIsRun(boolean apireplaceIsRun) {
        this.apireplaceIsRun = apireplaceIsRun;
    }


    public boolean isApiBayesIsRun() {
        return apiBayesIsRun;
    }


    public void setApiBayesIsRun(boolean apiBayesIsRun) {
        this.apiBayesIsRun = apiBayesIsRun;
    }


    public boolean isApireplaceActionIsRun() {
        return apireplaceActionIsRun;
    }


    public void setApireplaceActionIsRun(boolean apireplaceActionIsRun) {
        this.apireplaceActionIsRun = apireplaceActionIsRun;
    }


    public boolean isGenerateSetActionIsRun() {
        return generateSetActionIsRun;
    }


    public void setGenerateSetActionIsRun(boolean generateSetActionIsRun) {
        this.generateSetActionIsRun = generateSetActionIsRun;
    }






}
