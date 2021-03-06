package com.example.niceg.mysqlproject;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

public class TemplatesMenu extends AppCompatActivity {
    static VolleyStats vol;
    Template basic = new Template("BASIC", true, false, false, true,
            true, false, false, false, false, false, false, false,
            false, false, false, false, false, false);
    Template intermediate = new Template("INTERMEDIATE", true, true, true, true,
            true, true, true, true, false, false, false, false,
            false, false, false, false, false, false);
    Template comprehensive = new Template("COMPREHENSIVE", true, true, true, true,
            true, true, true, true, true, true, true, true,
            true, true, true, true, true, false);
    Template comprehensive2 = new Template("COMPREHENSIVE WITHOUT UNFORCED ERRORS", true,
            true, true, true, true, true, true, true, true,
            true, true, true, false, false, false,
            false, false, false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ComponentName sender = getCallingActivity();

        if(sender != null){
            NewTemplate newTemplate = new NewTemplate();
            Template temp = newTemplate.getVolNewTemplate();
            vol.templatesList.add(temp);
        }
        else {
            Home h = new Home();
            vol = h.getVolHome();
            //vol = (VolleyStats) getIntent().getSerializableExtra("volleyStatsClass");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates_menu);

        if( vol.templatesList.isEmpty()) {
            vol.templatesList.add(basic);
            vol.templatesList.add(intermediate);
            vol.templatesList.add(comprehensive);
            vol.templatesList.add(comprehensive2);
        }

        for (int i = 0; i < vol.templatesList.size(); i++) {
            Template temp = (Template)vol.templatesList.get(i);
            temp.m_selected = false;
        }

        newTemplate(vol.templatesList);
    }

    public void onSettingsClick(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onAddClick(View view) {
        Intent intent = new Intent(this, NewTemplate.class);
        startActivity(intent);
     }

    public void onDoneClick(View view) {
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);
        int radioButtonID = rg.getCheckedRadioButtonId();
        View radioButton = rg.findViewById(radioButtonID);
        int idx = rg.indexOfChild(radioButton);

        if(idx == -1) {
            idx = 1;
        }

        RadioButton r = (RadioButton)  rg.getChildAt(idx);

        String selectedtext = r.getText().toString();

        checkRadioBoxes(vol.templatesList, selectedtext);

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public boolean checkRadioBoxes(List list, String name) {
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            Template temp = (Template)list.get(i);
            if (temp.getNameEmma() == name) {
                temp.m_selected = true;
                flag = true;
            }
        }

//        if(flag == false) {
//            for (int i = 0; i < list.size(); i++) {
//                Template temp = (Template) list.get(i);
//                if (temp.getNameEmma() == "INTERMEDIATE") {
//                    temp.m_selected = true;
//                    flag = true;
//                }
//            }
//        }
        return flag;
    }

     public void newTemplate(List list) {
        //loop through list and make buttons for each template

         for (int i = 0; i < list.size(); i++) {

             Template temp = (Template)list.get(i);

             RadioButton radioButton = new RadioButton(this);
             radioButton.layout(0,16,24,0);
             radioButton.setText(temp.getNameEmma());
             radioButton.setTextSize(30);
             //radioButton.setTextColor(Color.parseColor("#aaa"));

//             if(temp.getNameEmma() == "INTERMEDIATE") {
//                 //radioButton.setChecked(true);
//                 temp.m_selected = true;
//             }

             RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);
             rg.addView(radioButton);
         }

     }

     public static VolleyStats getVolTemplatesMenu() {
        return vol;
     }

}
