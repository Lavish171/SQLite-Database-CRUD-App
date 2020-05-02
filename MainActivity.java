package com.example.sql_lite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.Level;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    EditText name,marks,surname,idedittext;
    Button add_data,viewall,update,delete;
     public void adddata(View view)
     {
     boolean isinserted= dataBaseHelper.insertdata(name.getText().toString(),surname.getText().toString(),marks.getText().toString());
     if(isinserted==true) Toast.makeText(getApplicationContext(),"Data is inserted  :)",Toast.LENGTH_SHORT).show();
     else Toast.makeText(getApplicationContext(),"Data is not inserted Sorry (:",Toast.LENGTH_SHORT).show();

     }
     public void viewall(View view)
     {
         Toast.makeText(getApplicationContext(),"View all clicked",Toast.LENGTH_SHORT).show();
      Cursor res=dataBaseHelper.getalldata();
      if(res.getCount()==0) { showmessage("Error","Nothing Found");
      Toast.makeText(getApplicationContext(),"There is no data in the table", Toast.LENGTH_SHORT).show();
      return ;
      }//if block
       StringBuffer stringBuffer=new StringBuffer();
      while (res.moveToNext())
      {
          stringBuffer.append("id : "+res.getString(0)+"\n");
          stringBuffer.append("Name : "+res.getString(1)+"\n");
          stringBuffer.append( "Surname : "+res.getString(2)+"\n");
          stringBuffer.append("Marks : "+res.getString(3)+"\n");
          stringBuffer.append("\n");
          //show all the data
      }
      //if count is equal to the 0 then it means that there is no data
         showmessage("Data",stringBuffer.toString());
     }//end of the view all method

    public void update(View view)
    {
     boolean isupdated=dataBaseHelper.updatedata(idedittext.getText().toString(),name.getText().toString(),
             surname.getText().toString(),marks.getText().toString());
     if(isupdated) Toast.makeText(getApplicationContext(),"Data is updated",Toast.LENGTH_SHORT).show();
     else Toast.makeText(getApplicationContext(),"Data is Not  updated",Toast.LENGTH_SHORT).show();
    }

    public void delete(View view)
    {
        Integer deletedrows= dataBaseHelper.deletedata(idedittext.getText().toString());
        if(deletedrows>=0) Toast.makeText(getApplicationContext(),"Data deleted",Toast.LENGTH_SHORT).show();
        else  Toast.makeText(getApplicationContext(),"Data Not deleted",Toast.LENGTH_SHORT).show();
    }
     public void showmessage(String title,String message)
     {
         AlertDialog.Builder builder=new AlertDialog.Builder(this);
         builder.setCancelable(false);//this blocks the back button
         builder.setTitle(title);
         builder.setMessage(message);
         builder.show();
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper=new DataBaseHelper(this);//this would call the constructor of the DataBaseHelper class
        //when we run the app ,it would call the oncreate method and in turn i
        // t would call the the constructor
        //of the DataBaseHelper class
        name=findViewById(R.id.name);
        marks=findViewById(R.id.marks);
        surname=findViewById(R.id.surname);
        add_data=findViewById(R.id.button);
        viewall=findViewById(R.id.viewallbutton);
        update=findViewById(R.id.update);
        idedittext=findViewById(R.id.idedittext);
        delete=findViewById(R.id.delete);
    }

}
