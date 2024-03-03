package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        String target = """ 
                Люди любят выдумывать страшилищ и страхи. Тогда сами себе они кажутся не столь уродливыми и ужасными.
                Напиваясь до белой горячки, обманывая, воруя, исхлёстывая жен вожжами, моря голодом старую бабку,
                четвертуя топорами пойманную в курятнике лису или осыпая стрелами последнего оставшегося на свете единорога,
                они любят думать, что ужаснее и безобразнее их все-таки привидение, которое ходит на заре по хатам.
                Тогда у них легчает на душе. И им проще жить.""";
        try {
            DESAlgorithm des = new DESAlgorithm();
            byte[] key = keyGen();
            System.out.println("Key");
            for (byte b : key) {
                System.out.printf("%02X",b);;
            }
            System.out.println();
            System.out.println("Encrypt: ");
            byte[] result = des.crypt(target.getBytes(), key, "encrypt");
            System.out.println(new String(result));
            System.out.println("Decrypt");
            System.out.println(new String(des.crypt(result, key, "decrypt")));
        } catch(Exception exp) {
            exp.printStackTrace();
        }

    }

    public static byte[] keyGen(){
        byte[] result = new byte[8];
        for(int i =0; i< 8; i++){
            result[i] = (byte) ThreadLocalRandom.current().ints(-128, 128).findAny().orElse(0);
        }
        return result;
    }
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}