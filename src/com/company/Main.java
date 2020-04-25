package com.company;

import com.company.Utils.ParseType32Utils;
import com.company.Utils.ParseType64Utils;
import com.company.Utils.Utils;

public class Main {
    byte[] fileByteArys;
    boolean isType32 = true;

    private void parse() {
        fileByteArys = Utils.readFile("src/com/company/so/libhello-jni.so");
        if (fileByteArys == null) {
            System.out.println("读取so文件失败");
            return;
        }
        if (isType32) {
            ParseType32Utils.parseELFHeader(fileByteArys);
            ParseType32Utils.parseSectionHeaderList(fileByteArys);
            ParseType32Utils.parseProgramHeaderList(fileByteArys);
            ParseType32Utils.printSo();
        } else {
            ParseType64Utils.parseELFHeader(fileByteArys);
            ParseType64Utils.parseSectionHeaderList(fileByteArys);
            ParseType64Utils.parseProgramHeaderList(fileByteArys);
            ParseType64Utils.printSo();
        }
    }

    public static void main(String[] args) {
        new Main().parse();
    }
}
