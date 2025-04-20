import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Double[]> readed = EDA.bacaData("penguins_size");
        for (int i = 0; i < readed.size(); i++) {
            for (int j = 0; j < readed.get(i).length; j++) {
                System.out.print(readed.get(i)[j] + " ");
            }
            System.out.println();
        }
        System.out.println(EDA.shape(readed));
        EDA.printIsi();
    }
}
