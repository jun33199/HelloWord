package NB;

public class ViewSql {
	public static String[] CREPORTS_TABLEIDS_QYSDS_ND_2014 = { "A100000","A101010","A101020","A102010","A102020","A103000","A104000","A105000","A105010",
		"A105020","A105030","A105040","A105050","A105060","A105070","A105080","A105081",
		"A105090","A105091","A105100","A105110","A105120","A106000","A107010","A107011",
		"A107012","A107013","A107014","A107020","A107030","A107040","A107041","A107042",
		"A107050","A108000","A108010","A108020","A108030","A109000","A109010" };
	public static int i=0;
	public static void main(String [] arg)throws Exception {
		
		String sql="";
		for(String sbdm:CREPORTS_TABLEIDS_QYSDS_ND_2014){
			i++;
			if(i==1)
				sql=sql+"select "+sbdm+".* from SBDB.SB_JL_QYSDSSBB_CB_ND_"+sbdm+" "+sbdm+"\n";
			else
				sql=sql+" union select "+sbdm+".* from SBDB.SB_JL_QYSDSSBB_CB_ND_"+sbdm+" "+sbdm+"\n";	
		}
		
		System.out.println(sql);
	}
}
