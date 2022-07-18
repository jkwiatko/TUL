package zad2;
import java.net.URL;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.io.IOException;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Vector;


class HTMLExtractor{
	    
    URL url;
    InputStream is = null;
    BufferedReader br;
    InetAddress ip;
    String line;
    String html = "";
    Pattern patternLink = Pattern.compile("(?i)<a([^>]+)>(.+?)</a>");
    Pattern patternLink2 = Pattern.compile("\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))");
    Pattern patternEmail = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\\\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    Pattern patternHead = Pattern.compile("<head.*?>([\\s\\S]*?)<\\/head>");
    Matcher matcherLink;
    Matcher matcherLink2;
    Matcher matcherEmail;
    Matcher matcherHead;


    HTMLExtractor(String address){
    try {
        url = new URL(address);
        is = url.openStream();  // throws an IOException
        br = new BufferedReader(new InputStreamReader(is));
        ip = InetAddress.getByName(url.getHost());

        while ((line = br.readLine()) != null) {
            html += line;
        }
    } catch (MalformedURLException mue) {
         mue.printStackTrace();
    } catch (IOException ioe) {
         ioe.printStackTrace();
    } finally {
        try {
            if (is != null) is.close();
        } catch (IOException ioe) {
        }
    }
	}

	String showHTML(){
		return html;
	}

		String extractHead(){
			matcherHead = patternHead.matcher(html);
			matcherHead.find();
			String head = matcherHead.group(0);
			return head;
	}

	String extractIp(){
		return ip.getHostAddress();
	}
	

	Vector<String> extractEmails(){
		Vector<String> emails = new Vector<>();
		matcherEmail = patternEmail.matcher(html);
		while(matcherEmail.find()){
			String email = matcherEmail.group(0);
			emails.add(email);
		}
		return emails;
	}

	Vector<String> extractLinks(){
		Vector<String> Links = new Vector<>();
		matcherLink = patternLink.matcher(html);
		while(matcherLink.find()){
			String href = matcherLink.group(1);
			String linkText = matcherLink.group(2);

			matcherLink2 = patternLink2.matcher(href);
			while(matcherLink2.find()){
				String link = matcherLink2.group(1);
				Links.add(link);
			}
		}
		return Links;
	}
}

