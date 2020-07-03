package com.arouter.jingshuai.javademo.markdown;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.List;

import static com.arouter.jingshuai.javademo.markdown.FileUtil.fileName;

/**
 * Created by jings on 2020/7/3.
 */

public class ExportStrategyOP implements IExportStrategy {
    @Override
    public void excuteExport(List<File> fileList,String exportFileName) {
        if (fileList == null) return;
        File fout = new File(exportFileName);

        try {
            @SuppressWarnings("resource") FileChannel mFileChannel = new FileOutputStream(fout)
                .getChannel();
            FileChannel inFileChannel;
            List<File> files = null;
            StringBuilder folderTitleBuilder = new StringBuilder();
            for (File folder : fileList) {
                if (folder.isDirectory()) {
                    files = Arrays.asList(folder.listFiles());
                    // sortFolder(files);
                    folderTitleBuilder.append("##");
                    folderTitleBuilder.append(folder.getName());
                    folderTitleBuilder.append("\r\n");
                    //每一个目录的title ##
                    mFileChannel.write(ByteBuffer.wrap(folderTitleBuilder.toString().getBytes()));
                    folderTitleBuilder.delete(0,folderTitleBuilder.length());
                    for (File fin : files) {
                        if (fin.getName().endsWith(fileName) && fin.getName().contains(Constraint.MD_TAG_OP) ) {
                            inFileChannel = new FileInputStream(fin).getChannel();
                            inFileChannel.transferTo(0, inFileChannel.size(), mFileChannel);
                            inFileChannel.close();
                            mFileChannel.write(ByteBuffer.wrap("\r\n".getBytes()));
                        }
                    }
                }
                else{
                    if (folder.getName().endsWith(fileName) && folder.getName().contains(Constraint.MD_TAG_OP) ) {
                        inFileChannel = new FileInputStream(folder).getChannel();
                        inFileChannel.transferTo(0, inFileChannel.size(), mFileChannel);
                        inFileChannel.close();
                        mFileChannel.write(ByteBuffer.wrap("\r\n".getBytes()));
                    }
                }
            }
            mFileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
