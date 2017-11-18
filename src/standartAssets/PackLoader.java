package standartAssets;

import java.io.InputStream;

public class PackLoader {

	private PackLoader() {}
	
	public static InputStream loadStandart(String name){
		return PackLoader.class.getResourceAsStream(name);
	}
	
}
