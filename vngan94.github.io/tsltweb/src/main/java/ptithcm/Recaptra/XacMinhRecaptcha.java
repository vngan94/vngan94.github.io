package ptithcm.Recaptra;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

public class XacMinhRecaptcha {
	public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	public static final String secretkey = "6LfiYPkfAAAAAA6eM0w_-9K0rU7GyJLsQXrsQfX3";
	public final static String USER_AGENT = "Mozilla/5.0";
	public static boolean xacminh(String r) {
		if(r == null || r.equals("")) {
			return false;
		}
		try {
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language","en-US,en;q=0.5");
			
			String postParams = "secret=" +secretkey+"&response=" + r;
			
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			JsonReader jR = Json.createReader(new StringReader(response.toString()));
			JsonObject jO = jR.readObject();
			jR.close();
			return jO.getBoolean("success");
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
