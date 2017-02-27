/**
 * Created by mukundmk on 26/02/17.
 */

import java.io.*;

public class Reloader extends ClassLoader {
    @Override
    public Class<?> loadClass(String className) {
        return findClass(className);
    }

    @Override
    public Class<?> findClass(String className) {
        try {
            File f = new File(className.replaceAll("\\.", "/") + ".class");
            int size = (int) f.length();
            byte buff[] = new byte[size];
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);
            dis.readFully(buff);
            dis.close();
            return defineClass(className, buff, 0, buff.length);
        }
        catch (IOException ioe) {
            try {
                return super.loadClass(className);
            }
            catch (ClassNotFoundException e) {
            }

            ioe.printStackTrace(System.out);
            return null;
        }
    }

}
