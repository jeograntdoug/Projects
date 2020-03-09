import java.util.ArrayList;
import java.io.*;

import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import java.net.MalformedURLException;

public class WebtoonDownloader {
	ArrayList<String> imageUrlList  = new ArrayList<>();
	Webtoon webtoon;
	int episode;
	String fileExt;

	public WebtoonDownloader (Webtoon webtoon,int episode) throws IllegalArgumentException, UnsupportedEncodingException,IOException  {
		this.webtoon = webtoon;
		setEpisode(episode);
		findImageUrls();
	}

	public void setEpisode(int episode) throws IllegalArgumentException{
		if(episode > webtoon.getLastEpi()){
			throw new IllegalArgumentException("Invalid Episode number");
		}
		this.episode = episode;
	} 
		
	private void findImageUrls()throws UnsupportedEncodingException, IOException{

		String url = "https://comic.naver.com/webtoon/detail.nhn?titleId="+webtoon.getID()+"&no="+episode;
		String pattern = "(https:\\/\\/image-comic\\.pstatic\\.net\\/webtoon\\/"+webtoon.getID()+"\\/" +episode+"\\/.*_\\d+\\.\\w+)\"";

		imageUrlList = Webtoon.findPattern(pattern,url);

		if(imageUrlList.isEmpty()){
			System.out.println("There is no Episode");
		}
	}

	public void download(){
		try{
			if(imageUrlList.isEmpty()){
				System.out.println("There is no episode:"+episode);
			}
	
			String filePath = "./Webtoons/"+webtoon.getName()
								+"/" + episode + "/";
			String fileName = webtoon.getID()+"_" + episode + "_";

			File file = new File(filePath);
			if(!file.mkdirs()){
				throw new IOException("Cannot create directory:"+filePath);
			}

			int count = 0;
			for(String httpsUrl : imageUrlList){

				URL myUrl = new URL(httpsUrl);
				HttpsURLConnection conn = (HttpsURLConnection)myUrl.openConnection();
				try(InputStream in = new BufferedInputStream(conn.getInputStream());
				OutputStream fos = new BufferedOutputStream(new FileOutputStream(filePath+fileName + (count++) +".jpg",true));){
					//FIXME : file extention dynamic

					byte[] buf = new byte[1024];
					int value = 0;

					while (-1!=(value=in.read(buf)))
					{
						   fos.write(buf, 0, value);
					}

				} catch (IOException ex){
					System.out.println(ex.getMessage());
				}
			}
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}

