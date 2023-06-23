package interview.protection.devices;

public class TemperatureDevice {

    private final String id;
    private State state;
    private short temperature;

    public TemperatureDevice(String id, State state, short temperature) {
        this.id = id;
        this.state = state;
        this.temperature = temperature;
    }

    public String getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public short getTemperature() {
        return temperature;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setTemperature(short temperature) {
        this.temperature = temperature;
    }
}
