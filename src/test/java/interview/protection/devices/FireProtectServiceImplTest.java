package interview.protection.devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class FireProtectServiceImplTest {

    FireProtectService fireProtectService;

    @Mock
    FireDepartment fireDepartment;

    @BeforeEach
    void setUp(){
        fireProtectService = new FireProtectServiceImpl();
    }

    @Test
    void changeState() {
        String deviceId = "1";
        fireProtectService.getState(deviceId); // DISARMED by default
        assertEquals(State.DISARMED, fireProtectService.getState(deviceId));

        fireProtectService.changeState(deviceId, State.ARMED);
        assertEquals(State.ARMED, fireProtectService.getState(deviceId));
    }

    @Test
    void changeTemperature() {
        String deviceId = "1";
        short actualTemperature = fireProtectService.getTemperature(deviceId);
        assertEquals(FireProtectServiceImpl.DEFAULT_TEMPERATURE, actualTemperature);

        short expectedTemperature = (short) (actualTemperature + 15);
        fireProtectService.changeTemperature(deviceId, (short) 15);
        assertEquals(expectedTemperature, fireProtectService.getTemperature(deviceId));
    }

    @Test
    void alarmFireDepartment() {
        String deviceId = "1";
        short actualTemperature = fireProtectService.getTemperature(deviceId);
        assertEquals(FireProtectServiceImpl.DEFAULT_TEMPERATURE, actualTemperature);

        short expectedTemperature = (short) (actualTemperature + 15);
        fireProtectService.changeTemperature(deviceId, (short) 15);
        assertEquals(expectedTemperature, fireProtectService.getTemperature(deviceId));
    }

}