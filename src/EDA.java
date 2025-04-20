import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class EDA {
  public static void main(String[] args) {
    // ArrayList<Double[]> readed = bacaData();
    // System.out.println(shape(readed));
    // printIsi();
    isnull();
    // isnull();
    // value_counts();
  }

  static void isnull() {
    ArrayList<String[]> readed = bacaDataAsli();
    int b = 0, c = 0, d = 0, e = 0, f = 0, g = 0;
    for (int i = 0; i < readed.size(); i++) {
      String[] baris = readed.get(i);

      if (baris.length > 1 && baris[1].equalsIgnoreCase("NA")) {
        b++;
      }
      if (baris.length > 2 && baris[2].equalsIgnoreCase("NA")) {
        c++;
      }
      if (baris.length > 3 && baris[3].equalsIgnoreCase("NA")) {
        d++;
      }
      if (baris.length > 4 && baris[4].equalsIgnoreCase("NA")) {
        e++;
      }
      if (baris.length > 5 && baris[5].equalsIgnoreCase("NA")) {
        f++;
      }
      if (baris.length > 6 && baris[6].equalsIgnoreCase("NA")) {
        g++;
      }
    }

    // System.out.println("species\t\t\t\t" + a);
    System.out.println("island\t\t\t" + b);
    System.out.println("culmen_length\t\t" + c);
    System.out.println("culment_depth_mm\t" + d);
    System.out.println("flipper_length_mm\t" + e);
    System.out.println("body_mass_g\t\t" + f);
    System.out.println("sex\t\t\t" + g);
  }

  static void value_counts() {
    ArrayList<Double[]> readed = bacaData("penguins_size");
    int adelie = 0, chinstrap = 0, gentoo = 0;
    for (int i = 0; i < readed.size(); i++) {
      for (int j = 0; j < readed.get(i).length; j++) {
        if (readed.get(i)[j] == 1) {
          adelie++;
        } else if (readed.get(i)[j] == 2) {
          chinstrap++;
        } else if (readed.get(i)[j] == 3) {
          gentoo++;
        }
      }
    }
    System.out.println("Adelie\t\t" + adelie);
    System.out.println("chinstrap\t" + chinstrap);
    System.out.println("gentoo\t\t" + gentoo);
  }

  // ! shape : berapa [baris dan kolom]
  static String shape(ArrayList<Double[]> simpan) {
    ArrayList<Double[]> temp = simpan;
    Integer[] hasil = new Integer[2];
    hasil[0] = temp.size();
    hasil[1] = temp.get(0).length;
    return Arrays.toString(hasil);
  }
  // ! shape : berapa [baris dan kolom] end

  static void printIsi() {
    ArrayList<String[]> simpan = new ArrayList<>();
    simpan = bacaDataAsli();
    for (int i = 0; i < simpan.size(); i++) {
      System.out.println(Arrays.toString(simpan.get(i)));
    }
  }

  static ArrayList<String[]> bacaDataAsli() {
    ArrayList<String[]> simpan = new ArrayList<>();
    String line;
    try (BufferedReader br = new BufferedReader(new FileReader("src/penguins_asli.csv"))) {
      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] baca = line.split(";");
        simpan.add(baca);
      }
    } catch (IOException e) {
      System.out.println("Terjadi kesalahan saat membaca file.");
      e.printStackTrace();
    }
    return simpan;
  }

  static ArrayList<Double[]> bacaData(String namaFile) {
    ArrayList<Double[]> simpan = new ArrayList<>();
    String line;
    try (BufferedReader br = new BufferedReader(new FileReader("src/" + namaFile + ".csv"))) {
      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] baca = line.split(";");
        Double[] data = new Double[baca.length];
        if (baca[0].equalsIgnoreCase("Adelie")) {
          data[0] = 1.0;
        } else if (baca[0].equalsIgnoreCase("Chinstrap")) {
          data[0] = 2.0;
        } else if (baca[0].equalsIgnoreCase("Gentoo")) {
          data[0] = 3.0;
        } else {
          continue;
        }

        if (baca[1].equalsIgnoreCase("torgersen")) {
          data[1] = 1.0;
        } else if (baca[1].equalsIgnoreCase("biscoe")) {
          data[1] = 2.0;
        } else if (baca[1].equalsIgnoreCase("dream")) {
          data[1] = 3.0;
        } else {
          continue;
        }
        if (!baca[2].equalsIgnoreCase("NA")) {
          data[2] = Double.parseDouble(baca[2]);
        } else {
          continue;
        }

        if (!baca[3].equalsIgnoreCase("NA")) {
          data[3] = Double.parseDouble(baca[3]);
        } else {
          continue;
        }

        if (!baca[4].equalsIgnoreCase("NA")) {
          data[4] = Double.parseDouble(baca[4]);
        } else {
          continue;
        }

        if (!baca[5].equalsIgnoreCase("NA")) {
          data[5] = Double.parseDouble(baca[5]);
        } else {
          continue;
        }

        if (baca[6].equalsIgnoreCase("female")) {
          data[6] = 2.0;
        } else if (baca[6].equalsIgnoreCase("male")) {
          data[6] = 1.0;
        } else {
          continue;
        }
        simpan.add(data);
      }
    } catch (IOException e) {
      System.out.println("Terjadi kesalahan saat membaca file.");
      e.printStackTrace();
    }
    return simpan;
  }

  static boolean simpanFile(ArrayList<Double[]> simpan, String namaFile) {
    boolean hasil = false;
    try {
      FileWriter fw = new FileWriter("src/" + namaFile + ".csv");
      BufferedWriter bw = new BufferedWriter(fw);
      bw.append(
          "species;island;culmen_length_mm;culmen_depth_mm;flipper_length_mm;body_mass_g;sex\n");
      for (int i = 0; i < simpan.size(); i++) {
        for (int j = 0; j < simpan.get(i).length; j++) {
          bw.append(String.valueOf(simpan.get(i)[j]));
          bw.append(";");
        }
        bw.newLine();
      }
      bw.close();
      hasil = true;
    } catch (Exception e) {
      hasil = false;
    }
    return hasil;
  }

  static double max(ArrayList<Double[]> simpan, int i) {
    double max = Double.MIN_VALUE;
    for (int j = 0; j < simpan.size(); j++) {
      if (simpan.get(j)[i] > max) {
        max = simpan.get(j)[i];
      }
    }
    return max;
  }

  static double min(ArrayList<Double[]> simpan, int i) {
    double min = Double.MAX_VALUE;
    // for (int i = 0; i < simpan.size(); i++) {
    for (int j = 0; j < simpan.size(); j++) {
      if (simpan.get(j)[i] < min) {
        min = simpan.get(j)[i];
      }
    }
    // }
    return min;
  }

  static ArrayList<Double[]> bacaDataNormal(String namaFile) {
    ArrayList<Double[]> simpan = new ArrayList<>();
    String line;
    try (BufferedReader br = new BufferedReader(new FileReader("src/" + namaFile + ".csv"))) {
      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] baca = line.split(";");
        Double[] data = new Double[baca.length];
        for (int i = 0; i < data.length; i++) {
          data[i] = Double.parseDouble(baca[i]);
        }
        simpan.add(data);
      }
    } catch (IOException e) {
      System.out.println("Terjadi kesalahan saat membaca file.");
      e.printStackTrace();
    }
    return simpan;
  }
}
