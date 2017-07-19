package atownsend.androidthingsfirebase;

import android.util.Log;
import atownsend.androidthingsfirebase.peripheral.SimpleLed;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LedValueEventListener implements ValueEventListener {

  private final SimpleLed led;

  LedValueEventListener(SimpleLed led) {
    this.led = led;
  }

  @Override public void onDataChange(DataSnapshot dataSnapshot) {
    Boolean on = (Boolean) dataSnapshot.getValue();
    led.turnOnOff(on == null ? false : on);
  }

  @Override public void onCancelled(DatabaseError databaseError) {

  }
}
