package cryptography;

import java.util.HashMap;
import java.util.Scanner;

public class SPN_Decryption {
    public static void main(String args[])
    {
        //enter in strings in binary format ex: 1000 1010 1000 1010
        // string 1 is plain text
        // string 2 is a key
        //9EBF 2nd
        Scanner scan = new Scanner(System.in);
        String cypherText = scan.nextLine();
        String totalKey = scan.nextLine();
        scan.close();
        HashMap<Integer, Integer> sbox = new HashMap<Integer, Integer>();
        String w1str = "";

        //round one with two keys and sbox permutation
        String key5 = totalKey.substring(16, totalKey.length());

        //XOR with key
        char[] cypherTextArr = new char[cypherText.length()];
        cypherTextArr = cypherText.toCharArray();
        char[] key5Arr = new char[key5.length()];
        key5Arr = key5.toCharArray();
        String w = "";

        //XOR
        for(int i = 0; i < 16; i++){
            if(cypherTextArr[i]== ' ' || key5Arr[i] == ' ') {
                continue;
            }
            else if(cypherTextArr[i] == key5Arr[i]) {
                w += "0";
            }
            else {
                w += "1";
            }

        }

        char [] u1 = w.toCharArray();

        //SBOX with u1 change actual numbers

        //build sbox hashMap reversed


        sbox.put(14, 0);
        sbox.put(4,  1);
        sbox.put(13, 2);
        sbox.put(1, 3);
        sbox.put(2, 4);
        sbox.put(15, 5);
        sbox.put(11, 6);
        sbox.put(8, 7);
        sbox.put(3, 8);
        sbox.put(10, 9);
        sbox.put(6, 10);
        sbox.put(12, 11);
        sbox.put(5, 12);
        sbox.put(9, 13);
        sbox.put(0, 14);
        sbox.put(7, 15);

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
        int character1 = sbox.get(one);
        int character2 = sbox.get(two);
        int character3 = sbox.get(three);
        int character4 = sbox.get(four);

        //convert to binary
        StringBuilder sb = new StringBuilder();
        sb.append(intToBinary(character1));
        sb.append(intToBinary(character2));
        sb.append(intToBinary(character3));
        sb.append(intToBinary(character4));

        String sboxOutput = sb.toString();
        System.out.println("end sbox");
        System.out.println(sboxOutput);


        String key4 = totalKey.substring(12, 28);
        char[] key4Arr = new char[key4.length()];
        key4Arr = key4.toCharArray();
        char[] boxOutput = new char[sboxOutput.length()];
        boxOutput = sboxOutput.toCharArray();
        String w1 = "";

        //XOR
        for(int i = 0; i < 16; i++){
            if(boxOutput[i]== ' ' || key4Arr[i] == ' ') {
                continue;
            }
            else if(boxOutput[i] == key4Arr[i]) {
                w1 += "0";
            }
            else {
                w1 += "1";
            }

        }

        cypherText = w1;
        System.out.println("loop beginning");
        //begin loop for remaining 3 rounds
        int runs = 1;
        String key = "";
        while (runs < 4) {
            //scramble reversed
            char[] w2 = new char[sboxOutput.length()];
            char[] v1 = new char[cypherText.length()];
            v1 = cypherText.toCharArray();

            w2[0] = v1[0];
            w2[4] = v1[1];
            w2[8] = v1[2];
            w2[12] = v1[3];
            w2[1] = v1[4];
            w2[5] = v1[5];
            w2[9] = v1[6];
            w2[13] = v1[7];
            w2[2] = v1[8];
            w2[6] = v1[9];
            w2[10] = v1[10];
            w2[14] = v1[11];
            w2[3] = v1[12];
            w2[7] = v1[13];
            w2[11] = v1[14];
            w2[15] = v1[15];

            StringBuilder what = new StringBuilder();
            for(int i = 0; i < w2.length; i++) {
                what.append(w2[i]);
            }
            w1str = what.toString();

            System.out.println(w1str);

            //sbox
            char[] x1 = new char[w1str.length()];
            x1 = w1str.toCharArray();

            //split x1 and convert to int
            StringBuilder it1 = new StringBuilder();
            for(int i = 0; i < 4; i++) {
                it1.append(x1[i]);

            }
            StringBuilder it2 = new StringBuilder();
            for(int i = 4; i < 8; i++) {
                it2.append(x1[i]);
            }
            StringBuilder it3 = new StringBuilder();
            for(int i = 8; i < 12; i++) {
                it3.append(x1[i]);
            }
            StringBuilder it4 = new StringBuilder();
            for(int i = 12; i < 16; i++){
                it4.append(x1[i]);
            }

            String inter1 = it1.toString();

            //System.out.println(interger1);
            int one1 = binaryStrToInt(inter1);
            //System.out.println(one);
            String inter2 = it2.toString();
            int two2 = binaryStrToInt(inter2);
            String inter3 = it3.toString();
            int three3 = binaryStrToInt(inter3);
            String inter4 = it4.toString();
            int four4 = binaryStrToInt(inter4);

            //access hashmap
            int ch1 = sbox.get(one1);
            int ch2 = sbox.get(two2);
            int ch3 = sbox.get(three3);
            int ch4 = sbox.get(four4);


            //convert to binary
            StringBuilder strb = new StringBuilder();
            strb.append(intToBinary(ch1));
            strb.append(intToBinary(ch2));
            strb.append(intToBinary(ch3));
            strb.append(intToBinary(ch4));

            String bOutput = strb.toString();
            System.out.println("end sbox");
            System.out.println(boxOutput);

            //key xor
            if(runs == 1) {
                key = totalKey.substring(8, 24);
            }
            if (runs == 2) {
                key = totalKey.substring(4, 20);
            }
            if (runs == 3) {
                key = totalKey.substring(0, 16);
            }


            //XOR with key
            char[] out = new char[bOutput.length()];
            out = bOutput.toCharArray();
            char[] keyArr = new char[key.length()];
            keyArr = key.toCharArray();
            String k = "";

            //XOR
            for(int i = 0; i < 16; i++){
                if(out[i]== ' ' || keyArr[i] == ' ') {
                    continue;
                }
                else if(out[i] == keyArr[i]) {
                    k += "0";
                }
                else {
                    k += "1";
                }

            }

            cypherText = k;

            runs++;
        }

        //cypherText to hex


        String finalOne = cypherText.substring(0, 4);
        System.out.println(finalOne);
        String finalTwo = cypherText.substring(4, 8);
        System.out.println(finalTwo);
        String finalThree = cypherText.substring(8, 12);
        System.out.println(finalThree);
        String finalFour = cypherText.substring(12, cypherText.length());
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

        String decrypted = finalStr.toString();
        System.out.println("FINAL:");
        System.out.println(decrypted);

    } //end main

    //helper methods
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
