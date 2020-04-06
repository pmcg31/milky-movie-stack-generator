package online.ideaup;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class mmsg implements Runnable {

	public static void main(String[] args) {
		if (args.length == 1) {
			File imageDir = new File(args[0]);
			if (imageDir.exists() && imageDir.isDirectory()) {
				new mmsg(imageDir).run();
			} else {
				System.out.println("'"+ imageDir +"' doesn't exist or is not a directory");
				System.exit(1);
			}
		} else {
			System.out.println("Sorry, Charlie");

		}
	}

	public mmsg(File imageDir) {
		_imageDir = imageDir;
	}
	
	
	@Override
	public void run() {
		File[] files = _imageDir.listFiles();
		ArrayList<ImageFile> images = new ArrayList<ImageFile>();
		
		if (files != null) {
			// Load up all the NEF files in _imageDir
			for (File f : files) {
				if (f.isFile() && f.getName().toLowerCase().endsWith("nef")) {
					images.add(new ImageFile(f, getDateTimeOriginal(f)));
				}
			} 
			
			// Sort by original date from EXIF data
			images.sort(new Comparator<ImageFile>() {
				@Override
				public int compare(ImageFile o1, ImageFile o2) {
					if ((o1.dtOriginal != null) && (o2.dtOriginal != null)) {
						return o1.dtOriginal.compareTo(o2.dtOriginal);
					} else {
						System.out.println("Shit was null!");
						return 0;
					}
				}
			});
			
			int count = images.size();
			ArrayList<DSSStackFile> stacks = new ArrayList<DSSStackFile>();
			for (int i = 0; i < (count - (g_stackSize - 1)); i++) {
				stacks.add(new DSSStackFile(images, i, g_stackSize));
			}
			
			int numStacks = stacks.size();
			if (numStacks != 0) {
				File outDir = new File("./DSS");
				outDir.mkdirs();
				
				for (int i = 0; i < numStacks; i++) {
					String idxText = Integer.toString(i);
					int paddingCount = g_numFieldWidth - idxText.length();
					StringBuffer filename = new StringBuffer();
					filename.append("stack-");
					for (int p = 0; p < paddingCount; p++) {
						filename.append("0");
					}
					filename.append(idxText);
					filename.append(".txt");
					File outFile = new File(outDir, filename.toString());
					stacks.get(i).save(outFile);
				}
			}
		} else {
			System.out.println("file list is null! - "+ _imageDir);
		}

	}

	private Date getDateTimeOriginal(File f) {
		Date dtOriginal = null;
		try {
			Metadata m = ImageMetadataReader.readMetadata(f);
			
			Collection<ExifSubIFDDirectory> dirsToCheck = m.getDirectoriesOfType(ExifSubIFDDirectory.class);
			for (ExifSubIFDDirectory dir : dirsToCheck) {
				if (dir.containsTag(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL)) {
					dtOriginal = dir.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
					break;
				}
			}
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		
		return dtOriginal;
	}
	
	private File _imageDir;
	
	private static final int g_stackSize = 36;
	private static final int g_numFieldWidth = 5;
}
