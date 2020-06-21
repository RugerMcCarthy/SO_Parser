package com.company.model;

import com.company.Utils.Utils;

import java.util.ArrayList;

public class ElfType64 {

    public elf64_rel rel;
    public elf64_rela rela;
    public elf64_sym sym;
    public elf64_hdr hdr;
    public elf64_phdr phdr;
    public elf64_shdr shdr;
    public ArrayList<ElfType64.elf64_phdr> phdrList = new ArrayList();
    public ArrayList<ElfType64.elf64_shdr> shdrList = new ArrayList();

    public ElfType64() {
        rel = new elf64_rel();
        rela = new elf64_rela();
        sym = new elf64_sym();
        hdr = new elf64_hdr();
        phdr = new elf64_phdr();
        shdr = new elf64_shdr();
    }

    public class elf64_dynamic {
        public byte[] d_tag; // 8字节
        public byte[] d_val; // 8字节
    }

    public class elf64_rel{
        public byte[] r_offset; // 8字节
        public byte[] r_info; // 8字节
    }

    public class elf64_rela{
        public byte[] r_offset; // 8字节
        public byte[] r_info; // 8字节
        public byte[] r_addend; // 8字节
    }

    public class elf64_sym{
        public byte[] st_name; // 4字节
        public byte[] st_info; // 1字节
        public byte[] st_other; // 1字节
        public byte[] st_shndx; // 2字节
        public byte[] st_value; // 8字节
        public byte[] st_st_size; // 8字节
    }

    public class elf64_hdr{
        public byte[] e_ident; // 16字节
        public byte[] e_type; // 2字节
        public byte[] e_machine; // 2字节
        public byte[] e_version; // 4字节
        public byte[] e_entry; // 8字节
        public byte[] e_phoff; // 8字节
        public byte[] e_shoff; // 8字节
        public byte[] e_flags; // 4字节
        public byte[] e_ehsize; // 2字节
        public byte[] e_phentsize; // 2字节
        public byte[] e_phnum; // 2字节
        public byte[] e_shentsize; // 2字节
        public byte[] e_shnum; // 2字节
        public byte[] e_shstrndx; // 2字节
    }

    public static class elf64_phdr{
        public byte[] p_type; // 4字节
        public byte[] p_flag; // 4字节
        public byte[] p_offset; // 8字节
        public byte[] p_vaddr; // 8字节
        public byte[] p_paddr; // 8字节
        public byte[] p_filesz; // 8字节
        public byte[] p_memsz; // 8字节
        public byte[] p_align; // 8字节
    }

    public static class elf64_shdr{
        public byte[] sh_name; // 4字节
        public byte[] sh_type; // 4字节
        public byte[] sh_flags; // 8字节
        public byte[] sh_addr; // 8字节
        public byte[] sh_offset; // 8字节
        public byte[] sh_size; // 8字节
        public byte[] sh_link; // 4字节
        public byte[] sh_info; // 4字节
        public byte[] sh_addralign; // 8字节
        public byte[] sh_entsize; // 8字节
    }
}
