/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
/**
 *
 * @author HOME
 */
public class SmsSender {
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36";
    public boolean SendSMS(String mobile , String msgText)throws MalformedURLException, ProtocolException, IOException {
        try{
        
        
        String url = "https://smsapi.engineeringtgr.com/send/?Mobile=8602281211&Password=seemagupta&Message="+msgText+"&To="+ mobile;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Upgrade-Insecure-Requests", "1");
                con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
		con.setRequestProperty("DNT", "1");
		 
                int responseCode = con.getResponseCode();
                String response = con.getResponseMessage();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode + "Response Msg : " + response);
                return true;
        }catch(Exception e){
            System.out.println("exception"+ e.getMessage());
            throw new RuntimeException(e);
        }
        }
}
