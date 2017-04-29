package hairsaon.models.classes_for_master;

/**
 * Created by Лимаренко on 28.04.2017.
 */
public class ServiceMaster {
    String name;
    int duration; // в минутах
    int price;
    String info;

    public ServiceMaster(String name, int duration, int price) {
        this.name = name;
        this.duration = duration;
        this.price = price;
    }

    public ServiceMaster(String name, int duration, int price, String info) {
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Title: " + name +
                ", duration: " + duration +
                ", price: " + price +
                ", info service: " + info + "."
                ;
    }
}