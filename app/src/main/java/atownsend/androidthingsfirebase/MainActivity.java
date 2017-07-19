package atownsend.androidthingsfirebase;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import atownsend.androidthingsfirebase.peripheral.SimpleLed;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {

  private static final String TAG = MainActivity.class.getSimpleName();

  private static final String RED_PIN = "BCM4";
  private static final String BLUE_PIN = "BCM6";
  private static final String GREEN_PIN = "BCM16";

  private SimpleLed red;
  private SimpleLed blue;
  private SimpleLed green;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    try {
      red = new SimpleLed(RED_PIN, SimpleLed.InitialState.OFF);
      blue = new SimpleLed(BLUE_PIN, SimpleLed.InitialState.OFF);
      green = new SimpleLed(GREEN_PIN, SimpleLed.InitialState.OFF);
    } catch (Exception e) {
      Log.e(TAG, "Error opening LEDs", e);
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference ref = database.getReference("lighting_system");
    DatabaseReference redRef = ref.child("red_on");
    DatabaseReference blueRef = ref.child("blue_on");
    DatabaseReference greenRef = ref.child("green_on");

    redRef.addValueEventListener(new LedValueEventListener(red));
    blueRef.addValueEventListener(new LedValueEventListener(blue));
    greenRef.addValueEventListener(new LedValueEventListener(green));
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    try {
      red.close();
    } catch (Exception ignored) {
      red = null;
    }

    try {
      blue.close();
    } catch (Exception ignored) {
      blue = null;
    }

    try {
      green.close();
    } catch (Exception ignored) {
      green = null;
    }
  }
}
