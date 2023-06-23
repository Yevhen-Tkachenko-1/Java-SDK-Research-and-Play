package interview.protection.devices;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class FireProtectServiceImpl implements FireProtectService {

    public static final State DEFAULT_STATE = State.DISARMED;
    public static final short DEFAULT_TEMPERATURE = 20;
    public static final short ALARM_DELTA_TEMPERATURE = 20;
    public static final short ALARM_BOUNDARY_TEMPERATURE = 80;

    private final Map<String, TemperatureDevice> devices = new ConcurrentHashMap<>();
    private final List<FireDepartment> fireDepartments = new CopyOnWriteArrayList<>();

    @Override
    public void changeState(String deviceId, State state) {
        TemperatureDevice device = findTemperatureDevice(deviceId);
        device.setState(state);
    }

    @Override
    public void changeTemperature(String deviceId, short temperatureDiff) {
        TemperatureDevice device = findTemperatureDevice(deviceId);
        synchronized (device){
            short newTemperature = (short) (device.getTemperature() + temperatureDiff);
            device.setTemperature(newTemperature);
        }
        short newTemperature = device.getTemperature();
        if(newTemperature > ALARM_BOUNDARY_TEMPERATURE || temperatureDiff > ALARM_DELTA_TEMPERATURE){
            if(device.getState() == State.ARMED){
                notifyFireDepartments(deviceId);
            }
        }
    }

    @Override
    public State getState(String deviceId) {
        return findTemperatureDevice(deviceId).getState();
    }

    @Override
    public short getTemperature(String deviceId) {
        return findTemperatureDevice(deviceId).getTemperature();
    }

    @Override
    public void registerFireDepartment(FireDepartment fireDepartment) {
        fireDepartments.add(fireDepartment);
    }

    private TemperatureDevice findTemperatureDevice(String deviceId){
        if(devices.containsKey(deviceId)){
            return devices.get(deviceId);
        } else {
            TemperatureDevice temperatureDevice = new TemperatureDevice(deviceId, DEFAULT_STATE, DEFAULT_TEMPERATURE);
            devices.put(deviceId, temperatureDevice);
            return temperatureDevice;
        }
    }

    private void notifyFireDepartments(String deviceId){
        for(FireDepartment fireDepartment: fireDepartments){
            fireDepartment.alarm(deviceId);
        }
    }
}
