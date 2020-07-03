package com.arouter.jingshuai.javademo.markdown;

import java.io.File;
import java.util.List;

/**
 * Created by jings on 2020/7/3.
 */

public interface IExportStrategy {
    void excuteExport(List<File> fileList,String exportFileName);
}
