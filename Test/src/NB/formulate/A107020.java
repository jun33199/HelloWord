package NB.formulate;

public class A107020 {
	public static void main(String [] arg)throws Exception{

		A107020 obj=new A107020();
		obj.product9();
		
	}
	public void product1(){
		String fmt="";
		String tablename="TA107020I";
		String fmtmsq="��2";
		String fmtmsh="��3+��4+��5+��6+��7+��8+��9+��11+��12";
		String subfmt="";
		int h2=8,h3=15,h4=22,h5=29,h6=36,h7=43,h8=50,h9=57,h11=71,h12=78;
		
		for(int i=1;i<=7;i++){
			
			subfmt="TA107020I"+h3+"+TA107020I"+h4+"+TA107020I"+h5+"+TA107020I"+h6+"+TA107020I"+h7+"+TA107020I"+h8+"+TA107020I"+h9+"+TA107020I"+h11+"+TA107020I"+h12;	
			fmt=tablename+h2+"="+subfmt
			+" ���ü����Ż���ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:"+tablename+h2+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			h2+=1;h3+=1;h4+=1;h5+=1;h6+=1;h7+=1;h8+=1;h9+=1;h11+=1;h12+=1;
			subfmt="";
		}
	}
	
	
	public void product2(){
		String fmt="";
		String tablename="TA107020I";
		String fmtmsq="��13";
		String fmtmsh="��14+��15+��16";
		String subfmt="";
		int h13=85,h14=92,h15=99,h16=106;
		
		for(int i=1;i<=7;i++){
			
			subfmt="TA107020I"+h14+"+TA107020I"+h15+"+TA107020I"+h16;	
			fmt=tablename+h13+"="+subfmt
			+" ���ü����Ż���ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:"+tablename+h13+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			h13+=1;h14+=1;h15+=1;h16+=1;
			subfmt="";
		}
	}	
	
	public void product3(){
		String fmt="";
		String tablename="TA107020I";
		String fmtmsq="��17";
		String fmtmsh="��18+��19+��20+��21+��22+��23+��24+��25";
		String subfmt="";
		int h17=113,h18=120,h19=127,h20=134,h21=141,h22=148,h23=155,h24=162,h25=169;
		
		for(int i=1;i<=7;i++){
			
			subfmt="TA107020I"+h18+"+TA107020I"+h19+"+TA107020I"+h20+"+TA107020I"+h21+"+TA107020I"+h22+"+TA107020I"+h23+"+TA107020I"+h24+"+TA107020I"+h25;	
			fmt=tablename+h17+"="+subfmt
			+" ���ü����Ż���ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:"+tablename+h17+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			h17+=1;h18+=1;h19+=1;h20+=1;h21+=1;h22+=1;h23+=1;h24+=1;h25+=1;
			subfmt="";
		}
	}	
	
	public void product4(){
		String fmt="";
		String tablename="TA107020I";
		String fmtmsq="��26";
		String fmtmsh="��27+��28+��29+��30+��31+��32";
		String subfmt="";
		int h26=176,h27=183,h28=190,h29=197,h30=204,h31=211,h32=218;
		
		for(int i=1;i<=7;i++){
			
			subfmt="TA107020I"+h27+"+TA107020I"+h28+"+TA107020I"+h29+"+TA107020I"+h30+"+TA107020I"+h31+"+TA107020I"+h32;	
			fmt=tablename+h26+"="+subfmt
			+" ���ü����Ż���ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:"+tablename+h26+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			h26+=1;h27+=1;h28+=1;h29+=1;h30+=1;h31+=1;h32+=1;
			subfmt="";
		}
	}	
	public void product5(){
		String fmt="";
		String tablename="TA107020I";
		String fmtmsq="��36";
		String fmtmsh="��37+��38+��39";
		String subfmt="";
		int h36=233,h37=240,h38=247,h39=254;
		
		for(int i=1;i<=7;i++){
			
			subfmt="TA107020I"+h37+"+TA107020I"+h38+"+TA107020I"+h39+"+TA107020I";	
			fmt=tablename+h36+"="+subfmt
			+" ���ü����Ż���ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:"+tablename+h36+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			h36+=1;h37+=1;h38+=1;h39+=1;
			subfmt="";
		}
	}
	
	public void product6(){
		String fmt="";
		String tablename="TA107020I";
		String fmtmsq="��40";
		String fmtmsh="��1+��17+��26+��33+��36";
		String subfmt="";
		int h40=261,h1=1,h17=113,h26=176,h33=225,h36=233;
		
		for(int i=1;i<=7;i++){
			
			subfmt="TA107020I"+h1+"+TA107020I"+h17+"+TA107020I"+h26+"+TA107020I"+h33+"+TA107020I"+h36;	
			fmt=tablename+h40+"="+subfmt
			+" ���ü����Ż���ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:"+tablename+h40+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			h36+=1;h40+=1;h1+=1;h17+=1;h26+=1;h33+=1;
			subfmt="";
		}
	}
	
	public void product7(){
		String fmt="";
		String tablename="TA107020I";
		String fmtmsq="��6";
		String fmtmsh="��1-��2-��3-��4+��5";
		String subfmt="";
		int hs=233;
		int htmp=1;
		int hq=238;
		for(int i=1;i<=40;i++){
			htmp=hs;
			for(int j=1;j<=5;j++){
				if(j==4){
					subfmt=subfmt+tablename+htmp+"+";
				}else if(j==5){
					subfmt=subfmt+tablename+htmp;
				}else{
					subfmt=subfmt+tablename+htmp+"-";
				}
				htmp+=1;
			}
			
			fmt=tablename+hq+"="+subfmt
			+" ���ü����Ż���ϸ��"+fmtmsq+"="+fmtmsh+"�� ��ʾת��:"+tablename+hq+"|"+fmtmsq+";"+subfmt+"|"+fmtmsh;
			
			System.out.println(fmt);
			hq+=7;hs+=7;
			subfmt="";
			
		}
	}
	public void product8(){
		String fmt="";
		String tablename="TA107020I";
		String subfmt="";
		int hq=4;
		for(int i=1;i<=40;i++){			
			fmt=tablename+hq+">=0"+subfmt
			+" ˵���� ���ü����Ż���ϸ����"+i+"��4��0�� ��ʾת��:"+tablename+hq+"|"+i+"��4";
			
			System.out.println(fmt);
			hq+=7;
			subfmt="";
			
		}
	}

	public void product9(){
		String fmt="";
		String tablename="TA107020I";
		String subfmt="";
		int hq=21;
		for(int i=3;i<=12;i++){		
			subfmt=tablename+(hq-1);
			fmt=tablename+hq+"="+subfmt
			+" ˵���� ���ü����Ż���ϸ����"+i+"��7=��"+i+"��6�� ��ʾת��:"+tablename+hq+"|��"+i+"��7;"+tablename+(hq-1)+"|��"+i+"��6;";
			
			System.out.println(fmt);
			hq+=7;
			subfmt="";
			
		}
	}
}
