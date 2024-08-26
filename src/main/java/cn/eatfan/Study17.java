package cn.eatfan;

import java.io.*;

/**
 * Java 字节流与字符流
 */
public class Study17 {
    public static void main(String[] args){
        /*
            字节流
            字节流是以字节为单位进行操作的，用于处理二进制数据。字节流的主要类是 InputStream 和 OutputStream，分别用于输入和输出。
         */
        // 使用字节流读取和写入文件：
        // 定义输入文件和输出文件的位置，该位置就直接位于项目的根目录中的input.txt和output.txt
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // 读取文件的字节流
        try (FileInputStream fis = new FileInputStream(inputFile)) {
            // 写入文件的字节流
            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                int byteData;

                // 逐字节读取和写入
                while ((byteData = fis.read()) != -1) {
                    fos.write(byteData); // 将读取的字节写入目标文件
                }
                // 打印输出日志
                System.out.println("File copied successfully using byte stream.");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        /*
            字符流
            字符流是以字符为单位进行操作的，用于处理文本数据。字符流的主要类是 Reader 和 Writer，分别用于输入和输出字符数据
         */
        String inputFile2 = "input.txt";
        String outputFile2 = "output.txt";

        // 读取文件的字符流
        try (FileReader reader = new FileReader(inputFile)) {
            // 写入文件的字符流
            try (FileWriter writer = new FileWriter(outputFile)) {
                int charData;

                // 逐字符读取和写入
                while ((charData = reader.read()) != -1) {
                    writer.write(charData); // 将读取的字符写入目标文件
                }
                // 打印输出日志
                System.out.println("File copied successfully using character stream.");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        /*
            字节流与字符流的区别：
                1.	处理的数据类型：
	                •	字节流：处理原始的二进制数据，如图片、音频、视频、非文本文件。
	                •	字符流：处理文本数据，适合处理包含字符（如 char 型数据）的文件。
	            2.	类的层次结构：
	                •	字节流：InputStream 和 OutputStream 是字节流的抽象父类。
	                •	字符流：Reader 和 Writer 是字符流的抽象父类。
	            3.	单位：
	                •	字节流：以字节（8位）为单位进行读写操作。
	                •	字符流：以字符（16位或更多，取决于编码）为单位进行读写操作。
	            4.	使用场景：
	                •	字节流：适用于处理所有类型的文件，包括文本文件和二进制文件。特别适合非文本数据。
	                •	字符流：主要用于处理文本文件，如 .txt 文件，处理字符数据和需要编码转换的文件。
         */

        /*
            什么时候使用字节流和字符流
	        1、	使用字节流：
	            •	当你处理二进制文件（如图片、音频、视频）时，使用字节流更合适。
	            •	如果你需要处理文件内容的原始字节数据，而不涉及字符编码。
	        2、	使用字符流：
	            •	当你处理文本文件或需要根据字符编码读写文件时，使用字符流更合适。
        	    •	如果你需要逐字符处理文件内容，并确保字符编码正确。
         */
    }

}
