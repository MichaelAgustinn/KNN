import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class Normalisasi {
  public static void main(String[] args) {
    ArrayList<Double[]> readed = normalisasi(EDA.bacaData("penguins_size"));
    EDA.simpanFile(readed, "normal4");
  }

  static ArrayList<Double[]> normalisasi(ArrayList<Double[]> readed) {
    ArrayList<Double[]> hasil = readed;
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
    DecimalFormat df = new DecimalFormat("#.###", symbols);
    for (int i = 5; i <= 5; i++) {
      for (int j = 0; j < hasil.size(); j++) {
        double normal = hasil.get(j)[i];
        hasil.get(j)[i] = Double.parseDouble(df.format(normal));
      }
    }
    return hasil;
  }

  static double RumusNormalisasi(double vi, double min, double max) {
    double hasil = ((vi - min) / (max - min));
    return hasil;
  }
}
