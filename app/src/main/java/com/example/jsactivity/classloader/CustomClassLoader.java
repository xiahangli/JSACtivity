package com.example.jsactivity.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Henry
 * @Date 2022/4/5  10:03 PM
 * @Email 2427417167@qq.com
 */
public class CustomClassLoader extends ClassLoader {
    private String mClassPath; // Class存放的的目录

    public CustomClassLoader(String classPath) {
        mClassPath = classPath;
    }


    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {

        File file = new File(mClassPath, getClassName(name));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FileInputStream fis = null;
        byte[] bytes = null;
        try {
            bytes = new byte[10_000];
            fis = new FileInputStream(file);
            int num = 0;
            while (num != -1) {
              num = fis.read(bytes, 0, bytes.length);
              bos.write(bytes, 0, num);
            }
            bytes = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defineClass(name, bytes, 0, bytes.length);
    }

    private String getClassName(String name) {
        String className;
        int lastIndex = name.lastIndexOf(".");
        if (lastIndex == -1) {
            className = name + ".class";
        } else {
            className = name.substring(lastIndex + 1, name.length() - 1);
        }
        return className;
    }
}
