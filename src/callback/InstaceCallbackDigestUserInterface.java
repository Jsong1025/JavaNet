package callback;
import javax.xml.bind.DatatypeConverter;


public class InstaceCallbackDigestUserInterface {
	
	private String filename;
	private byte[] digest;
	
	public InstaceCallbackDigestUserInterface(String filename) {
		this.filename = filename;
	}
	
	public void receiveDigest(byte[] digest) {
		this.digest = digest;
		System.out.println(this);
	}
	
	public void calculateDigest() {
		InstanceCallbackDigest cd = new InstanceCallbackDigest(filename, this);
		cd.start();
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(filename);
		result.append(":");
		result.append(DatatypeConverter.printHexBinary(digest));
		return result.toString();
	}
	
	public static void main(String[] args) {
		String[] filenames = {"test","test2","test3"};

		for (int i = 0 ; i < filenames.length ; i++) {
			//¼ÆËãÕªÒª
			InstaceCallbackDigestUserInterface d = new InstaceCallbackDigestUserInterface(filenames[i]);
			d.calculateDigest();
		}

	}

}
