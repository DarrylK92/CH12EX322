import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class RankingSummary {

	public static void main(String[] args) {	
		//Get files
		for(int i = 2001; i <= 2010; i++) {
			try {
				URL url = new URL("http://liveexample.pearsoncmg.com/data/babynamesranking" + i + ".txt");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				int rc = conn.getResponseCode();
				
				if (rc == HttpURLConnection.HTTP_OK) {
		            InputStream is = conn.getInputStream();
		            FileOutputStream os = new FileOutputStream(new File(i + ".txt"));
		            
		            int ds = is.read();
		            while (ds != -1) {
		                os.write(ds);
		                ds = is.read();
		            }
		 
		            os.close();
		            is.close();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Display table headers
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println("Year Rank 1    Rank 2    Rank 3    Rank 4    Rank 5    Rank 1    Rank 2    Rank 3    Rank 4    Rank 5");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		
		//Open files and output rankings
		Scanner dataScanner = null;
		
		for(int i = 2010; i <= 2001; i--) {
			try {
				dataScanner = new Scanner(new File(i + ".txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			//Read data into ArrayList
			ArrayList<String> data = new ArrayList<>();
			
			String getValue = "";
			boolean exitLoop = false;
			while(!exitLoop) {
				getValue = dataScanner.next().trim();
				if (getValue.equals("6")) {
					exitLoop = true;
					System.out.println(data);
				} else {
					data.add(getValue);
				}
			}
			
			String format = "%s%s%s%s%s%s%s%s%s%s";
			System.out.println(i + "  " + data.get(3).format(format));
		}
	}

}
