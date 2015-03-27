package NB.formulate;

public class formulateFactory {
	public static void main(String [] arg)throws Exception{

		formulateFactory obj=new formulateFactory();
		obj.product2();
		
	}
	
	public void product1(){
		String fmt="";
		String tablename="TA105080I";
		String fmtmsq="行19";
		String fmtmsh="行20+行21+行22+行23+行24";
		int hs=162,ls=8,hsbac=162;
		String subfmt="";

		
		for(int i=1;i<=8;i++){
			hsbac=hs;
			for(int j=0;j<5;j++){
				
				hsbac+=8;
				if(j==4){
					subfmt=subfmt+tablename+hsbac;	
				}else{
					subfmt=subfmt+tablename+hsbac+"+";
				} 
						
			}
			fmt=tablename+hs+"="+subfmt
			+" 资产折旧、摊销情况及纳税调整明细表，"+fmtmsq+"="+fmtmsh+"。 提示转换:"+tablename+hs+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			hs++;
			subfmt="";
		}
	}
	public void product2(){
		String fmt="";
		int l1=1,l4=4,l7=7,l10=10,l13=13,l16=16;
		for(int i=1;i<=13;i++){
			String subfmt="TA105081I"+l1+"+TA1050801"+l4+"+TA1050801"+l7+"+TA1050801"+l10+"+TA1050801"+l13;	
			String fmtmsq="列16";
			String fmtmsh="列1+列4+列7+列10+列13";
			fmt="TA105081I"+l16
			+"="+subfmt
			+" 说明:固定资产加速折旧、扣除明细表，"+fmtmsq+"="+fmtmsh+"。 提示转换:TA105081I"+l16+"|"+fmtmsq+";"+subfmt+" |"+fmtmsh;
			
			System.out.println(fmt);
			l1+=20;l4+=20;l7+=20;l10+=20;l13+=20;l16+=20;
		}
	}
	
	public void productHl(){
		int hlq=12;
		int hlh=3;
		String tablename="TA105040I";
		String fmt="";
		String subfmt=tablename+3+"-"+tablename+5+"-"+tablename+6+"-"+tablename+7+"-"+tablename+8+"-"+tablename+9+"-"+tablename+10;
		

		for(int i=1;i<=10;i++){
			String fmtmsq="行1列12";
			String fmtmsh="行1列3-列5-列6-列7-列8-列9-列10";
			fmt="TA105040I"+hlq
			+"="+subfmt
			+" 说明:投资收益纳税调整明细表，"+fmtmsq+"="+fmtmsh+"。 提示转换:TA105040I"+hlq+"|"+fmtmsq+";"+subfmt+" |"+fmtmsh;
			
			System.out.println(fmt);
		}
	}
}
