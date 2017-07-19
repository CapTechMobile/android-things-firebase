package atownsend.androidthingsfirebase.peripheral;

import android.util.Log;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;
import java.io.IOException;

public class SimpleLed implements AutoCloseable {

  private static final String TAG = SimpleLed.class.getSimpleName();

  private Gpio led;

  public enum InitialState {
    ON(Gpio.DIRECTION_OUT_INITIALLY_HIGH), OFF(Gpio.DIRECTION_OUT_INITIALLY_LOW);

    final int direction;

    InitialState(int direction) {
      this.direction = direction;
    }
  }

  public SimpleLed(String pinName, InitialState initialState) throws Exception {
    PeripheralManagerService managerService = new PeripheralManagerService();
    try {
      Gpio led = managerService.openGpio(pinName);
      connect(led, initialState);
    } catch (IOException e) {
      close();
      throw e;
    }
  }

  public void turnOnOff(boolean on) {
    try {
      led.setValue(on);
    } catch (IOException e) {
      Log.e(TAG, "Error setting LED value", e);
    }
  }

  private void connect(Gpio led, InitialState initialState) throws IOException {
    this.led = led;
    this.led.setDirection(initialState.direction);
  }

  @Override public void close() throws Exception {
    if (led != null) {
      try {
        led.close();
      } finally {
        led = null;
      }
    }
  }
}
