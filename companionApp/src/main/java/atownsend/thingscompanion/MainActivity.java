package atownsend.thingscompanion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Switch;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Switch redLightSwitch = (Switch) findViewById(R.id.red_switch);
    Switch blueLightSwitch = (Switch) findViewById(R.id.blue_switch);
    Switch greenLightSwitch = (Switch) findViewById(R.id.green_switch);

    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    final DatabaseReference reference = database.getReference("lighting_system");
    final DatabaseReference redReference = reference.child("red_on");
    final DatabaseReference blueReference = reference.child("blue_on");
    final DatabaseReference greenReference = reference.child("green_on");

    redReference.addValueEventListener(new SwitchValueEventListener(redLightSwitch));
    blueReference.addValueEventListener(new SwitchValueEventListener(blueLightSwitch));
    greenReference.addValueEventListener(new SwitchValueEventListener(greenLightSwitch));

    redLightSwitch.setOnCheckedChangeListener(
        (buttonView, isChecked) -> redReference.setValue(isChecked));

    blueLightSwitch.setOnCheckedChangeListener(
        (buttonView, isChecked) -> blueReference.setValue(isChecked));

    greenLightSwitch.setOnCheckedChangeListener(
        (buttonView, isChecked) -> greenReference.setValue(isChecked));

    Button turnAllOnButton = (Button) findViewById(R.id.turn_all_on_btn);
    Button turnAllOffButton = (Button) findViewById(R.id.turn_all_off_btn);

    turnAllOffButton.setOnClickListener(v -> updateAllSwitches(reference, false));

    turnAllOnButton.setOnClickListener(v -> updateAllSwitches(reference, true));
  }

  /**
   * Bulk update all the switches
   * @param reference the database reference
   * @param on true for on, false for off
   */
  private void updateAllSwitches(final DatabaseReference reference, final boolean on) {
    Map<String, Object> childUpdates = new HashMap<>();
    childUpdates.put("/red_on", on);
    childUpdates.put("/blue_on", on);
    childUpdates.put("/green_on", on);
    reference.updateChildren(childUpdates);
  }
}
