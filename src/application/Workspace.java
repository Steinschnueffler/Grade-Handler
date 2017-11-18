package application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import library.Cryptograph;
import library.TitledMessage;
import schueler.Schueler;
import schueler.SchuelerException;
import standartAssets.PackLoader;

public class Workspace {

	public static final FileSystem FILE_SYSTEM = FileSystems.getDefault();
	
	private final Path root;
	
	private final Path schueler;
	private final Path exceptions;
	private final Path errors;
	private final Path messages;
	private final Path versions;
	private final Path assets;
	
	private final Path standardPackage;
	
	public Image menuButton_hintergrund = new WritableImage(1, 1);
	public Image menuButton_hintergrund_ausgewählt = new WritableImage(1, 1);
	public Image menuButton_hintergrund_gedrückt = new WritableImage(1, 1);
	
	public Image menuButton_einstellungen = new WritableImage(1, 1);
	public Image menuButton_einstellungen_ausgewählt = new WritableImage(1, 1);
	public Image menuButton_einstellungen_gedrückt = new WritableImage(1, 1);
	
	public Workspace(String path) {
		root = FILE_SYSTEM.getPath(path);
		Path logs = root.resolve("logs");		
		
		schueler = root.resolve("schueler");		
		exceptions = logs.resolve("exceptions");		
		errors = logs.resolve("errors");		
		messages = root.resolve("messages");		
		assets = root.resolve("assets");		
		versions = root.resolve("versions");
				
		standardPackage = assets.resolve("standardPack.zip");
		
		try {
			Files.createDirectories(root);
			Files.createDirectories(logs);
			Files.createDirectories(schueler);
			Files.createDirectories(exceptions);
			Files.createDirectories(errors);
			Files.createDirectories(messages);
			Files.createDirectories(assets);
			Files.createDirectories(versions);
			
			Files.createFile(standardPackage);
		}catch(IOException e) {
			return;
		}
		
		InputStream standardPackStream = PackLoader.loadStandart("standardPack.zip");
		try {
			Files.copy(standardPackStream, standardPackage, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			writeException(e);
		}
		
	}
	
	public void saveNewSchueler() throws SchuelerException {
		Path p = schueler.resolve(Main.schueler.getName() + ".schueler");
		if(Files.exists(p)) throw new SchuelerException("Schueler name already exists: " + Main.schueler.getName());
		saveSchueler();
	}
	
	public void saveSchueler(){
		new Thread() {
			
			@Override
			public void run() {
				Path p = schueler.resolve(Main.schueler.getName() + ".schueler");
				try {
					Files.createFile(p);
					
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(bos);
					oos.writeObject(Main.schueler);
					oos.flush();
					bos.flush();
					byte[] coded = Cryptograph.code(bos.toByteArray());
					oos.close();
					bos.close();
					
					Files.write(p, coded, StandardOpenOption.WRITE);
				}catch(Exception e) {
					writeException(e);
				}
			};
		}.start();
	}
	
	public Schueler loadSchueler(String name) throws SchuelerException {
		Path p = schueler.resolve(name + ".schueler");
		if(!Files.exists(p)) throw new SchuelerException("Schueler doesn't exsist: " + p.toString());
		try {
			
			byte[] all = Files.readAllBytes(p);
			
			ByteArrayInputStream bais = new ByteArrayInputStream(Cryptograph.deCode(all));
			ObjectInputStream ois = new ObjectInputStream(bais);
			Schueler s = (Schueler) ois.readObject();
			ois.close();
			bais.close();
			
			return s;
			
		}catch(Exception e) {
			writeException(e);
			throw new SchuelerException("Failed to load " + p.toString());
		}
	}
	
	public void saveMessage(TitledMessage m) {
		new Thread() {
			@Override
			public void run() {
				Path p = messages.resolve(m.title + ".message");
				try {
					Files.write(p, m.text.getBytes(), StandardOpenOption.WRITE);
				} catch (IOException e) {
					writeException(e);
				}
			}
		}.start();
	}

	public void loadTexturePack(FileSystem fs) {

	}
	
	public void writeException(Throwable th){
		writeThrowable(th, exceptions.resolve(getActualTime() + ".exception"));
	}

	public void writeError(Throwable th){
		writeThrowable(th, errors.resolve(getActualTime() + ".error"));;
	}
	
	//static
	
	private static void writeThrowable(Throwable th, Path p) {
		new Thread() {
			
			@Override
			public void run() {
				try {
					Files.createFile(p);
					PrintWriter pw = new PrintWriter(Files.newOutputStream(p));
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
				} catch (IOException e1) {}
			}
		}.start();
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
