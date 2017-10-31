package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import library.TitledMessage;
import schueler.Schueler;
import schueler.SchuelerException;

public class Workspace {

	private final File root;
	
	private final File schueler;
	private final File standartPack;
	private final File exceptions;
	private final File errors;
	private final File messages;
	private final File versions;
	
	public Workspace(String path) {
		root = new File(path);
		
		schueler = new File(root.getAbsolutePath() + "\\schueler");
		schueler.mkdirs();
		
		standartPack = new File(root.getAbsolutePath() + "\\schueler");
		standartPack.mkdirs();
		
		exceptions = new File(root.getAbsolutePath() + "\\logs\\exceptions");
		exceptions.mkdirs();
		
		errors = new File(root.getAbsolutePath() + "\\logs\\errors");
		errors.mkdirs();
		
		messages = new File(root.getAbsolutePath() + "\\messages");
		messages.mkdirs();
		
		versions = new File(root.getAbsolutePath() + "\\versions");
		versions.mkdirs();
		
	}
	
	public void saveSchueler(Schueler s) throws SchuelerException{
		File f = new File(schueler.getAbsolutePath() + "\\" + s.getName() + ".schueler");
		try {
			if(f.exists()) f.delete();
			f.createNewFile();
			
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(s);
			oos.flush();
			oos.close();
			fos.flush();
			fos.close();
		}catch(Exception e) {
			writeException(e);
			throw new SchuelerException("Failed to save schueler: " +e.getLocalizedMessage());
		}
	}
	
	public Schueler loadSchueler(String name) throws SchuelerException {
		File f = new File(schueler.getAbsolutePath() + "\\" + name + ".schueler");
		if(!f.exists()) throw new SchuelerException("Schueler doesn't exsists: " + f.getAbsolutePath());
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Schueler s = (Schueler) ois.readObject();
			ois.close();
			fis.close();
			return s;
		}catch(Exception e) {
			writeException(e);
			throw new SchuelerException("Failed to load " + f.getAbsolutePath());
		}
	}
	
	public boolean saveMessage(TitledMessage m) {
		File f = new File(messages.getAbsolutePath() + "\\" + m.title + ".message");
		try {
			f.createNewFile();
			FileWriter fw = new FileWriter(f);
			fw.write(m.text);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}


	public void writeException(Throwable th){
		writeThrowable(th, new File(exceptions.getAbsolutePath() + "\\" + getActualTime() + ".exception"));
	}

	public void writeError(Throwable th){
		writeThrowable(th, new File(errors.getAbsolutePath() + "\\" + getActualTime() + ".error"));
	}
	
	//static
	
	private static void writeThrowable(Throwable th, File f) {
		try {
			f.createNewFile();
		} catch (IOException e1) {
			return;
		}
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		pw.println(th.getClass().getSimpleName() +" caused at " + getActualTime());
		pw.println();
		pw.println();
		pw.println("Message: " + th.getMessage());
		pw.println("Localized Message: " +th.getLocalizedMessage());
		pw.println("Cause: " +(th.getCause() == null ? "unbekannt" : th.getCause()));
		pw.println("String representation: " + th.toString());
		pw.println("Class infos: " + th.getClass().toString());
		pw.println();
		pw.println();
		pw.println("Stacktrace:");
		pw.println();
		th.printStackTrace(pw);
		pw.println();
		pw.println();
		pw.println("Supressed Throwables:");
		pw.println();
		for(Throwable t : th.getSuppressed()){
			t.printStackTrace(pw);
			pw.println();
		}
		pw.flush();
		pw.close();
	}
	
	private static String getActualTime(){
		LocalDateTime ld = LocalDateTime.now();
		return
			ld.getDayOfMonth()+
			"."+
			ld.getMonthValue()+
			"."+
			ld.getYear()+
			"-"+
			ld.getHour()+
			"."+
			ld.getMinute()+
			"."+
			ld.getSecond();
	}
	
	public static Workspace getDefault() {
		return new Workspace(".\\.nm");
	}
	
}
