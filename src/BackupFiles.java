/**
 * Написать функцию, создающую резервную копию всех файлов в директории(без поддиректорий)
 * во вновь созданную папку ./backup
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BackupFiles {

    public static void main(String[] args) {
        String sourceDirectory = "./src/testdir";

        // Создаем новую папку для резервной копии
        String backupDirectory = "./src/backup";
        File backupDir = new File(backupDirectory);
        backupDir.mkdir();

        // Получаем список файлов в исходной директории
        File sourceDir = new File(sourceDirectory);
        File[] files = sourceDir.listFiles();

        if (files != null) {
            // Копируем каждый файл в новую директорию
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        copyFile(file, new File(backupDirectory + "/" + file.getName()));
                        System.out.println("Скопирован файл: " + file.getName());
                    } catch (IOException e) {
                        System.err.println("Ошибка при копировании файла " + file.getName());
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("Резервная копия завершена.");
        } else {
            System.err.println("Неверный путь к директории.");
        }
    }

    private static void copyFile(File source, File destination) throws IOException {
        Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
