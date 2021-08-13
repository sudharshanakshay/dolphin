//package com.example.dolphin.feed;
//
//import android.content.Context;
//import android.os.Build;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.annotation.RequiresApi;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.example.dolphin.DataAdapter;
//import com.example.dolphin.model.Post;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RequiresApi(api = Build.VERSION_CODES.KITKAT)
//public class FeedClass extends Thread{
//    Context _context;
//    RequestQueue requestQueue;
//    final protected String url="http://a70c8ac2e069.ngrok.io/feed.php";
//
//    public FeedClass(Context _context, RequestQueue requestQueue) {
//        this._context = _context;
//        this.requestQueue = requestQueue;
//    }
//
//    DataAdapter myAdapter;
//    List<Post> feedList = new ArrayList<>();
//
//    public DataAdapter  fetchFeed(){
//        //requestQueue = Volley.newRequestQueue(_context);
//        JsonArrayRequest request = new JsonArrayRequest(
//                Request.Method.POST,
//                url,
//                null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        if(response == null){
//                            Toast.makeText(_context, "couldn't fetch, try again later", Toast.LENGTH_SHORT);
//                            return;
//                        }
//                        System.out.println(response.toString());
//                        //TODO: process the response convert into List of Post object
//                        JSONArray jsonArray = null;
//                        try {
//                            for(int i = 0; i<response.length(); i++){
//                                jsonArray = response.getJSONArray(i);
//                                Post post = new Post(/*jsonArray.getString(0),*/
//                                        jsonArray.getString(0),
//                                        jsonArray.getString(1),
//                                        jsonArray.getString(2));
//                                feedList.add(post);
//                            }
//                            System.out.println("--------------------------");
//                            System.out.println(feedList.get(0).getCaption().toString());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        // refreshing recycler view
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("e", "Error: " + error.getMessage());
//                        Toast.makeText(_context, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//        );
//         requestQueue.add(request);
////        System.out.println("p--------- ");
////        System.out.println(feedList.toString());
//
//         myAdapter = new DataAdapter(feedList, _context);
//        return myAdapter;
//    }
//}
