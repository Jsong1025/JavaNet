package callback;
import javax.xml.bind.DatatypeConverter;


public class ReturnDigestUserInterface {
	
	public static void main(String[] args) {
		
		String[] filenames = {"test","test2","test3"};

		ReturnDigest[] digests = new ReturnDigest[filenames.length];

		for (int i = 0 ; i < filenames.length ; i++) {
			//¼ÆËãÕªÒª
			digests[i] = new ReturnDigest(filenames[i]);
			digests[i].start();
		}

		for (int i = 0 ; i < filenames.length ; i++) {
			while (true) {
				//ÏÔÊ¾
				byte[] digest = digests[i].getDigest();
				if (digest != null) {
					StringBuilder result = new StringBuilder(filenames[i]);
					result.append(":");
					result.append(DatatypeConverter.printHexBinary(digest));
					System.out.println(result);
					break;
				}
			}
		}
	}

}
