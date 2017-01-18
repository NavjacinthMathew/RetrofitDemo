package mathew.navjacinth.com.retrofitdemo.interfaces;

import mathew.navjacinth.com.retrofitdemo.model.EmployeeList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Navjacinth Mathew on 1/18/2017.
 */

public interface GetEmployeeDataService {
    @GET("")
    Call<EmployeeList> getEmployeeData();
}
