package NB.formulate;

public class A105081 {
	public static void main(String [] arg)throws Exception{

		A105081 obj=new A105081();
		obj.product1();
		System.out.println("\n");
		obj.product2();System.out.println("\n");
		obj.product3();System.out.println("\n");
		obj.product4();System.out.println("\n");
		obj.product5();
	}
	
	public void product1(){
		String fmt="";
		int l1=1,l4=4,l7=7,l10=10,l13=13,l16=16;
		for(int i=1;i<=13;i++){
			String subfmt="TA105081I"+l1+"+TA105081I"+l4+"+TA105081I"+l7+"+TA105081I"+l10+"+TA105081I"+l13;	
			String fmtmsq="��16";
			String fmtmsh="��1+��4+��7+��10+��13";
			fmt="TA105081I"+l16
			+"="+subfmt
			+" ˵��:�̶��ʲ������۾ɡ��۳���ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:TA105081I"+l16+"|"+fmtmsq+";"+subfmt+" |"+fmtmsh;
			
			System.out.println(fmt);
			l1+=20;l4+=20;l7+=20;l10+=20;l13+=20;l16+=20;
		}
	}
	public void product2(){
		String fmt="";
		int l2=2,l5=5,l8=8,l11=11,l14=14,l18=18;
		for(int i=1;i<=13;i++){
			String subfmt="TA105081I"+l2+"+TA105081I"+l5+"+TA105081I"+l8+"+TA105081I"+l11+"+TA105081I"+l14;	
			String fmtmsq="��18";
			String fmtmsh="��2+��5+��8+��11+��14";
			fmt="TA105081I"+l18
			+"="+subfmt
			+" ˵��:�̶��ʲ������۾ɡ��۳���ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:TA105081I"+l18+"|"+fmtmsq+";"+subfmt+" |"+fmtmsh;
			
			System.out.println(fmt);
			l2+=20;l5+=20;l8+=20;l11+=20;l14+=20;l18+=20;
		}
	}
	public void product3(){
		String fmt="";
		int l3=3,l6=6,l9=9,l12=12,l15=15,l20=20;
		for(int i=1;i<=13;i++){
			String subfmt="TA105081I"+l3+"+TA105081I"+l6+"+TA105081I"+l9+"+TA105081I"+l12+"+TA105081I"+l15;	
			String fmtmsq="��20";
			String fmtmsh="��3+��6+��9+��12+��15";
			fmt="TA105081I"+l20
			+"="+subfmt
			+" ˵��:�̶��ʲ������۾ɡ��۳���ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:TA105081I"+l20+"|"+fmtmsq+";"+subfmt+" |"+fmtmsh;
			
			System.out.println(fmt);
			l3+=20;l6+=20;l9+=20;l12+=20;l15+=20;l20+=20;
		}
	}
	
	public void product4(){
		String fmt="";
		String tablename="TA105081I";
		String fmtmsq="��1";
		String fmtmsh="��2+��3+��4+��5+��6+��7";
		int hs=1,ls=8,hsbac=2;
		String subfmt="";
		int h1=1,h2=21,h3=31,h4=41,h5=51,h6=61;
		
		for(int i=1;i<=20;i++){
			hsbac=hs;
			for(int j=0;j<6;j++){
				
				hsbac+=20;
				if(j==5){
					subfmt=subfmt+tablename+hsbac;	
				}else{
					subfmt=subfmt+tablename+hsbac+"+";
				} 
						
			}
			fmt=tablename+hs+"="+subfmt
			+" �̶��ʲ������۾ɡ��۳���ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:"+tablename+hs+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			hs++;
			subfmt="";
		}
	}
	
	public void product5(){
		String fmt="";
		String tablename="TA105081I";
		String fmtmsq="��9";
		String fmtmsh="��10+��12";
		int hs=9,ls=8,hsbac=2;
		String subfmt="";
		int h9=161,h10=181,h12=221;
		
		for(int i=1;i<=20;i++){
			
			subfmt="TA105081I"+h10+"+TA105081I"+h12;	
			fmt=tablename+h9+"="+subfmt
			+" �̶��ʲ������۾ɡ��۳���ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:"+tablename+h9+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			h9+=1;h10+=1;h12+=1;
			subfmt="";
		}
	}
}
