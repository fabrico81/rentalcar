package com.rentalcar.server.trace;

/**
 * @author faber
 *
 * MDC Logging
 */

public class MDC {


    /** Constant serviceName. */
    public static final String serviceName = "SERVICE";

    /**
     * The Enum MICROSERVICE_NAME.
     */
    public static enum SERVICE_NAME {

        /** Rental service. */
        RENTAL_SERVICE,
        /** Car service. */
        CAR_SERVICE,
        /** User service. */
        USER_SERVICE

    };

    /**
     * Instantiates a new mdc.
     */
    private MDC(){

    }

    /**
     * Sets the servicename.
     *
     * @param msName the new servicename
     */
    public static void setServicename(SERVICE_NAME msName){
        org.slf4j.MDC.put(serviceName, msName.name());
    }

    /**
     * Gets the Servicename.
     *
     * @return the Servicename
     */
    public static String getServicename() {
        return serviceName;
    }
}
