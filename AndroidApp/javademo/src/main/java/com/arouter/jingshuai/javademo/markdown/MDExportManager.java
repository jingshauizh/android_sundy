package com.arouter.jingshuai.javademo.markdown;

import android.text.TextUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jings on 2020/7/3.
 */

public class MDExportManager {
    private IExportStrategy mExportStrategy;
    private String filePath;
    private String exportFileName ;

    public MDExportManager(IExportStrategy mExportStrategy) {
        this.mExportStrategy = mExportStrategy;
    }

    public MDExportManager(IExportStrategy mExportStrategy,String exportFileName) {
        this.mExportStrategy = mExportStrategy;
        this.exportFileName = exportFileName;
    }

    public void excuteExportFile(String filePath){
        try {
            this.filePath = filePath;
            if(!FileUtil.fileExist(filePath)){
               return ;
            }
            List<File> filefolders = FileUtil.getFiles(filePath);
            if(null == exportFileName || exportFileName.length() == 0){
                exportFileName = generateExportFileName(filePath);
            }
            mExportStrategy.excuteExport(filefolders,exportFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateExportFileName(String filePath){
        SimpleDateFormat fd = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowTime = fd.format(new Date());
        return filePath + File.separator + nowTime + FileUtil.fileName;
    }

    public static void main(String [] args){
        System.out.println("11111111");
        String filepath = "I:\\1mat\\优化";
        MDExportManager tMDExportManager = new MDExportManager(new ExportStrategyOP());
        tMDExportManager.excuteExportFile(filepath);
    }


}
