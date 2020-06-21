package com.company.model;

public class ElfEnum {
    /* These constants are for the segment types stored in the image headers */
    public final static int a = 1;
    public final static int PT_NULL = 0;
    public final static int PT_LOAD = 1;
    public final static int PT_DYNAMIC = 2;
    public final static int PT_INTERP = 3;
    public final static int PT_NOTE = 4;
    public final static int PT_SHLIB = 5;
    public final static int PT_PHDR = 6;
    public final static int PT_TLS = 7;             /* Thread local storage segment */
    public final static int PT_LOOS = 0x60000000;     /* OS-specific */
    public final static int PT_HIOS = 0x6fffffff;      /* OS-specific */
    public final static int PT_LOPROC = 0x70000000;
    public final static int PT_HIPROC = 0x7fffffff;
    public final static int PT_GNU_EH_FRAME = 0x6474e550;
    public final static int PT_GNU_STACK = (PT_LOOS + 0x474e551);

    /* These constants define the different elf file types */
    public final static int ET_NONE = 0;
    public final static int ET_REL = 1;
    public final static int ET_EXEC = 2;
    public final static int ET_DYN = 3;
    public final static int ET_CORE = 4;
    public final static int ET_LOPROC = 0xff00;
    public final static int ET_HIPROC = 0xffff;

    /* This is the info that is needed to parse the dynamic section of the file */
    public final static int DT_NULL = 0;
    public final static int DT_NEEDED = 1;
    public final static int DT_PLTRELSZ = 2;
    public final static int DT_PLTGOT = 3;
    public final static int DT_HASH = 4;
    public final static int DT_STRTAB = 5;
    public final static int DT_SYMTAB = 6;
    public final static int DT_RELA = 7;
    public final static int DT_RELASZ = 8;
    public final static int DT_RELAENT = 9;
    public final static int DT_STRSZ = 10;
    public final static int DT_SYMENT = 11;
    public final static int DT_INIT = 12;
    public final static int DT_FINI = 13;
    public final static int DT_SONAME = 14;
    public final static int DT_RPATH = 15;
    public final static int DT_SYMBOLIC = 16;
    public final static int DT_REL = 17;
    public final static int DT_RELSZ = 18;
    public final static int DT_RELENT = 19;
    public final static int DT_PLTREL = 20;
    public final static int DT_DEBUG = 21;
    public final static int DT_TEXTREL = 22;
    public final static int DT_JMPREL = 23;
    public final static int DT_ENCODING = 32;
    public final static int OLD_DT_LOOS = 0x60000000;
    public final static int DT_LOOS = 0x6000000d;
    public final static int DT_HIOS = 0x6ffff000;
    public final static int DT_VALRNGLO = 0x6ffffd00;
    public final static int DT_VALRNGHI = 0x6ffffdff;
    public final static int DT_ADDRRNGLO = 0x6ffffe00;
    public final static int DT_ADDRRNGHI = 0x6ffffeff;
    public final static int DT_VERSYM = 0x6ffffff0;
    public final static int DT_RELACOUNT = 0x6ffffff9;
    public final static int DT_RELCOUNT = 0x6ffffffa;
    public final static int DT_FLAGS_1 = 0x6ffffffb;
    public final static int DT_VERDEF = 0x6ffffffc;
    public final static int DT_VERDEFNUM = 0x6ffffffd;
    public final static int DT_VERNEED = 0x6ffffffe;
    public final static int DT_VERNEEDNUM = 0x6fffffff;
    public final static int OLD_DT_HIOS = 0x6fffffff;
    public final static int DT_LOPROC = 0x70000000;
    public final static int DT_HIPROC = 0x7fffffff;

    /* This info is needed when parsing the symbol table */
    public final static int STB_LOCAL = 0;
    public final static int STB_GLOBAL = 1;
    public final static int STB_WEAK = 2;
    public final static int STT_NOTYPE = 0;
    public final static int STT_OBJECT = 1;
    public final static int STT_FUNC = 2;
    public final static int STT_SECTION = 3;
    public final static int STT_FILE = 4;
    public final static int STT_COMMON = 5;
    public final static int STT_TLS = 6;

    /* sh_type */
    public final static int SHT_NULL = 0;
    public final static int SHT_PROGBITS = 1;
    public final static int SHT_SYMTAB = 2;
    public final static int SHT_STRTAB = 3;
    public final static int SHT_RELA = 4;
    public final static int SHT_HASH = 5;
    public final static int SHT_DYNAMIC = 6;
    public final static int SHT_NOTE = 7;
    public final static int SHT_NOBITS = 8;
    public final static int SHT_REL = 9;
    public final static int SHT_SHLIB = 10;
    public final static int SHT_DYNSYM = 11;
    public final static int SHT_NUM = 12;
    public final static int SHT_LOPROC = 0x70000000;
    public final static int SHT_HIPROC = 0x7fffffff;
    public final static int SHT_LOUSER = 0x80000000;
    public final static int SHT_HIUSER = 0xffffffff;

    /* sh_flags */
    public final static int SHF_WRITE = 0x1;
    public final static int SHF_ALLOC = 0x2;
    public final static int SHF_EXECINSTR = 0x4;
    public final static int SHF_MASKPROC = 0xf0000000;

    /* special section indexes */
    public final static int SHN_UNDEF = 0;
    public final static int SHN_LORESERVE = 0xff00;
    public final static int SHN_LOPROC = 0xff00;
    public final static int SHN_HIPROC = 0xff1f;
    public final static int SHN_ABS = 0xfff1;
    public final static int SHN_COMMON = 0xfff2;
    public final static int SHN_HIRESERVE = 0xffff;
}
