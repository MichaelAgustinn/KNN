import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class KNNLagi {
  public static void main(String[] args) {
    ArrayList<Double[]> train = EDA.bacaDataNormal("dataBaru/train80");
    ArrayList<Double[]> test = EDA.bacaDataNormal("dataBaru/test20");
    int k = 8;
    int tp = 0, fn = 0, tn = 0, fp = 0;
    for (int i = 0; i < test.size(); i++) {
      ArrayList<double[]> listTetanggaLabel = new ArrayList<>();
      for (int j = 0; j < train.size(); j++) {
        double label = train.get(j)[0];
        listTetanggaLabel.add(new double[] {euclideanDistance(test.get(i), train.get(j)), label});
      }
      listTetanggaLabel.sort(Comparator.comparingDouble(a -> a[0]));

      HashMap<Double, Integer> jumlahLabel = new HashMap<>();
      for (int n = 0; n < k; n++) {
        double label = listTetanggaLabel.get(n)[1];
        jumlahLabel.put(label, jumlahLabel.getOrDefault(label, 0) + 1);
      }
      double prediksi = -1;
      int max = -1;
      for (Map.Entry<Double, Integer> entry : jumlahLabel.entrySet()) {
        if (entry.getValue() > max) {
          max = entry.getValue();
          prediksi = entry.getKey();
        }
      }
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
    double total = tp + tn + fp + fn;
    double akurasi = (tp + tn) / total;
    double recall = tp / (tp + fn + 0.0);
    double precision = tp / (tp + fp + 0.0);
    double f1 = (2.0 * precision * recall) / (precision + recall);

    System.out.println("K = " + k);
    System.out.println("Akurasi : " + akurasi * 100 + " %");
    System.out.println("Recall : " + recall * 100 + " %");
    System.out.println("Precision : " + precision * 100 + " %");
    System.out.println("F1 Score : " + f1 * 100 + " %");
  }

  static double euclideanDistance(Double[] test, Double[] train) {
    double hasil = 0;
    for (int i = 1; i < test.length; i++) {
      hasil += Math.pow(train[i] - test[i], 2);
    }
    hasil = Math.sqrt(hasil);
    return hasil;
  }
}
