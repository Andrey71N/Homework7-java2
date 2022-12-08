package lesson7;

public class Seat extends CarElement{

    private Integer seatsCount;

    public Seat() {
    }

    public Seat(Integer seatsCount) {
        this.seatsCount = seatsCount;
        setName("Сидение");
    }

    public Integer getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(Integer seatsCount) {
        this.seatsCount = seatsCount;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatsCount=" + seatsCount +
                '}';
    }
}
