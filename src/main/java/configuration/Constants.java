package configuration;

public interface Constants {

    String MASTER_CONFIGURATION_FOLDER = "conf/";
    String PROPERTY_FILE_POSTFIX = ".conf";
    String MASTER_PROPERTY = MASTER_CONFIGURATION_FOLDER + "config-master" + PROPERTY_FILE_POSTFIX;


    String CLIENT_ID = "client-id-";
    String ACKS_CONFIG = "0";
    String LINGER_MS_CONFIG = "1";

    String GROUP_ID = "group-id-";
    String ENABLE_AUTO_COMMIT_CONFIG = "true";
    String AUTO_COMMIT_INTERVAL_MS_CONFIG = "1000";
    String SESSION_TIMEOUT_MS_CONFIG = "30000";
    String AUTO_OFFSET_RESET_CONFIG = "latest";
    String KEY_DESERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.StringDeserializer";
    String VALUE_DESERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.StringDeserializer";
}
