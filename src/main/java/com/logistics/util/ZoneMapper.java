package com.logistics.util;

import java.util.Map;

public class ZoneMapper {
    private static final Map<String, String> addressToZoneMap = Map.of(
        "Visakhapatnam", "ZoneA",
        "Hyderabad", "ZoneB",
        "Mumbai", "ZoneC",
        "Bangalore", "ZoneD"
    );

    public static String getZoneForAddress(String address) {
        return addressToZoneMap.entrySet().stream()
            .filter(entry -> address.contains(entry.getKey()))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse("ZoneX");
    }
}
