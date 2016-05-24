package chen.abraham.com.bluetooth;

import android.view.View;
import android.widget.TextView;

import chen.abraham.com.R;

/**
 * Created by AbrahamChen on 2016/5/24.
 */
public class DevicesHolder {

    private TextView deviceName;
    private TextView deviceMac;
    private TextView deviceStatus;

    public TextView getDeviceMac() {
        return deviceMac;
    }

    public TextView getDeviceName() {
        return deviceName;
    }

    public TextView getDeviceStatus() {
        return deviceStatus;
    }

    public DevicesHolder(View rowView) {
        deviceName = (TextView) rowView.findViewById(R.id.deviceName);
        deviceMac = (TextView) rowView.findViewById(R.id.deviceMac);
        deviceStatus = (TextView) rowView.findViewById(R.id.deviceStatus);
    }
}
