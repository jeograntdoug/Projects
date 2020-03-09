import java.util.ArrayList;
public class Test {
	public static void main(String[] args){
		ArrayList<Player> list = new ArrayList<>();
		ArrayList<Player> lista = new ArrayList<>();

		for(int i=0; i < 3; i++){
			list.add(new Player("player"+ i));
		}

		lista.add(list.get(0));
		list.remove(list.get(0));
		for(Player player : list){
			System.out.println(player.getName());
		}
		for(Player p : lista){
			System.out.println(p.getName());
		}


	}
}
