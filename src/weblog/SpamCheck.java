package weblog;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ����Ƿ��� �����ʼ���������spammer��
 * 
 * ��Ҫ��ѯ��IP��ַ���ã�ƴ����sbl.sapmhaus.org���鿴�Ƿ����
 * 		���磺��ѯIP 17.34.87.207�Ƿ���Spammer��
 * 			���ѯ������ 207.87.34.17.sbl.sapmhaus.org �Ƿ����
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
