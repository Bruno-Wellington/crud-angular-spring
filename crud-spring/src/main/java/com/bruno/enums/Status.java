package com.bruno.enums;

public enum Status {
    ACTIVE("Ativo"), INACTIVE("Inativo");

    private String value;

    private Status(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    // toString
    @Override
    public String toString() {
        return this.value;
    }

}
