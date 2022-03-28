import java.util.*;
import java.io.*;

public class MixedNumberAdder {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("data.dat"));


		while (sc.hasNext()) {
			String[] input = sc.nextLine().split(" and ");

			MixedNumber ans = new MixedNumber(input[0]).add(new MixedNumber(input[1]));

			System.out.println(ans);
		}
	}
}