package org.example;

import lombok.Data;

import javax.persistence.Id;
import java.io.File;

@Data
public class CarBrand {

    private String carBrand;
    private String urlName;

    public CarBrand(String carBrand, String urlName) {
        this.carBrand = carBrand;
        this.urlName = urlName;
    }
}
