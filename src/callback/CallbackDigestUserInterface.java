package callback;
import javax.xml.bind.DatatypeConverter;


public class CallbackDigestUserInterface {
	
	public static void receiveDigest(byte[] digest,String name) {
		StringBuilder result = new StringBuilder(name);
		result.append(":");
		result.append(DatatypeConverter.printHexBinary(digest));
		System.out.println(result);
	}
	
	
	public static void main(String[] args) {
		String[] filenames = {"test","test2","test3"};

		for (int i = 0 ; i < filenames.length ; i++) {
			//¼ÆËãÕªÒª
			CallbackDigest digests = new CallbackDigest(filenames[i]);
			digests.start();
		}

	}

}
