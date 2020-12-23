package com.ai.spring.sofa.test.mogo;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/6/20
 * @Version 1.0
 **/
public class Base64Util {
    private Base64Util() {
    }

    private static final char[] INT_TO_BASE_64 = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final byte[] BASE_64_TO_INT = new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

    public static byte[] base64ToByteArray(String s) {
        byte[] alphaToInt = BASE_64_TO_INT;
        int sLen = s.length();
        int numGroups = sLen / 4;
        if (4 * numGroups != sLen) {
            throw new IllegalArgumentException("String length must be a multiple of four.");
        }

        int missingBytesInLastGroup = 0;
        int numFullGroups = numGroups;
        if(sLen != 0) {
            if(s.charAt(sLen - 1) == 61) {
                ++missingBytesInLastGroup;
                numFullGroups = numGroups - 1;
            }

            if(s.charAt(sLen - 2) == 61) {
                ++missingBytesInLastGroup;
            }
        }

        byte[] result = new byte[3 * numGroups - missingBytesInLastGroup];
        int inCursor = 0;
        int outCursor = 0;

        int ch0;
        int ch1;
        int ch2;
        for(ch0 = 0; ch0 < numFullGroups; ++ch0) {
            ch1 = base64toInt(s.charAt(inCursor++), alphaToInt);
            ch2 = base64toInt(s.charAt(inCursor++), alphaToInt);
            int ch21 = base64toInt(s.charAt(inCursor++), alphaToInt);
            int ch3 = base64toInt(s.charAt(inCursor++), alphaToInt);
            result[outCursor++] = (byte)(ch1 << 2 | ch2 >> 4);
            result[outCursor++] = (byte)(ch2 << 4 | ch21 >> 2);
            result[outCursor++] = (byte)(ch21 << 6 | ch3);
        }

        if(missingBytesInLastGroup != 0) {
            ch0 = base64toInt(s.charAt(inCursor++), alphaToInt);
            ch1 = base64toInt(s.charAt(inCursor++), alphaToInt);
            result[outCursor++] = (byte)(ch0 << 2 | ch1 >> 4);
            if(missingBytesInLastGroup == 1) {
                ch2 = base64toInt(s.charAt(inCursor++), alphaToInt);
                result[outCursor++] = (byte)(ch1 << 4 | ch2 >> 2);
            }
        }

        return result;

    }

    public static String byteArrayToBase64(byte[] a) {
        int aLen = a.length;
        int numFullGroups = aLen / 3;
        int numBytesInPartialGroup = aLen - 3 * numFullGroups;
        int resultLen = 4 * ((aLen + 2) / 3);
        StringBuilder result = new StringBuilder(resultLen);
        char[] intToAlpha = INT_TO_BASE_64;
        int inCursor = 0;

        int byte0;
        int byte1;
        for(byte0 = 0; byte0 < numFullGroups; ++byte0) {
            byte1 = a[inCursor++] & 255;
            int byte11 = a[inCursor++] & 255;
            int byte2 = a[inCursor++] & 255;
            result.append(intToAlpha[byte1 >> 2]);
            result.append(intToAlpha[byte1 << 4 & 63 | byte11 >> 4]);
            result.append(intToAlpha[byte11 << 2 & 63 | byte2 >> 6]);
            result.append(intToAlpha[byte2 & 63]);
        }

        if(numBytesInPartialGroup != 0) {
            byte0 = a[inCursor++] & 255;
            result.append(intToAlpha[byte0 >> 2]);
            if(numBytesInPartialGroup == 1) {
                result.append(intToAlpha[byte0 << 4 & 63]);
                result.append("==");
            } else {
                byte1 = a[inCursor++] & 255;
                result.append(intToAlpha[byte0 << 4 & 63 | byte1 >> 4]);
                result.append(intToAlpha[byte1 << 2 & 63]);
                result.append('=');
            }
        }

        return result.toString();
    }


    private static int base64toInt(char c, byte[] alphaToInt) {
        byte result = alphaToInt[c];
        if(result < 0) {
            throw new IllegalArgumentException("Illegal character " + c);
        } else {
            return result;
        }
    }
}
