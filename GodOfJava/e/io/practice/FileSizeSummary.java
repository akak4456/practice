package e.io.practice;

import java.io.File;

public class FileSizeSummary {
    public static void main(String[] args) {
        FileSizeSummary sample = new FileSizeSummary();
        String path = "C:\\GodOfJava";
        long sum = sample.printFileSize(path);
        System.out.println(path+"'s total size="+sum);
    }
    public long printFileSize(String dirName) {
        File dir = new File(dirName);
        long sum = 0;
        if(dir.isDirectory()) {
            for(File file: dir.listFiles()) {
                if(file.isDirectory()) {
                    sum += printFileSize(file.getName());
                } else {
                    sum += file.getTotalSpace();
                }
            }
        }
        return sum;
    }
}
