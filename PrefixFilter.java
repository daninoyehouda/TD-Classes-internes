import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PrefixFilter implements DirectoryStream.Filter<Path>{
	private int n;
	public PrefixFilter(int n){
		this.n = n;
	}
	
	@Override
	public boolean accept(Path entry) throws IOException {
		// TODO Auto-generated method stub
		if(Files.size(entry) > n) {
			return true;
		}
		return false;
	}
	
}
