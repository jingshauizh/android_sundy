package com.jingshuai.android.fregmentapp.pattern.patternlist;

import com.jingshuai.android.fregmentapp.pattern.command.CommandPattern;

/**
 * Created by jings on 2020/4/17.
 */

public class PaternTestManager {


    public static void runTest(){
        IActionPattern mPattern = new CommandPattern();
        if(null != mPattern){
            mPattern.excute();
        }
    }

}
