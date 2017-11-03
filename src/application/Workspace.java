package application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import library.Cryptograph;
import library.TitledMessage;
import schueler.Schueler;
import schueler.SchuelerException;
import standartAssets.PackLoader;

public class Workspace {

	private final File root;
	
	private final File schueler;
	private final File standartPack;
	private final File exceptions;
	private final File errors;
	private final File messages;
	private final File versions;
	private final File assets;
	
	public Image menuButton_hintergrund = new WritableImage(1, 1);
	public Image menuButton_hintergrund_ausgewählt = new WritableImage(1, 1);
	public Image menuButton_hintergrund_gedrückt = new WritableImage(1, 1);
	
	public Image menuButton_einstellungen = new WritableImage(1, 1);
	public Image menuButton_einstellungen_ausgewählt = new WritableImage(1, 1);
	public Image menuButton_einstellungen_gedrückt = new WritableImage(1, 1);
	
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
		
		assets = new File(root.getAbsolutePath() +"\\assets");
		assets.mkdirs();
		
		versions = new File(root.getAbsolutePath() + "\\versions");
		versions.mkdirs();
		
		
		PackLoader.loadStandart(assets);
		
		try {
			loadTexturePack(new ZipFile(assets.getAbsolutePath() + "\\standart-Pack.zip", Charset.forName("ISO-8859-1")));
		} catch (IOException e) {
			writeException(e);
		}
	}
	
	public void saveNewSchueler() throws SchuelerException {
		File f = new File(schueler.getAbsolutePath() + "\\" + Main.schueler.getName() + ".schueler");
		if(f.exists()) throw new SchuelerException("Schueler name already exists: " + Main.schueler.getName());
		saveSchueler();
	}
	
	public void saveSchueler() throws SchuelerException{
		File f = new File(schueler.getAbsolutePath() + "\\" + Main.schueler.getName() + ".schueler");
		try {
			f.createNewFile();
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(Main.schueler);
			oos.flush();
			bos.flush();
			byte[] coded = Cryptograph.code(bos.toByteArray());
			oos.close();
			bos.close();
			
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(coded);
			fos.flush();
			fos.close();
		}catch(Exception e) {
			writeException(e);
			throw new SchuelerException("Failed to save schueler: " +e.getLocalizedMessage());
		}
	}
	
	public Schueler loadSchueler(String name) throws SchuelerException {
		File f = new File(schueler.getAbsolutePath() + "\\" + name + ".schueler");
		if(!f.exists()) throw new SchuelerException("Schueler doesn't exsist: " + f.getAbsolutePath());
		try {
			FileInputStream fis = new FileInputStream(f);
			byte[] coded = fis.readAllBytes();
			fis.close();
			
			ByteArrayInputStream bais = new ByteArrayInputStream(Cryptograph.deCode(coded));
			ObjectInputStream ois = new ObjectInputStream(bais);
			Schueler s = (Schueler) ois.readObject();
			ois.close();
			bais.close();
			
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

	public void loadTexturePack(ZipFile zf) {
		Enumeration<? extends ZipEntry> enu = zf.entries();
		while(enu.hasMoreElements()) {
			ZipEntry ze = enu.nextElement();
			InputStream is;
			try {
				is = zf.getInputStream(ze);
			} catch (IOException e) {
				writeException(e);
				continue;
			}
						
			if(ze.getName().equalsIgnoreCase("menuButton_hintergrund.png")) {
				menuButton_hintergrund = new Image(is);
			}else if(ze.getName().equalsIgnoreCase("menuButton_hintergrund_ausgewaehlt.png")) {
				menuButton_hintergrund_ausgewählt = new Image(is);
			}else if(ze.getName().equalsIgnoreCase("menuButton_hintergrund_gedrueckt.png")) {
				menuButton_hintergrund_gedrückt = new Image(is);
			}
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
