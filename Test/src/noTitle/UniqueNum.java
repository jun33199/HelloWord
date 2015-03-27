package noTitle;
import java.net.InetAddress;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
public class UniqueNum {
	private static AtomicInteger counter = new AtomicInteger(0);
	public static void main(String [] a)throws Exception{
		long time=System.currentTimeMillis();
		System.out.println(time);
		InetAddress addr = InetAddress.getLocalHost();		
		String ip=addr.getHostAddress().toString();//获得本机IP
		ip=ip.replace('.', '0');
		String address=addr.getHostName().toString();//获得本机名称详情>>
		System.out.println(ip);
		System.out.println(address);
		System.out.println(UUID.randomUUID().toString());	
		Random r=new Random();
		int i=r.nextInt();
		System.out.println(Math.abs(i)+"/"+r.nextInt(32));
		System.out.println(i+"/"+r.nextInt(32));
		System.out.println(time+""+Math.abs(i)+ip);
		
	}
}
