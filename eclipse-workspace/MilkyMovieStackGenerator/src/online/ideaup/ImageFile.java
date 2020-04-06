package online.ideaup;

import java.io.File;
import java.util.Date;

public class ImageFile {
	public ImageFile(File f, Date dtOriginal) {
		this.f = f;
		this.dtOriginal = dtOriginal;
	}

	public File f;
	public Date dtOriginal;
}