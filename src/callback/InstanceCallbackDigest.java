package callback;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class InstanceCallbackDigest extends Thread {
	
	private String filename;
	private InstaceCallbackDigestUserInterface callback;
	
	public InstanceCallbackDigest(String filename,InstaceCallbackDigestUserInterface callback) {
		this.filename = filename;
		this.callback = callback;
	}
	
	@Override
	public void run() {
		try {
			FileInputStream in = new FileInputStream(filename);
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in, sha);
			
			while (din.read() != -1) ;
			din.close();
			byte[] digest = sha.digest();
			callback.receiveDigest(digest);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
