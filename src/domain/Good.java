package domain;

import java.util.Date;


public class Good {

    private Integer id;
    private String name;
    private Float price;
    private String time;

    public Good(Integer id, String name, Float price, String time) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
