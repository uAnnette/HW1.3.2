import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public void saveGame(String name, GameProgress status) {
        try (FileOutputStream f = new FileOutputStream(name);
             ObjectOutputStream ob = new ObjectOutputStream(f)) {
            ob.writeObject(status);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void zipFiles(String name, ArrayList<String> list) {
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

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }
}