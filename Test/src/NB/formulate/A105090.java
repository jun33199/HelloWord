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
			String fmtmsq="��3";
			String fmtmsh="��1-��2";
			fmt="TA105090I"+l3
			+"="+subfmt
			+" �ʲ���ʧ˰ǰ�۳�����˰������ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:TA105090I"+l3+"|"+fmtmsq+";"+subfmt+" |"+fmtmsh;
			
			System.out.println(fmt);
			l1+=3;l2+=3;l3+=3;
		}
	}

	
	public void product2(){
		String fmt="";
		String tablename="TA105090I";
		String fmtmsq="��1";
		String fmtmsh="��2+��3+��4+��5+��6+��7+��8";
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
			+" �ʲ���ʧ˰ǰ�۳�����˰������ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:"+tablename+hs+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			hs++;
			subfmt="";
		}
	}
	
	public void product3(){
		String fmt="";
		String tablename="TA105090I";
		String fmtmsq="��14";
		String fmtmsh="��1+��9";
		String subfmt="";
		int h14=40,h1=1,h9=25;
		
		for(int i=1;i<=3;i++){
			
			subfmt="TA105090I"+h1+"+TA105090I"+h9;	
			fmt=tablename+h14+"="+subfmt
			+" �ʲ���ʧ˰ǰ�۳�����˰������ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:"+tablename+h14+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			h14+=1;h1+=1;h9+=1;
			subfmt="";
		}
	}
}
