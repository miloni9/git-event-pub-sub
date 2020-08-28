package configuration;

public class ClassHelper {

    public static String getClassName(Class<?>  aClass){
        return aClass.getCanonicalName();
    }

}
