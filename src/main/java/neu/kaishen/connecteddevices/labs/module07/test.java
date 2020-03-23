package neu.kaishen.connecteddevices.labs.module07;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		String s = "abcdefg";
		System.out.println(s.charAt(0));
		boolean[] used = new boolean[128];
		used[s.charAt(0)] = true;
		System.out.println(used[s.charAt(0)]);

	}

}
