package mathew.navjacinth.com.retrofitdemo.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import mathew.navjacinth.com.retrofitdemo.R;
import mathew.navjacinth.com.retrofitdemo.adapter.EmployeeAdapter;
import mathew.navjacinth.com.retrofitdemo.interfaces.GetEmployeeDataService;
import mathew.navjacinth.com.retrofitdemo.model.Employee;
import mathew.navjacinth.com.retrofitdemo.model.EmployeeList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String baseUrl = "http://nmathew.pe.hu/json/";
    private Retrofit retrofit;
    private ProgressDialog progressBar;
    private EmployeeAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Method call to show ProgressBar with a message*/
        initProgressBar("Loading Data...Please wait");

        /*Retrofit configuration*/
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /*Create handle for the Retrofit interface*/
        GetEmployeeDataService service = retrofit.create(GetEmployeeDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<EmployeeList> call = service.getEmployeeData(100);

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<EmployeeList>() {
            @Override
            public void onResponse(Call<EmployeeList> call, Response<EmployeeList> response) {
                progressBar.dismiss();
                generateEmployeeList(response.body().getEmployeeArrayList());
            }

            @Override
            public void onFailure(Call<EmployeeList> call, Throwable t) {
                progressBar.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of employees using RecyclerView with custom adapter*/
    private void generateEmployeeList(ArrayList<Employee> empDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_employee_list);

        adapter = new EmployeeAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    /*Method to initiate the ProgressBar with a custom message*/
    private void initProgressBar(String loaderMessage) {
        progressBar = new ProgressDialog(MainActivity.this);
        progressBar.setMessage(loaderMessage);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setCancelable(false);
        progressBar.setCanceledOnTouchOutside(false);
        progressBar.setIndeterminate(true);
        progressBar.show();
    }
}
