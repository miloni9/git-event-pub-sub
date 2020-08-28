package configuration;

public class Constants {

	public static final String SERVERS = "10.40.10.230:9092,10.40.11.157:9093";


	public static final String MASTER_CONFIGURATION_FOLDER = "conf/";
	public static final String PROPERTY_FILE_POSTFIX = ".conf";
//	public static final String PROPERTY_FILE_PREFIX = "com.miloni.";
	public static final String MASTER_PROPERTY = MASTER_CONFIGURATION_FOLDER + "config-master" + PROPERTY_FILE_POSTFIX;

	public static final String streamPropPrefix = "STREAM.";
	public static final String clientPropPrefix = "CLIENT.";
	public static final String producerPropPrefix = "PRODUCER.";
	public static final String consumerPropPrefix = "CONSUMER.";
	public static final String dbPropPrefix = "DB.";
	public static final String logPropPrefix = "LOG.";

}
