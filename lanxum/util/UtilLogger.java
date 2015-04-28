package com.lscdz.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Message logger
 * @author jasper
 * 2012.01.18
 */
public class UtilLogger {
	// 格式化时间
    private static final SimpleDateFormat timeFormat = 
    	new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Singleton 实例
	private static UtilLogger logger = new UtilLogger();
	
	// 日志文件路径名
	private String logFilename = null;
	
	private UtilLogger() {
	}
	
	/**
	 * 初始化日志文件，添加空行
	 */
	synchronized protected static void initLogWriter(String filename) {
		try {
			logger.logFilename = filename;
			FileWriter fw = new FileWriter(logger.logFilename, true);
			BufferedWriter logBw = new BufferedWriter(fw);
			logBw.newLine();
			logBw.write(timeFormat.format(new Date()) + ", server startup...");
			logBw.newLine();
			try { logBw.close(); } catch (Exception ie) {}
		} catch (IOException e) {
		}
	}
	
	/**
	 * 写入日志
	 * @param message
	 */
	synchronized protected static void log(String message) {
		try {
			FileWriter fw = new FileWriter(logger.logFilename, true);
			BufferedWriter logBw = new BufferedWriter(fw);
			logBw.write(timeFormat.format(new Date()) + ": " + message);
			logBw.newLine();
			try { logBw.close(); } catch (Exception ie) {}
		} catch (IOException e) {
		}
	}
	
	/**
	 * 写入错误日志
	 * @param message
	 */
	synchronized protected static void logErr(Exception e, String msg) {
		try {
			FileWriter fw = new FileWriter(logger.logFilename, true);
			BufferedWriter logBw = new BufferedWriter(fw);
			Date dt = new Date();
			logBw.write(timeFormat.format(dt) + ": " + e.toString());
			logBw.newLine();
			logBw.write(timeFormat.format(dt) + ": Extend msg, " + msg);
			logBw.newLine();
			try { logBw.close(); } catch (Exception ie) {}
		} catch (IOException ex) {
		}
	}

	/**
	 * 写入错误日志
	 * @param message
	 */
	synchronized protected static void logErr(String msg) {
		try {
			FileWriter fw = new FileWriter(logger.logFilename, true);
			BufferedWriter logBw = new BufferedWriter(fw);
			logBw.write(timeFormat.format(new Date()) + ": Extend msg, " + msg);
			logBw.newLine();
			try { logBw.close(); } catch (Exception ie) {}
		} catch (IOException ex) {
		}
	}
}