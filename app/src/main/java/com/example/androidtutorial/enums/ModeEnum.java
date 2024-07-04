package com.example.androidtutorial.enums;

public enum ModeEnum {
    ALL("MODE_ALL_BOOKS"),
    FAVORITE("MODE_FAVORITE_BOOKS"),
    WISHLIST("MODE_WISHLIST_BOOKS"),
    ALREADY_READ("MODE_ALREADY_READ"),
    CURRENTLY_READING("MODE_CURRENTLY_READING");

    private final String value;

    ModeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ModeEnum from(String value) {
        for (ModeEnum modeEnum : ModeEnum.values()) {
            if (modeEnum.getValue().equals(value)) {
                return modeEnum;
            }
        }
        return null;
    }
}
