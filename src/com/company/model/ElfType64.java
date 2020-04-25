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


    /**
     *  typedef struct elf64_rel {
     Elf64_Addr r_offset;	// Location at which to apply the action
     Elf64_Xword r_info;	// index and type of relocation
     } Elf64_Rel;
     */
    public class elf64_rel{
        public long r_offset;
        public long r_info;
    }

    /**
     *  typedef struct elf64_rela {
     Elf64_Addr r_offset;	// Location at which to apply the action
     Elf64_Xword r_info;	// index and type of relocation
     Elf64_Sxword r_addend;	// Constant addend used to compute value
     } Elf64_Rela;
     */
    public class elf64_rela{
        public long r_offset;
        public long r_info;
        public long r_addend;
    }

    /**
     *  typedef struct elf64_sym {
     Elf64_Word st_name;	// Symbol name, index in string tbl
     unsigned char	st_info;	// Type and binding attributes
     unsigned char	st_other;	// No defined meaning, 0
     Elf64_Half st_shndx;	// Associated section index
     Elf64_Addr st_value;	// Value of the symbol
     Elf64_Xword st_size;	// Associated symbol size
     } Elf64_Sym;
     */
    public class elf64_sym{
        public int st_name;
        public char st_info;
        public char st_other;
        public short st_shndx;
        public long st_value;
        public long st_st_size;
    }

    /**
     * typedef struct elf64_hdr {
     unsigned char	e_ident[16];	// ELF "magic number"
     Elf64_Half e_type;
     Elf64_Half e_machine;
     Elf64_Word e_version;
     Elf64_Addr e_entry;	// Entry point virtual address
     Elf64_Off e_phoff;	// Program header table file offset
     Elf64_Off e_shoff;	// Section header table file offset
     Elf64_Word e_flags;
     Elf64_Half e_ehsize;
     Elf64_Half e_phentsize;
     Elf64_Half e_phnum;
     Elf64_Half e_shentsize;
     Elf64_Half e_shnum;
     Elf64_Half e_shstrndx;
     } Elf64_Ehdr;
     */
    public class elf64_hdr{
        public byte[] e_ident = new byte[16];
        public byte[] e_type = new byte[2];
        public byte[] e_machine = new byte[2];
        public byte[] e_version = new byte[4];
        public byte[] e_entry = new byte[8];
        public byte[] e_phoff = new byte[8];
        public byte[] e_shoff = new byte[8];
        public byte[] e_flags = new byte[4];
        public byte[] e_ehsize = new byte[2];
        public byte[] e_phentsize = new byte[2];
        public byte[] e_phnum = new byte[2];
        public byte[] e_shentsize = new byte[2];
        public byte[] e_shnum = new byte[2];
        public byte[] e_shstrndx = new byte[2];
    }

    /**
     *  typedef struct elf64_phdr {
     Elf64_Word p_type;
     Elf64_Word p_flags;
     Elf64_Off p_offset;	// Segment file offset
     Elf64_Addr p_vaddr;	// Segment virtual address
     Elf64_Addr p_paddr;	// Segment physical address
     Elf64_Xword p_filesz;	// Segment size in file
     Elf64_Xword p_memsz;	// Segment size in memory
     Elf64_Xword p_align;	// Segment alignment, file & memory
     } Elf64_Phdr;
     */
    public static class elf64_phdr{
        public byte[] p_type = new byte[4];
        public byte[] p_flag = new byte[4];
        public byte[] p_offset = new byte[8];
        public byte[] p_vaddr = new byte[8];
        public byte[] p_paddr = new byte[8];
        public byte[] p_filesz = new byte[8];
        public byte[] p_memsz = new byte[8];
        public byte[] p_align = new byte[8];

        @Override
        public String toString(){
            return "p_type:"+ Utils.bytes2HexString(p_type)
                    +"\np_flags:"+Utils.bytes2HexString(p_flag)
                    +"\np_offset:"+Utils.bytes2HexString(p_offset)
                    +"\np_vaddr:"+Utils.bytes2HexString(p_vaddr)
                    +"\np_paddr:"+Utils.bytes2HexString(p_paddr)
                    +"\np_filesz:"+Utils.bytes2HexString(p_filesz)
                    +"\np_memsz:"+Utils.bytes2HexString(p_memsz)
                    +"\np_align:"+Utils.bytes2HexString(p_align);
        }
    }


    /**
     * typedef struct elf64_shdr {
     Elf64_Word sh_name;	// Section name, index in string tbl
     Elf64_Word sh_type;	// Type of section
     Elf64_Xword sh_flags;	// Miscellaneous section attributes
     Elf64_Addr sh_addr;	// Section virtual addr at execution
     Elf64_Off sh_offset;	// Section file offset
     Elf64_Xword sh_size;	// Size of section in bytes
     Elf64_Word sh_link;	// Index of another section
     Elf64_Word sh_info;	// Additional section information
     Elf64_Xword sh_addralign;	// Section alignment
     Elf64_Xword sh_entsize;	// Entry size if section holds table
     } Elf64_Shdr;
     */
    public static class elf64_shdr{
        public byte[] sh_name = new byte[4];
        public byte[] sh_type = new byte[4];
        public byte[] sh_flags = new byte[8];
        public byte[] sh_addr = new byte[4];
        public byte[] sh_offset = new byte[4];
        public byte[] sh_size = new byte[8];
        public byte[] sh_link = new byte[4];
        public byte[] sh_info = new byte[4];
        public byte[] sh_addralign = new byte[8];
        public byte[] sh_entsize = new byte[8];


        @Override
        public String toString(){
            return "sh_name:"+Utils.bytes2HexString(sh_name)/*Utils.byte2Int(sh_name)*/
                    +"\nsh_type:"+Utils.bytes2HexString(sh_type)
                    +"\nsh_flags:"+Utils.bytes2HexString(sh_flags)
                    +"\nsh_addr:"+Utils.bytes2HexString(sh_addr)
                    +"\nsh_offset:"+Utils.bytes2HexString(sh_offset)
                    +"\nsh_size:"+Utils.bytes2HexString(sh_size)
                    +"\nsh_link:"+Utils.bytes2HexString(sh_link)
                    +"\nsh_info:"+Utils.bytes2HexString(sh_info)
                    +"\nsh_addralign:"+Utils.bytes2HexString(sh_addralign)
                    +"\nsh_entsize:"+ Utils.bytes2HexString(sh_entsize);
        }
    }

    public void printShdrList(){
        for(int i=0;i<shdrList.size();i++){
            System.out.println();
            System.out.println("The "+(i+1)+" Section Header:");
            System.out.println(shdrList.get(i));
        }
    }


    public void printPhdrList(){
        for(int i=0;i<phdrList.size();i++){
            System.out.println();
            System.out.println("The "+(i+1)+" Program Header:");
            System.out.println(phdrList.get(i).toString());
        }
    }
}
