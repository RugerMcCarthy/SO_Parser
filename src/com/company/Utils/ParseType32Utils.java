package com.company.Utils;

import com.company.model.ElfType32;

public class ParseType32Utils {
    private static ElfType32 elfContent;

    private static int SECTION_HEADER_SIZE = 40;
    private static int PROGRAM_HEADER_SIZE = 32;

    public static void parseELFHeader(byte[] fileByteArys) {
        if (fileByteArys == null) {
            return;
        }
        elfContent = new ElfType32();
        elfContent.hdr.e_ident = Utils.copyBytes(fileByteArys, 0, 16);
        elfContent.hdr.e_type = Utils.copyBytes(fileByteArys, 16, 2);
        elfContent.hdr.e_machine = Utils.copyBytes(fileByteArys, 18, 2);
        elfContent.hdr.e_version = Utils.copyBytes(fileByteArys, 20, 4);
        elfContent.hdr.e_entry = Utils.copyBytes(fileByteArys, 24, 4);
        elfContent.hdr.e_phoff = Utils.copyBytes(fileByteArys, 28, 4);
        elfContent.hdr.e_shoff = Utils.copyBytes(fileByteArys, 32, 4);
        elfContent.hdr.e_flags = Utils.copyBytes(fileByteArys, 36, 4);
        elfContent.hdr.e_ehsize = Utils.copyBytes(fileByteArys, 40, 2);
        elfContent.hdr.e_phentsize = Utils.copyBytes(fileByteArys, 42, 2);
        elfContent.hdr.e_phnum = Utils.copyBytes(fileByteArys, 44, 2);
        elfContent.hdr.e_shentsize = Utils.copyBytes(fileByteArys, 46, 2);
        elfContent.hdr.e_shnum = Utils.copyBytes(fileByteArys, 48, 2);
        elfContent.hdr.e_shstrndx = Utils.copyBytes(fileByteArys, 50, 2);
    }

    public static void parseSectionHeaderList(byte[] fileByteArys) {
        if (elfContent == null || fileByteArys == null) {
            return;
        }
        short sectionHeaderCount = Utils.byte2Short(elfContent.hdr.e_shnum);
        int sectionHeaderOffset = Utils.byte2Int(elfContent.hdr.e_shoff);
        for (int i = 0; i < sectionHeaderCount; ++i) {
            byte[] dest = new byte[SECTION_HEADER_SIZE];
            System.arraycopy(fileByteArys, i * SECTION_HEADER_SIZE + sectionHeaderOffset, dest, 0, SECTION_HEADER_SIZE);
            elfContent.shdrList.add(getSectionHeader(dest));
        }
    }

    private static ElfType32.elf32_shdr getSectionHeader(byte[] header) {
        ElfType32.elf32_shdr sectionHeader = new ElfType32.elf32_shdr();
        sectionHeader.sh_name = Utils.copyBytes(header, 0, 4);
        sectionHeader.sh_type =  Utils.copyBytes(header, 4, 4);
        sectionHeader.sh_flags =  Utils.copyBytes(header, 8, 4);
        sectionHeader.sh_addr =  Utils.copyBytes(header, 12, 4);
        sectionHeader.sh_offset =  Utils.copyBytes(header, 16, 4);
        sectionHeader.sh_size =  Utils.copyBytes(header, 20, 4);
        sectionHeader.sh_link =  Utils.copyBytes(header, 24, 4);
        sectionHeader.sh_info =  Utils.copyBytes(header, 28, 4);
        sectionHeader.sh_addralign =  Utils.copyBytes(header, 32, 4);
        sectionHeader.sh_entsize =  Utils.copyBytes(header, 36, 4);
        return sectionHeader;
    }

    public static void parseProgramHeaderList(byte[] fileByteArys) {
        if (fileByteArys == null || elfContent == null) {
            return;
        }
        short programHeaderCount = Utils.byte2Short(elfContent.hdr.e_phnum);
        int programHeaderOffset = Utils.byte2Int(elfContent.hdr.e_phoff);
        for (int i = 0; i < programHeaderCount; ++i) {
            byte[] dest = new byte[PROGRAM_HEADER_SIZE];
            System.arraycopy(fileByteArys, i * PROGRAM_HEADER_SIZE + programHeaderOffset, dest, 0, PROGRAM_HEADER_SIZE);
            elfContent.phdrList.add(getProgramHeader(dest));
        }
    }

    private static ElfType32.elf32_phdr getProgramHeader(byte[] header) {
        ElfType32.elf32_phdr programHeader = new ElfType32.elf32_phdr();
        programHeader.p_type = Utils.copyBytes(header, 0, 4);
        programHeader.p_offset =  Utils.copyBytes(header, 4, 4);
        programHeader.p_vaddr =  Utils.copyBytes(header, 8, 4);
        programHeader.p_paddr =  Utils.copyBytes(header, 12, 4);
        programHeader.p_filesz =  Utils.copyBytes(header, 16, 4);
        programHeader.p_memsz =  Utils.copyBytes(header, 20, 4);
        programHeader.p_flags =  Utils.copyBytes(header, 24, 4);
        programHeader.p_align =  Utils.copyBytes(header, 28, 4);
        return programHeader;
    }

    public static void printSo() {
        if (elfContent == null) {
            return;
        }
        System.out.println("+++++++++++++++++ELF Header+++++++++++++++++");
        System.out.println(elfContent.hdr);
        System.out.println();
        System.out.println("+++++++++++++++++Program Header+++++++++++++++++");
        elfContent.printPhdrList();
        System.out.println();
        System.out.println("+++++++++++++++++Section Header+++++++++++++++++");
        elfContent.printShdrList();
        System.out.println();
    }
}
