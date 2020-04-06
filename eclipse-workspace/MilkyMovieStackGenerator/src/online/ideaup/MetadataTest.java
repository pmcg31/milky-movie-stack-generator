package online.ideaup;

import java.io.File;
import java.util.Collection;
import java.util.Date;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class MetadataTest {

	public static void main(String[] args) {
		File f = new File(args[0]);
		
		try {
			Metadata m = ImageMetadataReader.readMetadata(f);
			
			Collection<ExifSubIFDDirectory> dirsToCheck = m.getDirectoriesOfType(ExifSubIFDDirectory.class);
			Date dtOriginal = null;
			for (ExifSubIFDDirectory dir : dirsToCheck) {
				if (dir.containsTag(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL)) {
					dtOriginal = dir.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
					break;
				}
			}
			
			System.out.println(f.getName() +" date/time original "+ dtOriginal);
			
			Iterable<Directory> directories = m.getDirectories();
			for (Directory d : directories) {
				System.out.println(d.getName() + " ["+ d.getTagCount() +" tags]");
				Collection<Tag> tags = d.getTags();
				for (Tag t : tags) {
					System.out.println("    "+ t.getTagName() + " ["+ t.getTagTypeHex() +"]");
				}
			}
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
