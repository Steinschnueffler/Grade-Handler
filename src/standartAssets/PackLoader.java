package standartAssets;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import application.Main;

public class PackLoader {

	private PackLoader() {}
	
	public static ZipFile loadStandart(File f) {
		File pack;
		File source = new File(f.getAbsolutePath() + "\\standart-Pack.zip");
		try {
			source.createNewFile();
		} catch (IOException e) {
			Main.workspace.writeException(e);
		}
		try {
			pack = new File(PackLoader.class.getResource("Standart-Pack.zip").toURI());
		} catch (URISyntaxException e) {
			Main.workspace.writeException(e);
			return null;
		}
		try {
			Files.copy(pack.toPath(), source.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			Main.workspace.writeException(e);
		}
		try {
			return new ZipFile(pack);
		} catch (ZipException e) {
			Main.workspace.writeException(e);
		} catch (IOException e) {
			Main.workspace.writeException(e);
		}
		return null;
	}
	
}
