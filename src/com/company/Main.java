package com.company;

import com.company.Utils.ParseType32Utils;
import com.company.Utils.ParseType64Utils;
import com.company.Utils.Utils;

public class Main {
    byte[] fileByteArys;
    boolean isType32 = false;

    private void parse() {
        fileByteArys = Utils.readFile("src/com/company/so/libJniDemo.so");
        if (fileByteArys == null) {
            System.out.println("读取so文件失败");
            return;
        }
        if (!Utils.isElfFile(fileByteArys)) {
            System.out.println("不是ELF文件");
            return;
        }
        isType32 = Utils.isElf32Bit(fileByteArys);
        if (isType32) {
            ParseType32Utils.parseELFHeader(fileByteArys);
            ParseType32Utils.parseSectionHeaderList(fileByteArys);
            ParseType32Utils.parseProgramHeaderList(fileByteArys);
            ParseType32Utils.parseDynamicSegment(fileByteArys, "Java_com_example_jnidemo_JNIDemo_sayHello");
        } else {
            ParseType64Utils.parseELFHeader(fileByteArys);
            ParseType64Utils.parseSectionHeaderList(fileByteArys);
            ParseType64Utils.parseProgramHeaderList(fileByteArys);
        }
    }

    public static void main(String[] args) {
        new Main().parse();
    }
}
