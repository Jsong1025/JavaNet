package weblog;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 检查是否是 垃圾邮件发送至（spammer）
 * 
 * 将要查询的IP地址逆置，拼接上sbl.sapmhaus.org，查看是否存在
 * 		例如：查询IP 17.34.87.207是否是Spammer，
 * 			则查询主机名 207.87.34.17.sbl.sapmhaus.org 是否存在
 *
 */
public class SpamCheck {
	
	public static final String BLACKHOLE = "sbl.sapmhaus.org";
	
	public static void main(String[] args) {
		for (String arg : args) {
			if (isSpammer(arg)) {
				System.out.println(arg+"is a known spammer.");
			} else {
				System.out.println(arg+"appears legitimate.");
			}
		}
	}
	
	public static boolean isSpammer(String arg){
		try {
			InetAddress address = InetAddress.getByName(arg);
			byte[] quad = address.getAddress();
			String query = BLACKHOLE;
			
			for (byte octet : quad) {
				int unsignedByte = octet<0 ? octet+256 : octet;
				query = unsignedByte + "." +query;
			}
			InetAddress.getAllByName(query);
			return true;
		} catch (UnknownHostException e) {
			return false;
		}
	}

}
