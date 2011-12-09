package com.xiaoxiang.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class SystemInTest {
    public static void main(String[] args) {
        InputStreamReader isr =
                new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String value = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            value = reader.readLine();
            while (value != null) {
                if ("exit".equalsIgnoreCase(value)) break;
                value = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {

        } finally {
            try {
                reader.close();
            } catch (IOException e) {

            }
        }

    }
}
