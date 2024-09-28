package com.Store.Store.domain.enums;

public enum VendorEnum {

        VENDOR_NOT_FOUND  ("Proveedor no encontrado"),
        VENDOR_ALREADY_EXISTS  ("Proveedor ya existe"),
        VENDOR_SAVED_SUCCESSFULLY ("Proveedor guardado exitosamente"),
        VENDOR_UPDATED_SUCCESSFULLY  ("Proveedor actualizado exitosamente"),
        VENDOR_DELETED_SUCCESSFULLY("Proveedor eliminado exitosamente");

        private final String value;

        VendorEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
}
