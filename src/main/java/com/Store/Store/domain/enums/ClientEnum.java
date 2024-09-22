package com.Store.Store.domain.enums;

public enum ClientEnum {

    CLIENT_NOT_FOUND  ("Cliente no encontrado"),
    CLIENT_ALREADY_EXISTS  ("Cliente ya existe"),
    CLIENT_SAVED_SUCCESSFULLY ("Cliente guardado exitosamente"),
    CLIENT_UPDATED_SUCCESSFULLY  ("Cliente actualizado exitosamente"),
    CLIENT_DELETED_SUCCESSFULLY("Cliente eliminado exitosamente");;

    private final String value;

    ClientEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
