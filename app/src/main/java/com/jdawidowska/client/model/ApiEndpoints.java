package com.jdawidowska.client.model;

public enum ApiEndpoints {

    FIND_ALL_EQUIPMENT("/findAllEquipment"),
    LEND_EQUIPMENT("/lend/"),
    RETURN_EQUIPMENT("/return/");

    String endpoint;

    private final String HOSTNAME ="http://192.168.1.4:8089";


    ApiEndpoints(String endpoint){
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return HOSTNAME + endpoint;
    }
}