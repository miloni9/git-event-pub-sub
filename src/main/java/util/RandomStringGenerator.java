package util;

import java.util.UUID;

public class RandomStringGenerator {

    /**
     * Generate UUID class based string of requested length
     * @param length length of required string
     * @return generated string
     */
    public static String UUIDString(int length){
        String uid =  UUID.randomUUID().toString();
        return  uid.substring(0, Math.min(uid.length(), length));
    }
    /**
     * Generate UUID class based string
     * @return generated string
     */
    public static String UUIDString(){
        return UUID.randomUUID().toString();
    }
}
