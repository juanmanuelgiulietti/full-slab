package com.fullslab.suppliers.enums;

public enum IvaCategory {
    RESPONSABLE_INSCRIPTO("Responsable Inscripto"),
    MONOTRIBUTISTA("Monotributista"),
    EXENTO("Exento"),
    CONSUMIDOR_FINAL("Consumidor Final");

    private final String displayName;

    IvaCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}