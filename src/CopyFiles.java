import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class CopyFiles {
	private String pathProject;
	private String pathExport;
	private List<String> pathFiles;
	private List<String> errorPaths;
	private static int countSuccess;
	
	public CopyFiles(String pathProject, String pathExport, List<String> pathFiles) {
		this.pathProject = pathProject;
		this.pathExport = pathExport;
		this.pathFiles = pathFiles;
		this.countSuccess = 0;
		errorPaths = new ArrayList<>();
	}
	
	/**
	 * Tạo mới file, thư mục
	 * @param pathFile
	 */
	private void createFiles(String pathFile) {
		File file = new File(pathFile);
		if(!file.exists())
			file.getParentFile().mkdirs();
	}
	
	/**
	 * Lấy từng đường dẫn do người dùng nhập
	 * Sau khi thực hiện copy xong sẽ thông báo số file đã copy và đường dẫn file không tồn tại
	 */
	public void analysisPathFiles() {
		try {
			for(String s: pathFiles) {
				copyFile(s);
			}
			StringBuilder str = new StringBuilder();
			str.append("da copy " + countSuccess + " file\n");
			if(errorPaths.size() > 0) {
				str.append("source khong thanh cong\n");
				for(String s: errorPaths) {
					str.append(s);
					str.append("\n");
				}
			}
			new ResultDialog().ShellInfo(str.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Kiểm tra đường dẫn có đúng, đường dẫn chỉ đến đích là file.
	 * Tạo mới file và thực hiện copy
	 * @param paths đường dẫn file
	 * @throws IOException
	 */
	private void copyFile(String paths) throws IOException {
		File file = new File(pathProject + "\\" + paths);
		if(!file.exists()) {
			errorPaths.add(paths);
		} else if(file.isDirectory()) {
			for(File childFile: file.listFiles())
				copyFile(paths + "\\" + childFile.getName().toString());
		} else if(file.isFile()) {
			createFiles(pathExport + "\\" + paths);
			File descFile = new File(pathExport + "\\" + paths);
			copyFileUsingChannel(file, descFile);
			countSuccess++;
		}
	}
	
	/**
	 * Copy dữ liệu
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	private static void copyFileUsingChannel(File source, File dest) throws IOException {
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	    	sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	    } catch(IOException ex) {
	    	ex.printStackTrace();
	    	throw ex;
	    } finally {
	    	sourceChannel.close();
	    	destChannel.close();
	    }
	}
}
