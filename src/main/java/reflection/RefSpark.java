package reflection;

import java.lang.reflect.Constructor;

public class RefSpark {
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("org.apache.spark.mllib.stat.test.ChiSqTest");
            Constructor<?>[] con = c.getDeclaredConstructors();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
