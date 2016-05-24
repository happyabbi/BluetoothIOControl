package chen.abraham.com.bluetooth;

/**
 * Created by AbrahamChen on 2016/5/24.
 */

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import chen.abraham.com.R;

/**
 * Devices listView adapter
 */
public class DevicesAdapter extends ArrayAdapter<BluetoothDevice> {

    private LayoutInflater layoutInflater;

    public DevicesAdapter(Context context, List<BluetoothDevice> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BluetoothDevice device = getItem(position);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.device, null);
            convertView.setTag(new DevicesHolder(convertView));
        }

        DevicesHolder holder = (DevicesHolder) convertView.getTag();
        holder.getDeviceName().setText(device.getName());
        holder.getDeviceMac().setText(device.getAddress());

        String strStatus;
        switch (device.getBondState()) {
            case (BluetoothDevice.BOND_BONDED):
                strStatus = "v";
                break;

            case (BluetoothDevice.BOND_BONDING):
                strStatus = "...";
                break;

            default:
                strStatus = "x";
                break;
        }
        holder.getDeviceStatus().setText(strStatus);

        return convertView;
    }
}