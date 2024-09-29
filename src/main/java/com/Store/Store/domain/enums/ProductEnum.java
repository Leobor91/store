package com.Store.Store.domain.enums;

public enum ProductEnum {
    PRODUCT_ALREADY_EXISTS("El producto ya existe"),
    PRODUCT_SAVED_SUCCESSFULLY("Producto guardado exitosamente"),
    PRODUCT_UPDATED_SUCCESSFULLY("Producto actualizado exitosamente"),
    PRODUCT_DELETED_SUCCESSFULLY("Producto eliminado exitosamente"),
    PRODUCT_NOT_FOUND("Producto no encontrado");

    private final String value;

    ProductEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
