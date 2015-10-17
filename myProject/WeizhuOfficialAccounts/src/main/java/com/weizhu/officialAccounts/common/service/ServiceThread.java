package com.weizhu.officialAccounts.common.service;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ServiceThread extends Thread {

	public ServiceThread() {
		super();
	}

	public ServiceThread(Runnable target, String name) {
		super(target, name);
	}

	public ServiceThread(Runnable target) {
		super(target);
	}

	public ServiceThread(String name) {
		super(name);
	}

	public ServiceThread(ThreadGroup group, Runnable target, String name, long stackSize) {
		super(group, target, name, stackSize);
	}

	public ServiceThread(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
	}

	public ServiceThread(ThreadGroup group, Runnable target) {
		super(group, target);
	}

	public ServiceThread(ThreadGroup group, String name) {
		super(group, name);
	}

	public static final class Factory implements ThreadFactory {
		private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public Factory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :  Thread.currentThread().getThreadGroup();
            namePrefix = "service-thread-";
        }

        public Thread newThread(Runnable r) {
        	ServiceThread t = new ServiceThread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
	}
	
}
