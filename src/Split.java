import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Split {
  public static void main(String[] args) {
    ArrayList<Double[]> dataset = EDA.bacaDataNormal("normal4");
    Map<Double, List<Double[]>> groupedData = new HashMap<>();

    for (Double[] data : dataset) {
      double label = data[0];
      groupedData.putIfAbsent(label, new ArrayList<>());
      groupedData.get(label).add(data);
    }

    ArrayList<Double[]> trainSet = new ArrayList<>();
    ArrayList<Double[]> testSet = new ArrayList<>();
    Random rand = new Random();
    for (Map.Entry<Double, List<Double[]>> entry : groupedData.entrySet()) {
      List<Double[]> dataList = entry.getValue();
      Collections.shuffle(dataList, rand);
      int splitIndex = (int) (dataList.size() * 0.3);
      trainSet.addAll(dataList.subList(0, splitIndex));
      testSet.addAll(dataList.subList(splitIndex, dataList.size()));
    }
    System.out.println(EDA.simpanFile(trainSet, "dataBaru/train30"));
    System.out.println(EDA.simpanFile(testSet, "dataBaru/test70"));
  }
}
