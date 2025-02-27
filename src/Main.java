import java.io.*;


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
    }
}
