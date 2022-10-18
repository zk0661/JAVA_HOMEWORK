import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        AppGui appGui = new AppGui();
        FileReader fileReader = new FileReader("lib/Student.txt");
        System.out.println(fileReader.toString());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str = new String();
        str = bufferedReader.readLine();
        String[] stt = str.split(" ");
        for (int i = 0; i < stt.length; i++) {
            System.out.println(stt[i]);
            System.out.println(Integer.parseInt(stt[4])+1);
        }
        bufferedReader.close();
        fileReader.close();

    }

}
