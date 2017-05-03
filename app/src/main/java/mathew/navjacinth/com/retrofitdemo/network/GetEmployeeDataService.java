package mathew.navjacinth.com.retrofitdemo.network;

import mathew.navjacinth.com.retrofitdemo.model.EmployeeList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Navjacinth Mathew on 1/18/2017.
 */

public interface GetEmployeeDataService {
    @GET("retrofit-demo.php")
    Call<EmployeeList> getEmployeeData(@Query("company_no") int companyNo);
}
