package de.ketrwu.lametric.entity.lametric;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class LaMetricFrame {

    private String text;
    private String icon;

    private LaMetricFrame(Builder builder) {
        setText(builder.text);
        setIcon(builder.icon.getIcon());
    }

    public static final class Builder {
        private String text;
        private LaMetricIcon icon = LaMetricIcon.DISPLIFY;

        public Builder() {
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder icon(LaMetricIcon icon) {
            this.icon = icon;
            return this;
        }

        public LaMetricFrame build() {
            return new LaMetricFrame(this);
        }
    }

}
