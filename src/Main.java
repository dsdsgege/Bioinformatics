import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;


public class Main {
    /**
     * This class is to make the output file by giving it the name and the format
     */
    static class OutputMaker {
        private String outputFormat;
        private String outputName;

        public OutputMaker(String outputName, String outputFormat) {
            this.outputFormat = outputFormat;
            this.outputName = outputName;
        }

        public void makeOutput(String out) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputName + "." + outputFormat));
                writer.write(out);
                writer.close();

            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class InputMaker {
        String inputName;
        String inputFormat;

        public InputMaker(String inputName, String inputFormat) {
            this.inputName = inputName;
            this.inputFormat = inputFormat;
        }

        public BufferedReader getReader() {
            try {
                return new BufferedReader(new FileReader(inputName + "." + inputFormat));

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    /**
     * This main method is to solve the problems from Rosalind
     * @param args
     */
    public static void main(String[] args) throws IOException {

        //Counting DNA Nucleotides
        {
            BufferedReader reader = new InputMaker("rosalind_dna", "txt").getReader();
            String input = reader.readLine();
            reader.close();

            String outputString = "";
            System.out.println(input);
            int ACounter = 0, CCounter = 0, GCounter = 0, TCounter = 0;
            for (char ch : input.toCharArray()) {
                switch (ch) {
                    case 'A':
                        ACounter++;
                        break;
                    case 'C':
                        CCounter++;
                        break;
                    case 'G':
                        GCounter++;
                        break;
                    case 'T':
                        TCounter++;
                        break;
                }
            }
            outputString += ACounter + " " + CCounter + " " + GCounter + " " + TCounter;

            OutputMaker outputMaker = new OutputMaker("rosalind_dna_output", "txt");
            outputMaker.makeOutput(outputString);
        }

        //Transcribing DNA into RNA
        {
            BufferedReader reader = new InputMaker("rosalind_rna", "txt").getReader();
            char[] strInArr = reader.readLine().toCharArray();
            reader.close();
            for (int i = 0; i < strInArr.length; i++) {
                if (strInArr[i] == 'T') strInArr[i] = 'U';
            }

            String outputString = new String(strInArr);

            OutputMaker outputMaker = new OutputMaker("rosalind_rna_output", "txt");
            outputMaker.makeOutput(outputString);
        }

        //Complementing a Strand of DNA
        {
            BufferedReader reader = new InputMaker("rosalind_revc", "txt").getReader();
            String inputString = reader.readLine();
            reader.close();
            char[] strInArray = inputString.toCharArray();

            int length = strInArray.length;
            for (int i = 0; i < length/2; i++) {
                char temp = strInArray[i];
                strInArray[i] = strInArray[length-1-i];
                strInArray[length-1-i] = temp;
            }

            for(int i = 0; i < length; i++) {
                char ch = strInArray[i];
                switch (ch) {
                    case 'A':
                        strInArray[i] = 'T';
                        break;
                    case 'T':
                        strInArray[i] = 'A';
                        break;
                    case 'C':
                        strInArray[i] = 'G';
                        break;
                    case 'G':
                        strInArray[i] = 'C';
                        break;
                }
            }

            OutputMaker outputMaker = new OutputMaker("rosalind_revc_output","txt");
            outputMaker.makeOutput(new String(strInArray));
        }

        //Wascally Wabbits
        {
            BufferedReader reader = new InputMaker("rosalind_fib","txt").getReader();
            try {
                String line = reader.readLine();
                reader.close();
                int n = Integer.parseInt(line.split(" ")[0]);
                int k = Integer.parseInt(line.split(" ")[1]);
                System.out.println(n+ " " + k);
                long[] integers = new long[40];
                integers[0] = 1;
                integers[1] = 1;
                int i;
                for(i = 2; i < n; i++) {
                    integers[i] = integers[i-1] + (integers[i-2] * k);
                }

                OutputMaker outputMaker = new OutputMaker("rosalind_fib_out","txt");
                outputMaker.makeOutput(String.valueOf(integers[i-1]) + "\n");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        //Computing GC Content
        {
            HashMap<String, Double> map = new HashMap<>();
            BufferedReader reader = new InputMaker("rosalind_gc","txt").getReader();
            String line;
            String dna = "";
            String id = "";
            while((line = reader.readLine()) != null) {

                if(line.startsWith(">")) {
                    //if it's not the first data and we found a new id,
                    //then we can add our Entry to the map
                    if(id.length() > 0) {
                        map.put(id,  (countGC(dna)/dna.length()*100));
                    }

                    // we have to trim off the '>' from the id
                    id = line.substring(1,line.length());

                    //after we found an id reset the dna string
                    dna = "";
                } else {
                    //dna line
                    dna += line;
                }
            }
            map.put(id,  (countGC(dna)/dna.length()*100));

            double max = 0;
            String maxID = "";
            for(Map.Entry<String, Double> entry : map.entrySet()) {
                if(entry.getValue() > max) {
                    max = entry.getValue();
                    maxID = entry.getKey();
                }
            }

            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("0.000000",symbols);
            String maxInStr = df.format(max);
            OutputMaker outputMaker = new OutputMaker("rosalind_gc_out","txt");
            outputMaker.makeOutput(maxID + "\n" + maxInStr);
        }
    }

    private static double countGC(String str) {
        int sum = 0;
        for (char ch : str.toCharArray()) {
            if(ch == 'G' || ch == 'C') {
                sum++;
            }
        }
        return sum;
    }
}