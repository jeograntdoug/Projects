import java.io.*;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class URLReader{
    public static void main(String[] args) {

		try{
			String httpsUrl = "https://image-comic.pstatic.net/webtoon/119874/1397/20191113232501_12b58534ffe70df72d010a42b1e633ee_IMAG01_1.jpg";
			String userAgent= "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:17.0) Gecko/20100101 Firefox/17.0";

			System.setProperty("http.agent",userAgent);

			URL myUrl = new URL(httpsUrl);
			HttpsURLConnection conn = (HttpsURLConnection)myUrl.openConnection();
			try(InputStream in = new BufferedInputStream(conn.getInputStream());
			OutputStream fos = new BufferedOutputStream(new FileOutputStream("image_bi.jpg",true));){

				byte[] buf = new byte[1024];
				int value = 0;

				while (-1!=(value=in.read(buf)))
				{
					   fos.write(buf, 0, value);
				}

			} catch (IOException ex){
				System.out.println(ex.getMessage());
			}


		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}
