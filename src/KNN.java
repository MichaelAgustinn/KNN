import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class KNN {
  public static void main(String[] args) {
    ArrayList<Double[]> train = EDA.bacaDataNormal("dataBaru/train80");
    ArrayList<Double[]> test = EDA.bacaDataNormal("dataBaru/test20");
    int k = 8;
    int tp = 0, fn = 0, tn = 0, fp = 0;
    for (int i = 0; i < test.size(); i++) {
      ArrayList<double[]> listTetanggaLabel = new ArrayList<>();
      for (int j = 0; j < train.size(); j++) {
        double label = train.get(j)[0];
        listTetanggaLabel
            .add(new double[] {KNNLagi.euclideanDistance(test.get(i), train.get(j)), label});
      }
      listTetanggaLabel.sort(Comparator.comparingDouble(a -> a[0]));

      HashMap<Double, Integer> jumlahLabel = new HashMap<>();
      for (int n = 0; n < k; n++) {
        double label = listTetanggaLabel.get(n)[1];
        jumlahLabel.put(label, jumlahLabel.getOrDefault(label, 0) + 1);
      }
      // System.out.println(jumlahLabel);
      double prediksi = -1;
      int max = -1;
      for (Map.Entry<Double, Integer> entry : jumlahLabel.entrySet()) {
        if (entry.getValue() > max) {
          max = entry.getValue();
          prediksi = entry.getKey();
        }
      }
      System.out.println("data ke-" + i + " prediksi label = " + prediksi + " | label asli = "
          + listTetanggaLabel.get(i)[1]);

      if (prediksi == 1.0 && test.get(i)[0] == 1.0) {
        tp++;
      } else if (prediksi != 1.0 && test.get(i)[0] == 1.0) {
        fn++;
      } else if (prediksi == 1.0 && test.get(i)[0] != 1.0) {
        fp++;
      } else if (prediksi != 1.0 && test.get(i)[0] != 1.0) {
        tn++;
      }
    }
    System.out.println("K = " + k);
    double total = tp + tn + fp + fn;
    // System.out.println(total);
    double akurasi = (tp + tn) / total;
    double recall = tp / (tp + fn + 0.0);
    double precision = tp / (tp + fp + 0.0);
    double f1 = (2.0 * precision * recall) / (precision + recall);

    System.out.println("Akurasi : " + akurasi * 100 + " %");
    System.out.println("Recall : " + recall * 100 + " %");
    System.out.println("Precision : " + precision * 100 + " %");
    System.out.println("F1 Score : " + f1 * 100 + " %");
  }
}
