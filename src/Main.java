import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        GameProgress gameProgress1 = new GameProgress(100, 50, 20, 220.5);
        GameProgress gameProgress2 = new GameProgress(51, 150, 60, 552.6);
        GameProgress gameProgress3 = new GameProgress(15, 2, 100, 854.3);

        saveGame("D://Games//savegames//save1.dat", gameProgress1);
        saveGame("D://Games//savegames//save2.dat", gameProgress2);
        saveGame("D://Games//savegames//save3.dat", gameProgress3);

        ArrayList<String> list = new ArrayList();
        list.add("D://Games//savegames//save1.dat");
        list.add("D://Games//savegames//save2.dat");
        list.add("D://Games//savegames//save3.dat");

        zipFiles("D://Games//savegames//save.zip", list);
    }

    public static void saveGame(String name, GameProgress status) {
        try (FileOutputStream f = new FileOutputStream(name);
             ObjectOutputStream ob = new ObjectOutputStream(f)) {
            ob.writeObject(status);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String name, ArrayList<String> list) {
        try (ZipOutputStream z = new ZipOutputStream(new
                FileOutputStream(name))) {
            for (String saveFile : list) {
                File file = new File(saveFile);
                FileInputStream f = new FileInputStream(file);
                ZipEntry entry = new ZipEntry(file.getName());
                z.putNextEntry(entry);
                byte[] buffer = new byte[f.available()];
                f.read(buffer);
                z.write(buffer);
                z.closeEntry();
                f.close();
                if (file.delete()) {
                    System.out.println("Файл " + saveFile + " удален");
                } else {
                    System.out.println("Файл " + saveFile + " удалить не удалось");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}