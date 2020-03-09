import java.util.ArrayList;
import java.io.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import java.net.MalformedURLException;

public class Webtoon {

	private String name;
	private String site;
	private int ID;
	private int lastEpi;

	public Webtoon(String name, String site, int ID, int lastEpi) throws UnsupportedEncodingException, IOException{
		setName(name);
		setSite(site);
		setID(ID);
		setLastEpi(lastEpi);
	}

	public Webtoon(String name, String site) throws UnsupportedEncodingException, IOException{
		setName(name);
		setSite(site);
		findWebtoonID();
		findLastEpi();
	}

	public String getName(){
		return name;
	} 
	public String getSite(){
		return site;
	} 
	public int getID(){
		return ID;
	} 
	public int getLastEpi(){
		return lastEpi;
	} 
	public void setName(String name){
		this.name = name;
	} 
	public void setSite(String site){
		this.site = site;
	} 
	public void setID(int ID){
		this.ID = ID;
	} 
	public void setLastEpi(int lastEpi){
		this.lastEpi = lastEpi;
	} 

	public void findWebtoonID() throws UnsupportedEncodingException, IOException{

		String url = "https://comic.naver.com/search.nhn?keyword="
						+URLEncoder.encode(name,"UTF-8");
		String pattern = "<a href=\"\\/webtoon\\/list\\.nhn\\?titleId=(\\d*)\">" + name + "<\\/a>";


		ArrayList<String> idList = Webtoon.findPattern(pattern,url);

		if(idList.isEmpty()){
			throw new IOException("There is no ["+name+"]");
		}

		ID = Integer.parseInt(idList.get(0));//NumberFormatException
	}

	public void findLastEpi() throws NumberFormatException, IOException{

		String url = "https://comic.naver.com/webtoon/list.nhn?titleId=" +ID;
		String pattern = "\\/webtoon\\/detail\\.nhn\\?titleId=" + ID +"&no=(\\d*)";

						

		ArrayList<String> idList = Webtoon.findPattern(pattern,url);

		if(idList.isEmpty()){
			System.out.println("There is no Episode");
		}

		lastEpi = Integer.parseInt(idList.get(0));//NumberFormatException
	}	

	static ArrayList<String> findPattern( String patternStr,String url ) throws IOException, MalformedURLException{

		ArrayList<String> dataList = new ArrayList<>();

		String userAgent= "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:17.0) Gecko/20100101 Firefox/17.0";
		System.setProperty("http.agent",userAgent);

		URL myUrl = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection)myUrl.openConnection();

		try(BufferedReader webInput = new BufferedReader(new InputStreamReader(conn.getInputStream()));){

			Pattern pattern = Pattern.compile(patternStr);

			while(true){
				String dataLine = webInput.readLine();

				if(!dataLine.isEmpty()){
					Matcher matcher = pattern.matcher(dataLine);
					if (matcher.find())
					{
						dataList.add((matcher.group(1)));
					}
				}
			}
		} catch (EOFException ex){
			System.out.println("End of stream");
		} finally {
			return dataList;
		}
	}	
	
	public String toString(){
		return String.format("<%s> webtoon %s[%d] last epi: %d",site,name,ID,lastEpi);
	}
}
