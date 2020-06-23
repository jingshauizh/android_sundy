package jings.ex.android.com.customuiapp.suspensiondemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jings.ex.android.com.customuiapp.R;

public class MainActivity extends Activity {

    private SuspensionWindowUtil windowUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_suspand);
        ButterKnife.bind(this);

        windowUtil = new SuspensionWindowUtil(this);

    }

    @OnClick(R.id.btn_showsuspension)
    public void onBtnShowClicked() {
        windowUtil.showSuspensionView();
    }

    @OnClick(R.id.btn_hidesuspension)
    public void onBtnHideClicked() {
        windowUtil.hideSuspensionView();
    }

    @OnClick(R.id.btn_showdialog)
    public void onBtnShowDialogClicked() {
        Context mContext = getApplicationContext();
        Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.show();
    }
}
