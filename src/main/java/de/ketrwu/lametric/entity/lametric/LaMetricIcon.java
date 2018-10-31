package de.ketrwu.lametric.entity.lametric;

import lombok.Getter;

@Getter
public enum LaMetricIcon {

    ATTENTION("i555"),
    DISPLIFY("i21651");

    private String icon;

    LaMetricIcon(String icon) {
        this.icon = icon;
    }

}
