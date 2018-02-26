package vikrant;

import java.io.*;

public class copyfile {
	public void copy(String add1 ,String add2) {

		File src =new File(add1);
		File decs =new File(add2);
		
		BufferedReader reader = null;
		PrintWriter writer = null;
		
		String line;
		
		try {
			if (decs.createNewFile() || !decs.createNewFile()) {
				reader =new BufferedReader(new FileReader(src));
				writer =new PrintWriter(new FileWriter(decs));
			}
			
			while ((line =reader.readLine()) != null ) {
				System.out.println(line);
				writer.println(line);
			}
			
			reader.close();
			writer.close();
		}catch (IOException ioEx) {
			System.out.println("I could not copy");
		}
	
	}

}
