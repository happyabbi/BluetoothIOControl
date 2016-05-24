package chen.abraham.com.log;

import android.view.View;
import android.widget.TextView;

import chen.abraham.com.R;

/**
 * Created by AbrahamChen on 2016/5/24.
 */
public class LogHolder {
    private TextView time;
    private TextView type;
    private TextView message;

    public TextView getTime() {
        return time;
    }

    public TextView getType() {
        return type;
    }

    public TextView getMessage() {
        return message;
    }

    public LogHolder(View rowView) {
        time = (TextView) rowView.findViewById(R.id.logrecord_time);
        type = (TextView) rowView.findViewById(R.id.logrecord_type);
        message = (TextView) rowView.findViewById(R.id.logrecord_message);
    }
}
