import java.net.URL;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import java.util.Scanner;

public class TTest
{
	public static void main(String[] args) throws Exception {
		String name;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter webtoon name: ");
		name = input.nextLine();

		Webtoon webtoon = new Webtoon(name,"naver");
		WebtoonDownloader downloader = new WebtoonDownloader(webtoon, 1100);
		downloader.download();

	}

}
