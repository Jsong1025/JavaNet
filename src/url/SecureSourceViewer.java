package url;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;

public class SecureSourceViewer {

	public static void main(String[] args) {
		String line[] = {"ftp://localhost"};
		
		Authenticator.setDefault(new DialogAuthenticator());

		for (int i = 0; i < line.length; i++) {
			try {
				// URL打开读取
				URL u = new URL(line[i]);
				InputStream in = null;
				try {
					in = new BufferedInputStream(u.openStream());
					Reader r = new InputStreamReader(in);
					int c;
					while ((c = r.read()) != -1) {
						System.out.print((char)c);
					}

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			//分割
			System.out.println();
		}

		System.exit(0);
	}

}

