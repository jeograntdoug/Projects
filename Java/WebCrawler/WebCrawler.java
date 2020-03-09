import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;

import java.net.MalformedURLException;
import java.util.InputMismatchException;

public class WebCrawler {

	static Scanner input = new Scanner(System.in);
	static ArrayList<Webtoon> webtoonList = new ArrayList<>();

	public static void main(String[] args) throws UnsupportedEncodingException,IOException{
		String message = "";
		loadWebtoonList();
		while(true){
			int choice = getMenuChoice(message);
			message = "";
			String name ="";

			System.out.println();
			switch(choice){
				case 1:
					printAllWebtoon();
					break;
				case 2:
					System.out.println("Enter webtoon name: ");
					name = input.nextLine();
					searchWebtoon(name);
					break;
				case 3:
					System.out.println("Enter webtoon name: ");
					name = input.nextLine();

					findWebtoonInTheList(name);
					break;
				case 4:
					System.out.println("Enter webtoon name: ");
					name = input.nextLine();
					updateWebtoon();//WIP with name parameter
					break;
				case 5:
	//				deleteWebtoon();
					break;
				case 0:
					System.out.println("ByeBye");
					return;
				default:
					message = "Choose one of options";
					continue;
			}
			System.out.print("\nPress Enter...");
			input.nextLine();
		}
	}

	static void printAllWebtoon(){
		int i=1;
		for(Webtoon toon : webtoonList){
			System.out.printf("#%d: %s\n",i++,toon);
		}
	}

	static void findWebtoonInTheList(String name){
		for(Webtoon toon : webtoonList){
			if(toon.getName().equals(name)){
				System.out.println("In the storage:");
				System.out.println(toon);
				return;
			}
		}
		System.out.printf("There is no webtoon[%s] in the storage\n",name);
	}

	static Webtoon searchWebtoon(String name)throws UnsupportedEncodingException,IOException{
		Webtoon webtoon = new Webtoon(name, "naver");


		if(webtoon == null){
			System.out.println("There is no webtoon[%s] on the website");
		}else{
			System.out.println("On the website:");
			System.out.println(webtoon);
		}

		findWebtoonInTheList(name);

		return webtoon;
	}

	static void updateWebtoon() throws UnsupportedEncodingException,IOException{
		if(webtoonList.size() == 0){
			System.out.println("There is no Webtoon");
			return;
		}

		int numToon;
		while(true){
			System.out.println("Enter number: ");
			numToon= inputInt();
			if(numToon < webtoonList.size() && numToon > 0){
				break;
			}
			System.out.println("Wrong number");
			return;
		}

		Webtoon upWebtoon = searchWebtoon(webtoonList.get(numToon-1).getName());
		System.out.println();

		System.out.print("Enter episode you need(ex,123 or 123-125): ");
		
		//WIP : Parse Line method
	}

	static void loadWebtoonList() throws UnsupportedEncodingException,IOException{

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


	}
	
	static int getMenuChoice(String message){
		System.out.println("\f______________________________________");
		System.out.println("l                                    l");
		System.out.println("l       Welcome to Webcrawler        l");
		System.out.println("l                                    l");
		System.out.println("l 1. My Webtoon list                 l");
		System.out.println("l 2. Search webtoon.                 l");
		System.out.println("l 3. Check My webtoon.               l");
		System.out.println("l 4. Update webtoon.                 l");
		System.out.println("l 5. Delete webtoon.                 l");
		System.out.println("l 0. Exit                            l");
		System.out.println("--------------------------------------");
		System.out.printf("%s\n",message);
		  System.out.print(" Enter: ");
		int choice = inputInt();

		return choice;
	}

	static int inputInt(){
		try{
			int choice = input.nextInt();
			input.nextLine();
			return choice;
		}catch (InputMismatchException ex){
			input.nextLine();
			return -1;
		}
	}
}
