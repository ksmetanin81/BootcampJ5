package com.colvir.homework4_archiver.impl;

import com.colvir.homework4_archiver.ArchiverService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ArchiverServiceImpl implements ArchiverService {

    private void archiveFiles(String filePath, ZipOutputStream zipOutputStream) {

        System.out.println("archiving: " + filePath);
        File file = new File(filePath);
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                archiveFiles(f.getAbsolutePath(), zipOutputStream);
            }
        } else {
            try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
                ZipEntry zipEntry = new ZipEntry(filePath);
                zipOutputStream.putNextEntry(zipEntry);

                byte[] buffer = new byte[fileInputStream.available()];
                fileInputStream.read(buffer);
                zipOutputStream.write(buffer);

                zipOutputStream.closeEntry();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("IO exception");
            }
        }
    }

    @Override
    public String archive(String filePath) {

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(filePath.concat(".zip")))) {
            archiveFiles(filePath, zipOutputStream);
            return filePath.concat(".zip");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO exception");
        }

        return "";
    }
}
