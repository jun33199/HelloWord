package NB.formulate;

public class formulateFactory {
	public static void main(String [] arg)throws Exception{

		formulateFactory obj=new formulateFactory();
		obj.product2();
		
	}
	
	public void product1(){
		String fmt="";
		String tablename="TA105080I";
		String fmtmsq="��19";
		String fmtmsh="��20+��21+��22+��23+��24";
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
			+" �ʲ��۾ɡ�̯���������˰������ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:"+tablename+hs+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
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
			String fmtmsq="��16";
			String fmtmsh="��1+��4+��7+��10+��13";
			fmt="TA105081I"+l16
			+"="+subfmt
			+" ˵��:�̶��ʲ������۾ɡ��۳���ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:TA105081I"+l16+"|"+fmtmsq+";"+subfmt+" |"+fmtmsh;
			
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
			String fmtmsq="��1��12";
			String fmtmsh="��1��3-��5-��6-��7-��8-��9-��10";
			fmt="TA105040I"+hlq
			+"="+subfmt
			+" ˵��:Ͷ��������˰������ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:TA105040I"+hlq+"|"+fmtmsq+";"+subfmt+" |"+fmtmsh;
			
			System.out.println(fmt);
		}
	}
}
