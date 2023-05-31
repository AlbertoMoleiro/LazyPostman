package com.lazypostman.optimizeroute.model;

import com.lazypostman.optimizeroute.model.formcreator.Town;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryItem {
    private String province;
    private Town town;
    private Integer postCode;
    private String roadType;
    private String roadName;
    private Integer roadNumber;
    private Double coordX;
    private Double coordY;

    public double distanceBetween(ItineraryItem otraCasa) {
        double dx = this.coordX - otraCasa.getCoordX();
        double dy = this.coordY - otraCasa.getCoordY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}