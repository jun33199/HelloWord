package patterns;

public class Singleton {

	// 饿汉式
	//	private static Singleton instance = new Singleton();
	//	
	//	private Singleton(){
	//		
	//	}
	//	
	//	public static Singleton getInstance(){
	//		return instance;
	//	}

	// 懒汉式
//	private static Singleton instance = null;
//
//	private Singleton() {
//
//	}
//
//	public static synchronized Singleton getInstance() {
//		if(instance==null){
//			instance = new Singleton();
//		}
//		return instance;
//	}
	
	// 双重校验锁

//	private static volatile Singleton instance;
//
//	private Singleton() {
//
//	}
//
//	public static Singleton getInstance() {
//
//		if (instance == null) {
//			synchronized (Singleton.class) {
//				if (instance == null) {
//					instance = new Singleton();
//				}
//			}
//		}
//
//		return instance;
//	}

	// 静态内部类
	
	private static class SingletonHolder{
		private static final Singleton INSTANCE = new Singleton();
	}
	
	private Singleton(){
		
	}
	
	public static final Singleton getInstance(){
		return SingletonHolder.INSTANCE;
	}
}
