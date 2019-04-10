/**
 * SportsCar.java
 * @version 1.0
 * @author Allen Liu
 * @since April 3, 2019
 * Represents cool sports cars i guess
 */
class SportsCar {
    
    String manufacturer;
    String model;
    double fuelTankSize;
    double topSpeed;
    double cost;
    double mpg;

    public SportsCar(String manufacturer, String model, double fuelTankSize, double topSpeed, double cost, double mpg) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.fuelTankSize = fuelTankSize;
        this.topSpeed = topSpeed;
        this.cost = cost;
        this.mpg = mpg;
    }
    
    /**
     * range
     * @return double, the range of the car
     */
    public double range() {
        return fuelTankSize * mpg;
    }
}