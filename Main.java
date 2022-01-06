import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Main {

	public static void main(String[] args) throws IOException {
		DirMonitor dir = new DirMonitor(Paths.get("."));
		dir.readDir();
		System.out.println(dir.sizeOfFiles());
		System.out.println(dir.mostRecent());
		
	}
}
