package sample;

public class Name {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("D:/sdasdasda");
		String str="a&b&c";

		String[] split = str.split("&");
		for (String string : split) {
			System.out.println(string);
		}

	}

}
