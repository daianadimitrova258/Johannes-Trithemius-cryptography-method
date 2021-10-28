package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static Character[] mainCharArray = new Character[] {'a', 'b', 'c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Make your choice -> Press '1' for encryption or '2' for decryption: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice){
            case 1:
                encrypt();
                break;
            case 2:
                decrypt();
                break;
            default:
                scanner.close();
                break;
        }
        scanner.close();
    }

    public static void encrypt(){
        System.out.println("Write text for encryption: ");
        String msgForCrypt = scanner.nextLine();
        String normalizedMsg = msgForCrypt.replaceAll("\\s", "").toLowerCase(Locale.ROOT);
        char[] charMsg = charArrayMaker(normalizedMsg);

        System.out.println("Enter key: ");
        String keyWord = scanner.nextLine();
        String normalizedKeyWord = keyWord.replaceAll("\\s", "").toLowerCase(Locale.ROOT);
        char[] charKeyWord = charArrayMaker(normalizedKeyWord);

        Character[] keyArray = appendKeyWord(charKeyWord, normalizedMsg.length());
        StringBuilder resultWord = new StringBuilder();
        for(int i = 0; i<normalizedMsg.length(); i++){
            int firstVal = getNumberByChar(charMsg[i]);
            int secondVal = getNumberByChar(keyArray[i]);
            resultWord.append(getCharByNumber(firstVal+secondVal));
        }

        System.out.println("The encrypted message is: " + resultWord);
    }

    public static void decrypt() {
        System.out.println("Write text for decryption: ");
        String msgForDecrypt = scanner.nextLine();
        String normalizedDecMsg = msgForDecrypt.replaceAll("\\s", "").toLowerCase(Locale.ROOT);

        System.out.println("Enter key: ");
        String keyWord = scanner.nextLine();
        String normalizedKeyWord = keyWord.replaceAll("\\s", "").toLowerCase(Locale.ROOT);
        char[] charKeyWord = charArrayMaker(normalizedKeyWord);

        Character[] keyArray = appendKeyWord(charKeyWord, normalizedDecMsg.length());
        System.out.println("The decrypted message is: " + decryptWord(keyArray, normalizedDecMsg));
    }

    public static char getCharByNumber(int position) {
        int length = mainCharArray.length;
        if(position<=length) {
            return  mainCharArray[position - 1];
        } else {
            if (position % length == 0)
                return mainCharArray[25];
            else {
                int newPosition = position % length - 1;
                return mainCharArray[newPosition];
            }
        }
    }

    public static int getNumberByChar(char ch){
        int res = -1;
        for(int i = 0; i<mainCharArray.length; i++){
            if (mainCharArray[i] == ch) {
                res = i + 1;
                break;
            }
        }
        return res;
    }

    public static String decryptWord(Character[] key, String crypt){
        StringBuilder result = new StringBuilder();
        int length = crypt.length();
        char[] cryptWord = charArrayMaker(crypt);
        for(int i = 0; i<length; i++){
            int sub = cryptWord[i] - key[i];
            if(sub>0) {
                result.append(getCharByNumber(sub));
            } else {
                int subSum = sub + 26;
                result.append(getCharByNumber(subSum));
            }
        }
        return result.toString();
    }

    public static char[] charArrayMaker(String word) {
        int length = word.length();
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = word.charAt(i);
        }
        return result;
    }

    public static Character[] appendKeyWord(char[] charKeyWord, int length) {
        Character[] result = new Character[length];
        List<Character> keyList = new ArrayList<>();
        while (keyList.size() < length) {
            for (int i = 0; i < charKeyWord.length; i++) {
                keyList.add(charKeyWord[i]);
                if (keyList.size() == length)
                    break;
            }
        }
        return keyList.toArray(result);
    }
}
