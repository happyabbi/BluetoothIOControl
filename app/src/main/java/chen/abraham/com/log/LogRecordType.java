package chen.abraham.com.log;

import chen.abraham.com.R;

/**
 * Created by AbrahamChen on 2016/5/24.
 */
public enum LogRecordType {

    INFO("info", R.color.info),
    FROM_DEVICE("<<", R.color.fromDevice),
    TO_DEVICE(">>", R.color.toDevice);

    private String title;
    private int textColor;

    public String getTitle() {
        return title;
    }

    public int getTextColor() {
        return textColor;
    }

    LogRecordType(String title, int textColor) {
        this.title = title;
        this.textColor = textColor;
    }
}
