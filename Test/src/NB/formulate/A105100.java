package NB.formulate;

public class A105100 {
	public static void main(String [] arg)throws Exception{

		A105100 obj=new A105100();
		obj.product4();
		
	}
	public void product1(){
		String fmt="";
		String tablename="TA105100I";
		String fmtmsq="��14";
		String fmtmsh="��1+��4+��6+��8+��11+��12";
		String subfmt="";
		int h14=92,h1=1,h4=22,h6=36,h8=50,h11=71,h12=78;
		
		for(int i=1;i<=7;i++){
			
			subfmt="TA105100I"+h1+"+TA105100I"+h4+"+TA105100I"+h6+"+TA105100I"+h8+"+TA105100I"+h11+"+TA105100I"+h12;	
			fmt=tablename+h14+"="+subfmt
			+" ��ҵ������˰������ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:"+tablename+h14+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			h14+=1;h1+=1;h4+=1;h6+=1;h8+=1;h11+=1;h12+=1;
			subfmt="";
		}
	}
	
	public void product2(){
		String fmt="";
		int l1=1,l2=2,l3=3;
		for(int i=1;i<=14;i++){
			String subfmt="TA105100I"+l2+"-TA105100I"+l1;	
			String fmtmsq="��3";
			String fmtmsh="��2-��1";
			fmt="TA105100I"+l3
			+"="+subfmt
			+" ��ҵ������˰������ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:TA105100I"+l3+"|"+fmtmsq+";"+subfmt+" |"+fmtmsh;
			
			System.out.println(fmt);
			l1+=7;l2+=7;l3+=7;
		}
	}

	public void product3(){
		String fmt="";
		int l6=6,l5=5,l4=4;
		for(int i=1;i<=14;i++){
			String subfmt="TA105100I"+l5+"-TA105100I"+l4;	
			String fmtmsq="��6";
			String fmtmsh="��5-��4";
			fmt="TA105100I"+l6
			+"="+subfmt
			+" ��ҵ������˰������ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:TA105100I"+l6+"|"+fmtmsq+";"+subfmt+" |"+fmtmsh;
			
			System.out.println(fmt);
			l6+=7;l5+=7;l4+=7;
		}
	}	

	public void product4(){
		String fmt="";
		int l7=7,l3=3,l6=6;
		for(int i=1;i<=14;i++){
			String subfmt="TA105100I"+l3+"+TA105100I"+l6;	
			String fmtmsq="��7";
			String fmtmsh="��3+��6";
			fmt="TA105100I"+l7
			+"="+subfmt
			+" ��ҵ������˰������ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:TA105100I"+l7+"|"+fmtmsq+";"+subfmt+" |"+fmtmsh;
			
			System.out.println(fmt);
			l7+=7;l3+=7;l6+=7;
		}
	}

}
