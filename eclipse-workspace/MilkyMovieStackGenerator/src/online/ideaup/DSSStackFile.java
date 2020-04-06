package online.ideaup;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DSSStackFile {
	public DSSStackFile(ArrayList<ImageFile> images, int leadIdx, int stackSize) {
		_images = images;
		_leadIdx = leadIdx;
		_stackSize = stackSize;
	}
	
	public boolean save(File f) {
		try {
			Path stackDirPath = Paths.get(f.getCanonicalPath()).getParent();

			PrintWriter out = new PrintWriter(f);

			out.print(g_dssFilePrefix);
			
			int end = _leadIdx + _stackSize;
			for (int i = _leadIdx; i < end; i++) {
				ImageFile image = _images.get(i);
				Path imageFilePath = Paths.get(image.f.getCanonicalPath());
				Path relPath = stackDirPath.relativize(imageFilePath);
				
				String fType = "light";
				if (i == 0) {
					fType = "reflight";
				}
				
				out.println("1	"+ fType +"	"+ relPath);
			}
			
			out.print(g_dssFilePostfix);
			
			out.close();
			
			return true;
		}
		catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private ArrayList<ImageFile> _images;
	private int _leadIdx;
	private int _stackSize;
	
	private static String g_dssFilePrefix = "DSS file list\n" + 
			"CHECKED	TYPE	FILE\n";
	private static String g_dssFilePostfix = "#WS#Software\\DeepSkyStacker\\FitsDDP|BayerPattern=4\n" + 
			"#WS#Software\\DeepSkyStacker\\FitsDDP|BlueScale=1.0000\n" + 
			"#WS#Software\\DeepSkyStacker\\FitsDDP|Brighness=1.0000\n" + 
			"#WS#Software\\DeepSkyStacker\\FitsDDP|DSLR=\n" + 
			"#WS#Software\\DeepSkyStacker\\FitsDDP|FITSisRAW=0\n" + 
			"#WS#Software\\DeepSkyStacker\\FitsDDP|ForceUnsigned=0\n" + 
			"#WS#Software\\DeepSkyStacker\\FitsDDP|Interpolation=Bilinear\n" + 
			"#WS#Software\\DeepSkyStacker\\FitsDDP|RedScale=1.0000\n" + 
			"#WS#Software\\DeepSkyStacker\\RawDDP|AHD=0\n" + 
			"#WS#Software\\DeepSkyStacker\\RawDDP|AutoWB=0\n" + 
			"#WS#Software\\DeepSkyStacker\\RawDDP|BlackPointTo0=1\n" + 
			"#WS#Software\\DeepSkyStacker\\RawDDP|BlueScale=1.0000\n" + 
			"#WS#Software\\DeepSkyStacker\\RawDDP|Brighness=1.0000\n" + 
			"#WS#Software\\DeepSkyStacker\\RawDDP|CameraWB=0\n" + 
			"#WS#Software\\DeepSkyStacker\\RawDDP|Interpolation=Bilinear\n" + 
			"#WS#Software\\DeepSkyStacker\\RawDDP|RawBayer=0\n" + 
			"#WS#Software\\DeepSkyStacker\\RawDDP|RedScale=1.0000\n" + 
			"#WS#Software\\DeepSkyStacker\\RawDDP|SuperPixels=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Register|ApplyMedianFilter=1\n" + 
			"#WS#Software\\DeepSkyStacker\\Register|DetectHotPixels=1\n" + 
			"#WS#Software\\DeepSkyStacker\\Register|DetectionThreshold=9\n" + 
			"#WS#Software\\DeepSkyStacker\\Register|PercentStack=100\n" + 
			"#WS#Software\\DeepSkyStacker\\Register|StackAfter=1\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|AlignChannels=1\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|AlignmentTransformation=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|ApplyFilterToCometImages=1\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|BackgroundCalibration=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|BackgroundCalibrationInterpolation=1\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|BadLinesDetection=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|CometStackingMode=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|CreateIntermediates=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|DarkFactor=1.0000\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|DarkOptimization=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|Dark_Iteration=5\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|Dark_Kappa=2.0000\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|Dark_Method=7\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|Debloom=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|Flat_Iteration=5\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|Flat_Kappa=2.0000\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|Flat_Method=7\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|HotPixelsDetection=1\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|IntermediateFileFormat=1\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|Light_Iteration=5\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|Light_Kappa=2.0000\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|Light_Method=4\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|LockCorners=1\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|Mosaic=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|Offset_Iteration=5\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|Offset_Kappa=2.0000\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|Offset_Method=7\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|PCS_ColdDetection=500\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|PCS_ColdFilter=1\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|PCS_DetectCleanCold=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|PCS_DetectCleanHot=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|PCS_HotDetection=500\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|PCS_HotFilter=1\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|PCS_ReplaceMethod=1\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|PCS_SaveDeltaImage=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|PerChannelBackgroundCalibration=1\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|PixelSizeMultiplier=1\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|RGBBackgroundCalibrationMethod=2\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|SaveCalibrated=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|SaveCalibratedDebayered=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|SaveCometImages=0\n" + 
			"#WS#Software\\DeepSkyStacker\\Stacking|UseDarkFactor=0\n";
}
