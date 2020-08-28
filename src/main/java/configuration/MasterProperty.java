package configuration;


import file.PropertyFile;

import java.util.Properties;
import java.util.stream.Collectors;


public class MasterProperty {

    public static Properties getMasterProperty() throws Exception {
        return PropertyFile.readPropertyFile(Constants.MASTER_PROPERTY, true);
    }


    public static Properties getServiceClassProperty(Class<?> aClass) throws Exception {
        Properties p = getMasterProperty();
        if (!p.containsKey(ClassHelper.getClassName(aClass))) {
            p.put(ClassHelper.getClassName(aClass), Constants.MASTER_CONFIGURATION_FOLDER + ClassHelper.getClassName(aClass) + Constants.PROPERTY_FILE_POSTFIX);
            PropertyFile.updatePropertyFile(p, Constants.MASTER_PROPERTY);
        }
        return PropertyFile.readPropertyFile(p.getProperty(ClassHelper.getClassName(aClass)), true);
    }
/*

    public static Properties getCommonProperty(String propertyOf) throws Exception {
        String propertyFileName = Constants.MASTER_CONFIGURATION_FOLDER + Constants.PROPERTY_FILE_PREFIX + propertyOf +Constants.PROPERTY_FILE_POSTFIX;
        return PropertyFile.readPropertyFile(propertyFileName, true);
    }
*/


    public static Properties getServiceClassScopeProperty(Class<?> aClass, String startsWith) throws Exception {
        Properties p = getServiceClassProperty(aClass);
        Properties properties = new Properties();
        p.entrySet().stream().filter((s) -> s.getKey().toString().startsWith(startsWith)).collect(Collectors.toSet()).forEach((e) -> properties.put(e.getKey().toString().replace(startsWith, ""), e.getValue()));
        return properties;
    }



    public static void main(String[] args) throws Exception {
        Properties p = new Properties();
        PropertyFile.writePropertyFile(p, Constants.MASTER_PROPERTY);

    }

}
