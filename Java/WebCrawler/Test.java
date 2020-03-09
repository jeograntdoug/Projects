import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException,IOException{
		ArrayList<Webtoon> webtoonList  = new ArrayList<>();
		String filePath = "./Webtoons/";
		File file = new File(filePath);

		for(String name : file.list()){

			String subPath = filePath + name + "/";
			File subFile = new File(subPath);

			String[] epis = subFile.list();
			if(epis.length == 0){
				continue;
			}

			Arrays.sort(epis);
			String lastEpi = epis[epis.length-1];
			int ID = 0;

			for(int i=epis.length-1; i >= 0 ; i--){
				String imagePath = subPath + lastEpi + "/";
				File imageFile = new File(imagePath);
				String[] images = imageFile.list();

				if(images.length == 0){
					lastEpi = epis[i-1];
					continue;
				}

				String[] data = images[0].split("_");
				ID = Integer.parseInt(data[0]);
				break;
			}
			if(ID == 0){
				continue;
			}

			String site = "naver";		//FIXME : for now

			webtoonList.add(new Webtoon(name,site,ID,Integer.parseInt(lastEpi)));
		}
		for(Webtoon toon : webtoonList){
			System.out.println(toon);
		}
	}
}
