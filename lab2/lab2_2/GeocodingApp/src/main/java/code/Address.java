package code;

import java.util.Objects;

public class Address {
    private String road, state, city, zio, houseNumber;

    public Address(String road, String state, String city, String zio, String houseNumber) {
        this.road = road;
        this.state = state;
        this.city = city;
        this.zio = zio;
        this.houseNumber = houseNumber;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZio() {
        return zio;
    }

    public void setZio(String zio) {
        this.zio = zio;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(road, address.road) && Objects.equals(state, address.state) && Objects.equals(city, address.city) && Objects.equals(zio, address.zio) && Objects.equals(houseNumber, address.houseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(road, state, city, zio, houseNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "road='" + road + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zio='" + zio + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }
}
