import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class DirMonitor {
	Path path;
    public DirMonitor(Path path) throws IOException {
        if (!Files.isDirectory(path) || !Files.isReadable(path)) {
        	throw new IOException("path is not valide");
        }
        this.path = path;
    }
    
    
    public void readDir() {
    	/*// exo 2.b
    	class PrefixFilter implements DirectoryStream.Filter<Path>{
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
		*/
    	final int n = 1000;
    	try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(path, new DirectoryStream.Filter<Path>(){
    		
	    		@Override
	    		public boolean accept(Path entry) throws IOException {
	    			// TODO Auto-generated method stub
	    			if(Files.size(entry) > n) {
	    				return true;
	    			} 
	    			return false;
	    		}
	    		
	    	});
			for(Path file:stream) {
				 System.out.println(file.getFileName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public long sizeOfFiles() {
    	long sum = 0;
		DirectoryStream<Path> stream;
		try {
			stream = Files.newDirectoryStream(path, "*");
			for(Path file:stream) {
				 sum += Files.size(file);
			}
			return sum;	
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
    }
    public Path mostRecent() {
		DirectoryStream<Path> stream;
		try {
	    	Iterator<Path> it = Files.newDirectoryStream(path, "*" ).iterator();
	    	Path latestFile = it.next();
	    	Path currentFile = latestFile;
			stream = Files.newDirectoryStream(path, "*");
			while(it.hasNext()) {
				currentFile = it.next();
				if(Files.getLastModifiedTime(latestFile).toMillis() < Files.getLastModifiedTime(currentFile).toMillis()) {
					latestFile= currentFile;
				}
			}	
			return currentFile;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
    }
}