package library;

import java.io.Serializable;

public class TitledMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public final String title;
	public final String text;
	
	public TitledMessage(String title, String text) {
		this.title = title;
		this.text = text;
	}
	
}
