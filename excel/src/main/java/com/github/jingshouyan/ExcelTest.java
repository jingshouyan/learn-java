package com.github.jingshouyan;

import com.Ostermiller.util.CircularByteBuffer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExcelTest {

    public static final int LINE_NUMBER = 1000000;

    public static final ExecutorService execSev = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        String[] title = { "id", "name", "sex" };
        // 创建一个工作簿
        Workbook workbook = new SXSSFWorkbook();

        // 创建一个工作表sheet
        Sheet sheet = workbook.createSheet();
        // 创建第一行
        Row row = sheet.createRow(0);
        // 创建一个单元格
        Cell cell = null;
        // 创建表头
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        // 从第二行开始追加数据
        for (int i = 1; i <= LINE_NUMBER; i++) {
            // 创建第i行
            Row nextRow = sheet.createRow(i);
            // 参数代表第几列
            Cell cell2 = nextRow.createCell(0);
            cell2.setCellValue("a" + i);
            cell2 = nextRow.createCell(1);
            cell2.setCellValue("user" + i);
            cell2 = nextRow.createCell(2);
            cell2.setCellValue("男");
        }
//        POIFSFileSystem fs = new POIFSFileSystem();
//        EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
//// final EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile, CipherAlgorithm.aes256, HashAlgorithm.sha256, -1, -1, null);
//        Encryptor enc = info.getEncryptor();
//        enc.confirmPassword("123456");
//
//        OutputStream os = enc.getDataStream(fs);
//        workbook.write(os);
//        FileOutputStream fos = new FileOutputStream("d:/tt1.xlsx");
//        fs.writeFilesystem(fos);
//        fos.close();
//        os.close();

        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        execSev.execute(()->{
            try {
                workbook.write(out);
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try (POIFSFileSystem fs = new POIFSFileSystem()) {
            // 创建加密信息
            EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
            // EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile, CipherAlgorithm.aes192, HashAlgorithm.sha384, -1, -1, null);
            Encryptor enc = info.getEncryptor();
            enc.confirmPassword("123456");
            // Read in an existing OOXML file and write to encrypted output stream
            // don't forget to close the output stream otherwise the padding bytes aren't added
            // 一定要提前将opc输出流关闭！！！

            try (OPCPackage opc = OPCPackage.open(in);
                 OutputStream os = enc.getDataStream(fs)) {
                opc.save(os);
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            // 写文件
            try (FileOutputStream fos = new FileOutputStream("d:\\tt1.xlsx")) {
                fs.writeFilesystem(fos);
            }
            System.out.println("DONE");
        }

    }
}
