package getsize;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.math.*;
import java.io.PrintWriter;
import java.io.*;
import java.util.ArrayList;

public class GetSize {

    public static void main(String[] args) throws Exception {
        double current = 0;
        double currentmb = 0;
        double totalmb = 0;
        ArrayList<String> fSizes = new ArrayList<String>();
        String[] lin = read("L:\\urls.txt"); //input path
        int v = lin.length;
        
            for (int i = 1; i <= v; i++) {
               double progress = i/v;              
               System.out.println("progress: " + progress + "    " + i + " out of " + v);
               current = getSize(lin[i-1]);
               currentmb = current/1000000;
               totalmb = currentmb + totalmb;
               System.out.println("Total Megabytes: " + totalmb + "    Current Filesize: " + currentmb);
               fSizes.add(String.valueOf(currentmb));

            }
            writeToFile(fSizes);
            
        
    }
    
    public static String[] read(String filename) throws Exception {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);
    }
    private static int getSize(String con) throws Exception {
        URL cons = new URL(con);
    HttpURLConnection conn = null;
    try {
        conn = (HttpURLConnection) cons.openConnection();
        conn.setRequestMethod("HEAD");
        conn.getInputStream();
        return conn.getContentLength();
    } catch (IOException e) {
        return -1;
    } finally {
        conn.disconnect();
    }
}


public static void writeToFile(ArrayList<String> input) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileOutputStream("L:\\file.txt"));
            for (int i = 0; i < input.size(); i++) {
                printWriter.println(input.get(i));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }


}