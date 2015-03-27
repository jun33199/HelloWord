package NB.formulate;

public class A105090 {
	public static void main(String [] arg)throws Exception{

		A105090 obj=new A105090();
		obj.product3();
		
	}
	
	public void product1(){
		String fmt="";
		int l1=1,l2=2,l3=3;
		for(int i=1;i<=14;i++){
			String subfmt="TA105090I"+l1+"-TA105090I"+l2;	
			String fmtmsq="列3";
			String fmtmsh="列1-列2";
			fmt="TA105090I"+l3
			+"="+subfmt
			+" 资产损失税前扣除及纳税调整明细表，"+fmtmsq+"="+fmtmsh+"。 提示转换:TA105090I"+l3+"|"+fmtmsq+";"+subfmt+" |"+fmtmsh;
			
			System.out.println(fmt);
			l1+=3;l2+=3;l3+=3;
		}
	}

	
	public void product2(){
		String fmt="";
		String tablename="TA105090I";
		String fmtmsq="行1";
		String fmtmsh="行2+行3+行4+行5+行6+行7+行8";
		int hs=1,ls=8,hsbac=1;
		String subfmt="";
		
		for(int i=1;i<=3;i++){
			hsbac=hs;
			for(int j=0;j<7;j++){
				
				hsbac+=3;
				if(j==6){
					subfmt=subfmt+tablename+hsbac;	
				}else{
					subfmt=subfmt+tablename+hsbac+"+";
				} 
						
			}
			fmt=tablename+hs+"="+subfmt
			+" 资产损失税前扣除及纳税调整明细表，"+fmtmsq+"="+fmtmsh+"。 提示转换:"+tablename+hs+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			hs++;
			subfmt="";
		}
	}
	
	public void product3(){
		String fmt="";
		String tablename="TA105090I";
		String fmtmsq="行14";
		String fmtmsh="行1+行9";
		String subfmt="";
		int h14=40,h1=1,h9=25;
		
		for(int i=1;i<=3;i++){
			
			subfmt="TA105090I"+h1+"+TA105090I"+h9;	
			fmt=tablename+h14+"="+subfmt
			+" 资产损失税前扣除及纳税调整明细表，"+fmtmsq+"="+fmtmsh+"。 提示转换:"+tablename+h14+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			h14+=1;h1+=1;h9+=1;
			subfmt="";
		}
	}
}
