package weblog;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PooledWebLog {

	private final static int NUM_THREADS = 4;

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
		Queue<LogEntry> results = new LinkedList<LogEntry>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(
					new InputStreamReader(new FileInputStream(args[0]),"UTF-8"));
			for (String entry = in.readLine();
					entry != null;
					entry = in.readLine()) {
				LookupTask task = new LookupTask(entry);
				Future<String> future = executor.submit(task);
				LogEntry result = new LogEntry(entry, future);
				results.add(result);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//开始打印结果
		for (LogEntry result : results) {
			try {
				System.out.println(result.future.get());
			} catch (Exception e) {
				System.out.println(result.original);
			}
		}
		executor.shutdown();
	}

	private static class LogEntry {
		String original;
		Future<String> future;

		public LogEntry(String original , Future<String> future) {
			this.original = original;
			this.future = future;
		}
	}

}
