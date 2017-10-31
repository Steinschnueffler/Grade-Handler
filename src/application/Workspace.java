package application;

import java.io.File;

public class Workspace {

	private final File root;
	
	public Workspace(String path) {
		root = new File(path);
		root.mkdirs();
	}
	
	//static
	
	public static Workspace getDefault() {
		return new Workspace(".\\.nm");
	}
	
}
