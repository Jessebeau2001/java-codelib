import com.jessebeau.codelib.components.ArgMap;

public class ArgMapTest {
	public static void main(String[] args) {
		var map = new ArgMap("=", args);
		map.ifPresent("hello", value -> System.out.println("Value is " + value));
	}
}
