package com.company.model;

import com.company.Utils.Utils;

import java.util.ArrayList;

public class ElfType32 {

    public elf32_rel rel;
    public elf32_rela rela;
    public elf32_hdr hdr;
    public ArrayList<elf32_phdr> phdrList = new ArrayList<elf32_phdr>();
    public ArrayList<elf32_shdr> shdrList = new ArrayList<elf32_shdr>();
    public ArrayList<elf32_dynamic> dynamicList = new ArrayList<elf32_dynamic>();
    public ElfType32() {
        rel = new elf32_rel();
        rela = new elf32_rela();
        hdr = new elf32_hdr();
    }

    public static class elf32_dynamic {
        public byte[] d_tag; // 4字节
        public byte[] d_val; // 4字节
    }

    public class elf32_rel {
        public byte[] r_offset; // 4字节
        public byte[] r_info; // 4字节
    }

    public class elf32_rela{
        public byte[] r_offset; // 4字节
        public byte[] r_info; // 4字节
        public byte[] r_addend; // 4字节
    }

    public static class elf32_sym {
        public byte[] st_name; // 4字节
        public byte[] st_value; // 4字节
        public byte[] st_size; // 4字节
        public byte[] st_info; // 1字节
        public byte[] st_other; // 1字节
        public byte[] st_shndx; // 2字节
    }

    public class elf32_hdr{
        public byte[] e_ident; // 16字节
        public byte[] e_type; // 2字节
        public byte[] e_machine; // 2字节
        public byte[] e_version; // 4字节
        public byte[] e_entry; // 4字节
        public byte[] e_phoff; // 4字节
        public byte[] e_shoff; // 4字节
        public byte[] e_flags; // 4字节
        public byte[] e_ehsize; // 2字节
        public byte[] e_phentsize; // 2字节
        public byte[] e_phnum; // 2字节
        public byte[] e_shentsize; // 2字节
        public byte[] e_shnum; // 2字节
        public byte[] e_shstrndx; // 2字节
    }

    public static class elf32_phdr{
        public byte[] p_type; // 4字节
        public byte[] p_offset; // 4字节
        public byte[] p_vaddr; // 4字节
        public byte[] p_paddr; // 4字节
        public byte[] p_filesz; // 4字节
        public byte[] p_memsz; // 4字节
        public byte[] p_flags; // 4字节
        public byte[] p_align; // 4字节
    }

    public static class elf32_shdr{
        public byte[] sh_name; // 4字节
        public byte[] sh_type; // 4字节
        public byte[] sh_flags; // 4字节
        public byte[] sh_addr; // 4字节
        public byte[] sh_offset; // 4字节
        public byte[] sh_size; // 4字节
        public byte[] sh_link; // 4字节
        public byte[] sh_info; // 4字节
        public byte[] sh_addralign; // 4字节
        public byte[] sh_entsize; // 4字节
    }
}