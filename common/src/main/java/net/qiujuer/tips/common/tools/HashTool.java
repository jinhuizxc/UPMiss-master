/*
 * Copyright (C) 2014-2016 Qiujuer <qiujuer@live.cn>
 * WebSite http://www.qiujuer.net
 * Author qiujuer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.qiujuer.tips.common.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by JuQiu
 * on 16/6/30.
 * This is HashKit
 * Calculate the HashCode file or string
 */
public final class HashTool {
    /**
     * This is HashKit
     * Calculate the HashCode file or string
     */

    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};

    private static String convertToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (byte a : b) {
            sb.append(HEX_DIGITS[(a & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[a & 0x0f]);
        }
        return sb.toString();
    }

    /**
     * Get a String's HashCode
     *
     * @param str String
     * @return HashCode
     */
    public static String getMD5String(String str) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        md5.update(str.getBytes());
        return convertToHexString(md5.digest());
    }

    /**
     * Get a File's HashCode
     *
     * @param file File
     * @return HashCode
     */
    public static String getMD5String(File file) {
        // Create md5
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        // Stream
        InputStream in = null;
        byte[] buffer = new byte[1024];
        int numRead;
        // Read
        try {
            in = new FileInputStream(file);
            while ((numRead = in.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            return convertToHexString(md5.digest());
        } catch (Exception e) {
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

