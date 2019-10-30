package com.rk.commonmodule.utils;

import android.util.Log;

import java.util.ArrayList;

public class DataConvertUtils {
    private static final String TAG = DataConvertUtils.class.getSimpleName();

    public static final ArrayList<Byte> convertHexStringToByteArray(String hexString, int length, boolean revertTo) {
        Log.i(TAG, "convertHexStringToByteArray, hex string: " + hexString + ", length: " + length);
        ArrayList<Byte> byteArray = new ArrayList<>();
        if (hexString == null || hexString.trim().equals("")) {
            Log.i(TAG, "convertHexStringToByteArray, no hex string data");
            return null;
        }
        if (length <= 0 || length % 2 != 0) {
            Log.i(TAG, "convertHexStringToByteArray, this length value is not suitable");
            return null;
        }
        if (hexString.length() > length) {
            Log.i(TAG, "convertHexStringToByteArray, hex string length is too longer");
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(hexString);
        for (int i = 0; i < length - hexString.length(); i++) {
             sb.insert(0, "0");
        }
        String rawHexData = sb.toString();
        Log.i(TAG, "convertHexStringToByteArray, hex data: " + rawHexData);

        for (int j = 0; j < length / 2; j++) {
            String item = rawHexData.substring(j * 2, j * 2 + 2);
            if (revertTo) {
                byteArray.add(0, (byte) Integer.parseInt(item, 16));
            } else {
                byteArray.add((byte) Integer.parseInt(item, 16));
            }
        }
        return byteArray;
    }

    public static final String convertByteArrayToString(byte[] data, boolean revertTo) {
        Log.i(TAG, "convertByteArrayToString");
        if (data == null || data.length <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            String item = Integer.toHexString(data[i] & 0xFF);
            if (item == null || item.trim().equals("")) {
                Log.i(TAG, "convertByteArrayToString, data error");
                return null;
            }

            if (item.length() < 2) {
                item = "0" + item;
            }
            if (revertTo) {
                sb.insert(0, item);
            } else {
                sb.append(item);
            }
        }
        return sb.toString();
    }
}
