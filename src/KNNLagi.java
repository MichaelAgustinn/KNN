import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class KNNLagi {
  public static void main(String[] args) {
    ArrayList<Double[]> train = EDA.bacaDataNormal("dataBaru/train70");
    ArrayList<Double[]> test = EDA.bacaDataNormal("dataBaru/test30");
    int k = 6;
    int tpA = 0, fnA = 0, tnA = 0, fpA = 0;
    int tpB = 0, fnB = 0, tnB = 0, fpB = 0;
    int tpC = 0, fnC = 0, tnC = 0, fpC = 0;
    for (int i = 0; i < test.size(); i++) {
      ArrayList<double[]> listTetanggaLabel = new ArrayList<>();
      for (int j = 0; j < train.size(); j++) {
        double label = train.get(j)[0];
        listTetanggaLabel.add(new double[] {euclideanDistance(test.get(i), train.get(j)), label});
      }
      listTetanggaLabel.sort(Comparator.comparingDouble(a -> a[0])); // namanya -> lambda expression

      HashMap<Double, Integer> jumlahLabel = new HashMap<>();
      for (int n = 0; n < k; n++) {
        double label = listTetanggaLabel.get(n)[1];
        jumlahLabel.put(label, jumlahLabel.getOrDefault(label, 0) + 1); // getOrDefault punya nilai
                                                                        // tapi tidak masuk ke map
      }

      double prediksi = -1; // nilai awal untuk label train, nd ngaruh | nnti akan di update
      int max = -1; // nilai awal juga nnti akan di update
      for (Map.Entry<Double, Integer> entry : jumlahLabel.entrySet()) {
        if (entry.getValue() > max) {
          max = entry.getValue();
          prediksi = entry.getKey();
        }
      }

      if (prediksi == 1.0 && test.get(i)[0] == 1.0) {
        tpA++;
      } else if (prediksi != 1.0 && test.get(i)[0] == 1.0) {
        fnA++;
      } else if (prediksi == 1.0 && test.get(i)[0] != 1.0) {
        fpA++;
      } else if (prediksi != 1.0 && test.get(i)[0] != 1.0) {
        tnA++;
      }

      if (prediksi == 2.0 && test.get(i)[0] == 2.0) {
        tpB++;
      } else if (prediksi != 2.0 && test.get(i)[0] == 2.0) {
        fnB++;
      } else if (prediksi == 2.0 && test.get(i)[0] != 2.0) {
        fpB++;
      } else if (prediksi != 2.0 && test.get(i)[0] != 2.0) {
        tnB++;
      }

      if (prediksi == 3.0 && test.get(i)[0] == 3.0) {
        tpC++;
      } else if (prediksi != 3.0 && test.get(i)[0] == 3.0) {
        fnC++;
      } else if (prediksi == 3.0 && test.get(i)[0] != 3.0) {
        fpC++;
      } else if (prediksi != 3.0 && test.get(i)[0] != 3.0) {
        tnC++;
      }

    }

    int total = tpA + tnA + fpA + fnA;
    System.out.println(total);
    double akurasi = (tpA + tnA + 0.0) / total;

    double recallA = tpA / (tpA + fnA + 0.0);
    double recallB = tpB / (tpB + fnB + 0.0);
    double recallC = tpC / (tpC + fnC + 0.0);
    double precisionA = tpA / (tpA + fpA + 0.0);
    double precisionB = tpB / (tpB + fpB + 0.0);
    double precisionC = tpC / (tpC + fpC + 0.0);
    double fA = (2.0 * precisionA * recallA) / (precisionA + recallA);
    double fB = (2.0 * precisionB * recallB) / (precisionB + recallB);
    double fC = (2.0 * precisionC * recallC) / (precisionC + recallC);

    System.out.println("K = " + k);
    System.out.println("Akurasi : " + akurasi * 100 + " %");
    System.out.println("==============================================");
    System.out.println("Recall kelas Adelie : " + recallA * 100 + " %");
    System.out.println("Precision kelas Adelie : " + precisionA * 100 + " %");
    System.out.println("F1 Score kelas Adelie : " + fA * 100 + " %");
    System.out.println("==============================================");
    System.out.println("Recall kelas Chinstrap : " + recallB * 100 + " %");
    System.out.println("Precision kelas Chinstrap : " + precisionB * 100 + " %");
    System.out.println("F1 Score kelas Chinstrap : " + fB * 100 + " %");
    System.out.println("==============================================");
    System.out.println("Recall kelas Gentoo : " + recallC * 100 + " %");
    System.out.println("Precision kelas Gentoo : " + precisionC * 100 + " %");
    System.out.println("F1 Score kelas Gentoo : " + fC * 100 + " %");
    System.out.println("==============================================");

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
