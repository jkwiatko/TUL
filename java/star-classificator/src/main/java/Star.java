import java.math.BigDecimal;

public class Star {

    String name;
    BigDecimal luminosity;
    BigDecimal surfaceTemperature;
    EStar clazz;

    Star(String name, BigDecimal luminosity, BigDecimal surfaceTemperature, EStar clazz) {
        this.name               = name;
        this.luminosity         = luminosity;
        this.surfaceTemperature = surfaceTemperature;
        this.clazz              = clazz;
    }

}