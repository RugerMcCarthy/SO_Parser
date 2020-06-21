package com.company.Utils;

import com.company.model.ElfEnum;
import com.company.model.ElfType32;

public class ParseType32Utils {
    private static ElfType32 elfContent;

    private static int SECTION_HEADER_SIZE = 40;
    private static int PROGRAM_HEADER_SIZE = 32;
    private static int SYMBOL_TABLE_SIZE = 16;

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

    public static void parseDynamicSegment(byte[] fileByteArys, String funcName) {
        boolean hasFoundDynamicSeg = false;
        int dynamicSegOffset = 0;
        int dynamicSegFileSize = 0;
        for (ElfType32.elf32_phdr phdr : elfContent.phdrList) {
            if (Utils.byte2Int(phdr.p_type) == ElfEnum.PT_DYNAMIC) {
                hasFoundDynamicSeg = true;
                dynamicSegOffset = Utils.byte2Int(phdr.p_offset);
                dynamicSegFileSize = Utils.byte2Int(phdr.p_filesz);
            }
        }
        if (!hasFoundDynamicSeg) {
            System.out.println("未找到Dynamic Seg");
            return;
        }
        int sectionSize = 8;
        int symbolTableSize = 16;
        int sectionCount = dynamicSegFileSize / sectionSize;
        for (int i = 0; i < sectionCount; ++i) {
            byte[] dest = new byte[sectionSize];
            System.arraycopy(fileByteArys, i * sectionSize + dynamicSegOffset, dest, 0, sectionSize);
            elfContent.dynamicList.add(getDynamicHeader(dest));
        }

        int dynSymbolOffset = 0;
        int dynStrOffset = 0;
        int dynHashOffset = 0;
        int dynStrSize = 0;
        for (ElfType32.elf32_dynamic dynamic : elfContent.dynamicList) {
            switch (Utils.byte2Int(dynamic.d_tag)) {
                case ElfEnum.DT_SYMTAB:
                    dynSymbolOffset = Utils.byte2Int(dynamic.d_val);
                    break;
                case ElfEnum.DT_STRTAB:
                    dynStrOffset = Utils.byte2Int(dynamic.d_val);
                    break;
                case ElfEnum.DT_HASH:
                    dynHashOffset = Utils.byte2Int(dynamic.d_val);
                    break;
                case ElfEnum.DT_STRSZ:
                    dynStrSize = Utils.byte2Int(dynamic.d_val);
                    break;
            }
        }
        byte[] dynSymbolStringPool = Utils.copyBytes(fileByteArys, dynStrOffset, dynStrSize);
        int nBucket = Utils.byte2Int(Utils.copyBytes(fileByteArys, dynHashOffset, 4));
        int nChain = Utils.byte2Int(Utils.copyBytes(fileByteArys, dynHashOffset + 4, 4));
        int funcHashName = getFuncHashName(funcName) % nBucket;
        int funcIndex = Utils.byte2Int(Utils.copyBytes(fileByteArys, dynHashOffset + (2 + funcHashName) * 4, 4));
        byte[] symbolTable = Utils.copyBytes(fileByteArys, dynSymbolOffset + funcIndex * SYMBOL_TABLE_SIZE, SYMBOL_TABLE_SIZE);
        ElfType32.elf32_sym sym = parseSymbolTable(symbolTable);
        String foundFuncName = getStringFromPool(dynSymbolStringPool, Utils.byte2Int(sym.st_name));
        if (funcName != null && funcName.equals(foundFuncName)) {
            System.out.println("func address has found: " + Utils.byte2Int(sym.st_value));
            return;
        }
        while (true) {
            funcIndex = Utils.byte2Int(Utils.copyBytes(fileByteArys, dynHashOffset + (2 + nBucket + funcIndex) * 4, 4));
            symbolTable = Utils.copyBytes(fileByteArys, dynSymbolOffset + funcIndex * SYMBOL_TABLE_SIZE, SYMBOL_TABLE_SIZE);
            sym = parseSymbolTable(symbolTable);
            foundFuncName = getStringFromPool(dynSymbolStringPool, Utils.byte2Int(sym.st_name));
            if (funcName != null && funcName.equals(foundFuncName)) {
                System.out.println("func address: " + Utils.byte2Int(sym.st_value));
                return;
            }
        }
    }

    public static ElfType32.elf32_sym parseSymbolTable(byte[] data) {
        ElfType32.elf32_sym sym = new ElfType32.elf32_sym();
        sym.st_name = Utils.copyBytes(data, 0, 4);
        sym.st_value = Utils.copyBytes(data, 4, 4);
        sym.st_size = Utils.copyBytes(data, 8, 4);
        sym.st_info = Utils.copyBytes(data, 12, 1);
        sym.st_other = Utils.copyBytes(data, 13 , 1);
        sym.st_shndx = Utils.copyBytes(data, 14, 2);
        return sym;
    }

    public static ElfType32.elf32_dynamic getDynamicHeader(byte[] header) {
        ElfType32.elf32_dynamic dynamic = new ElfType32.elf32_dynamic();
        dynamic.d_tag = Utils.copyBytes(header, 0, 4);
        dynamic.d_val = Utils.copyBytes(header, 4, 4);
        return dynamic;
    }

    private static int getFuncHashName(String name){
        if(name == null || name.length() == 0){
            return -1;
        }
        long h=0, g;
        for(int i=0;i<name.length();i++){
            h = (h << 4) + (int)name.charAt(i);
            g = h & 0xf0000000;
            h ^= g;
            h ^= (g >> 24);
        }
        return (int)h;
    }

    public static String getStringFromPool(byte[] pool, int offset) {
        StringBuilder result = new StringBuilder();
        int length = 0;
        while (pool[offset + length] != 0) {
            result.append((char)pool[offset + length]);
            length++;
        }
        return result.toString();
    }
}
