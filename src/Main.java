import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        GameProgress gameProgress1 = new GameProgress(100, 50, 20, 220.5);
        GameProgress gameProgress2 = new GameProgress(51, 150, 60, 552.6);
        GameProgress gameProgress3 = new GameProgress(15, 2, 100, 854.3);

        gameProgress1.saveGame("D://Games//savegames//save1.dat", gameProgress1);
        gameProgress2.saveGame("D://Games//savegames//save2.dat", gameProgress2);
        gameProgress3.saveGame("D://Games//savegames//save3.dat", gameProgress3);

        ArrayList<String> list = new ArrayList();
        list.add("D://Games//savegames//save1.dat");
        list.add("D://Games//savegames//save2.dat");
        list.add("D://Games//savegames//save3.dat");

        gameProgress1.zipFiles("D://Games//savegames//save.zip", list);
    }
}