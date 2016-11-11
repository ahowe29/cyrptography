package cryptography;

import java.util.HashMap;
import java.util.Scanner;

public class SPN {
    public static void main(String args[])
    {
        //enter in strings in binary format ex: 1000 1010 1000 1010
        // string 1 is plain text
        // string 2 is a key
        //9EBF 2nd
        Scanner scan = new Scanner(System.in);
        String plainText = scan.nextLine();
        String totalKey = scan.nextLine();
        scan.close();
        int runs = 1;
        String w1str = "";
        HashMap<Integer, Character> sbox = new HashMap<Integer, Character>();

        while (runs <= 4) {

            String key = "";
            if(runs == 1) {
                key = totalKey.substring(0, 16);
            }
            if (runs == 2) {
                key = totalKey.substring(4, 20);
            }
            if (runs == 3) {
                key = totalKey.substring(8, 24);
            }
            if (runs == 4) {
                key = totalKey.substring(12, 28);
                System.out.println("last round");
            }
       

            //XOR with key
            char[] plainTextArr = new char[plainText.length()];
            plainTextArr = plainText.toCharArray();
            char[] keyArr = new char[key.length()];
            keyArr = key.toCharArray();
            String u1Str = "";

            //XOR
            for(int i = 0; i < 16; i++){
                if(plainTextArr[i]== ' ' || keyArr[i] == ' ') {
                    continue;
                }
                else if(plainTextArr[i] == keyArr[i]) {
                    u1Str += "0";
                }
                else {
                    u1Str += "1";
                }

            }
            System.out.println("end key");
            System.out.println(u1Str);
            char [] u1 = u1Str.toCharArray();

            //SBOX with u1 change actual numbers

            //build sbox hashMap

           
            sbox.put(0, 'E');
            sbox.put(1,  '4');
            sbox.put(2, 'D');
            sbox.put(3, '1');
            sbox.put(4, '2');
            sbox.put(5,  'F');
            sbox.put(6,  'B');
            sbox.put(7, '8');
            sbox.put(8, '3');
            sbox.put(9, 'A');
            sbox.put(10, '6');
            sbox.put(11, 'C');
            sbox.put(12, '5');
            sbox.put(13, '9');
            sbox.put(14, '0');
            sbox.put(15, '7');

            //split u1 and convert to int
            StringBuilder int1 = new StringBuilder();
            for(int i = 0; i < 4; i++) {
                int1.append(u1[i]);

            }
            StringBuilder int2 = new StringBuilder();
            for(int i = 4; i < 8; i++) {
                int2.append(u1[i]);
            }
            StringBuilder int3 = new StringBuilder();
            for(int i = 8; i < 12; i++) {
                int3.append(u1[i]);
            }
            StringBuilder int4 = new StringBuilder();
            for(int i = 12; i < 16; i++){
                int4.append(u1[i]);
            }
            String interger1 = int1.toString();
            //System.out.println(interger1);
            int one = binaryStrToInt(interger1);
            //System.out.println(one);
            String interger2 = int2.toString();
            int two = binaryStrToInt(interger2);
            String integer3 = int3.toString();
            int three = binaryStrToInt(integer3);
            String integer4 = int4.toString();
            int four = binaryStrToInt(integer4);

            //access hashmap
            int character1 = Character.getNumericValue(sbox.get(one));
            int character2 = Character.getNumericValue(sbox.get(two));
            int character3 = Character.getNumericValue(sbox.get(three));
            int character4 = Character.getNumericValue(sbox.get(four));

            //convert to binary
            StringBuilder sb = new StringBuilder();
            sb.append(intToBinary(character1));
            sb.append(intToBinary(character2));
            sb.append(intToBinary(character3));
            sb.append(intToBinary(character4));

            String sboxOutput = sb.toString();
            System.out.println("end sbox");
            System.out.println(sboxOutput);
            char[] v1 = new char[sboxOutput.length()];
            v1 = sboxOutput.toCharArray();
            System.out.println(v1);

            //bit scramble, index permutation
            if(runs != 4) {

            char[] w1 = new char[sboxOutput.length()];

            w1[0] = v1[0];
            w1[1] = v1[4];
            w1[2] = v1[8];
            w1[3] = v1[12];
            w1[4] = v1[1];
            w1[5] = v1[5];
            w1[6] = v1[9];
            w1[7] = v1[13];
            w1[8] = v1[2];
            w1[9] = v1[6];
            w1[10] = v1[10];
            w1[11] = v1[14];
            w1[12] = v1[3];
            w1[13] = v1[7];
            w1[14] = v1[11];
            w1[15] = v1[15];

            StringBuilder what = new StringBuilder();
            for(int i = 0; i < w1.length; i++) {
                what.append(w1[i]);
            }
            w1str = what.toString();
            }
            
            //else skip 4th round of sboxes and do extra key
            else {
                StringBuilder what = new StringBuilder();
                for(int i = 0; i < v1.length; i++) {
                    what.append(v1[i]);
                }
                w1str = what.toString();
                
                String key5 = totalKey.substring(16, totalKey.length());
                System.out.println(key5);
                
                char[] w1strArr = new char[w1str.length()];
                w1strArr = w1str.toCharArray();
                char[] key5Arr = new char[key5.length()];
                key5Arr = key5.toCharArray();
                String w = "";

                //XOR
                for(int i = 0; i < 16; i++){
                    if(w1strArr[i]== ' ' || key5Arr[i] == ' ') {
                        continue;
                    }
                    else if(w1strArr[i] == key5Arr[i]) {
                        w += "0";
                    }
                    else {
                        w += "1";
                    }

                }
                w1str = w;
                System.out.println("end key");
                
            }

            //convert the four binary numbers in w1 to Hex
           
            System.out.println("end scramble");
            System.out.println(w1str);

            plainText = w1str; 
            System.out.println(plainText);
            runs++;
        }


        String finalOne = w1str.substring(0, 4);
        System.out.println(finalOne);
        String finalTwo = w1str.substring(4, 8);
        System.out.println(finalTwo);
        String finalThree = w1str.substring(8, 12);
        System.out.println(finalThree);
        String finalFour = w1str.substring(12, w1str.length());
        System.out.println(finalFour);

        String f1;
        String f2;
        String f3;
        String f4;

        int final1 = binaryStrToInt(finalOne);
        if(final1 >= 10) {
            f1 = convertToHex(final1);
        }
        else {
            f1 = "" + final1;
        }
        int final2 = binaryStrToInt(finalTwo);
        if(final2 >= 10) {
            f2 = convertToHex(final2);
        }
        else {
            f2 = "" + final2;
        }
        int final3 = binaryStrToInt(finalThree);
        if(final3 >= 10) {
            f3 = convertToHex(final3);
        }
        else {
            f3 = "" + final3;
        }
        int final4 = binaryStrToInt(finalFour);
        if(final4 >= 10) {
            f4 = convertToHex(final4);
        }
        else {
            f4 = "" + final4;
        }


        StringBuilder finalStr = new StringBuilder();
        finalStr.append(f1);
        finalStr.append(f2);
        finalStr.append(f3);
        finalStr.append(f4);

        String encrypted = finalStr.toString();
        System.out.println("FINAL:");
        System.out.println(encrypted);


    } //end while and third round of SPN
    
    //fourth round

    public static int binaryStrToInt(String binary) {
        int one = Integer.parseInt(binary, 2);
        return one;
    }

    public static String intToBinary(int next) {
        String ret = Integer.toBinaryString(next);
        if (ret.length() < 4) {
            while(ret.length() < 4){
                ret = "0" + ret;
            }
        }
        return ret;
    }

    /**
     * next is >= 10
     * @param next
     * @return
     */
    public static String convertToHex(int next) {
        String ret = "";
        if(next == 10) {
            ret = "A";
        }
        if(next == 11) {
            ret = "B";
        }
        if(next == 12) {
            ret = "C";
        }
        if(next == 13) {
            ret = "D";
        }
        if(next == 14) {
            ret = "E";
        }
        if(next == 15) {
            ret = "F";
        }
        return ret;

    }

}
