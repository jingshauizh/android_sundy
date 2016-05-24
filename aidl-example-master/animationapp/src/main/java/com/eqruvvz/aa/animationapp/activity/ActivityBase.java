package com.eqruvvz.aa.animationapp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Toast;


import com.eqruvvz.aa.animationapp.R;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 16-4-7.
 */
public class ActivityBase extends AppCompatActivity {

    protected static final int SHOW_TIME = 1;
    private ProgressDialog m_ProgressDialog;
    protected void showMsg(String msg){
        Integer duration =1000;
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

    }

    protected void openActivity(Class<?> classA)  {
        Intent intent = new Intent();
        intent.setClass(this,classA);
        startActivity(intent);
    }

    protected LayoutInflater getLayouInflater() {
        LayoutInflater _LayoutInflater = LayoutInflater.from(this);
        return _LayoutInflater;
    }

    public void setAlertDialogIsClose(DialogInterface pDialog,Boolean pIsClose)
    {
        try {
            Field _Field = pDialog.getClass().getSuperclass().getDeclaredField("mShowing");
            _Field.setAccessible(true);
            _Field.set(pDialog, pIsClose);
        } catch (Exception e) {
        }
    }

    protected AlertDialog showAlertDialog(int p_TitelResID,String p_Message,DialogInterface.OnClickListener p_ClickListener)
    {
        String _Title = getResources().getString(p_TitelResID);
        return showAlertDialog(_Title, p_Message, p_ClickListener);
    }

    protected AlertDialog showAlertDialog(String p_Title,String p_Message,DialogInterface.OnClickListener p_ClickListener)
    {
        return new AlertDialog.Builder(this)
                .setTitle(p_Title)
                .setMessage(p_Message)
                .setPositiveButton(R.string.ButtonTextYes, p_ClickListener)
                .setNegativeButton(R.string.ButtonTextNo, null)
                .show();
    }

    protected void showProgressDialog(int p_TitleResID,int p_MessageResID) {
        m_ProgressDialog = new ProgressDialog(this);
        m_ProgressDialog.setTitle(getString(p_TitleResID));
        m_ProgressDialog.setMessage(getString(p_MessageResID));
        m_ProgressDialog.show();
    }

    protected void dismissProgressDialog() {
        if(m_ProgressDialog != null)
        {
            m_ProgressDialog.dismiss();
        }
    }
}
