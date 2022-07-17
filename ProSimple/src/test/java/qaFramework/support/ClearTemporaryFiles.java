package qaFramework.support;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import org.apache.commons.io.FileUtils;

public class ClearTemporaryFiles {



	public void deleteTempFiles() throws Exception{
		{
			try {

				File tempDir = new File(System.getProperty("java.io.tmpdir"));
				System.out.println("Temp File Path: " + tempDir);
				FileUtils.cleanDirectory(tempDir);
			}
			catch  ( IOException e ) { throw new UncheckedIOException(e); }
		} ;
	}
}


