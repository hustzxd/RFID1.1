package com.example.buxia.rfid11;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现用户登录
 * Created by buxia on 2015/11/16.
 */
public class LoginFragment extends Fragment {
    static public boolean isLogin = false;
    static public String studentName = "申小东";
    private Button loginBtn;
    private AutoCompleteTextView studentNumberView;
    private EditText passwordView;

    private String studentNumber;
    private String password;
    private int retCode;

    private HelloFragment helloFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);


        loginBtn = (Button) view.findViewById(R.id.sign_in_button);
        studentNumberView = (AutoCompleteTextView) view.findViewById(R.id.student_number);
        passwordView = (EditText) view.findViewById(R.id.password);
        final Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("zzz", s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    retCode = jsonObject.getInt("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (retCode == 1) {
                    Toast.makeText(getActivity().getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    isLogin = true;
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    if (helloFragment == null) {
                        helloFragment = new HelloFragment();
                    }
                    transaction.replace(R.id.fragment_content, helloFragment);
                    transaction.commit();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "用户名或密码错误",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("zzz", volleyError.getMessage(), volleyError);
                Toast.makeText(getActivity().getApplicationContext(), "请检查网络连接",
                        Toast.LENGTH_SHORT).show();
            }
        };

        loginBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            studentNumber = studentNumberView.getText().toString();
                                            password = passwordView.getText().toString();
                                            if (studentNumber.equals("") || password.equals("")) {
                                                Toast.makeText(getActivity().getApplicationContext(),
                                                        "用户名或密码不能为空，请重新输入!",
                                                        Toast.LENGTH_SHORT).show();
                                            }


                                            RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                                            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                                                    "http://192.168.31.184/Client/login.php", listener, errorListener) {
                                                @Override
                                                protected Map<String, String> getParams() throws AuthFailureError {
                                                    Map<String, String> map = new HashMap<String, String>();
                                                    map.put("username", studentNumber);
                                                    map.put("password", password);
                                                    return map;
                                                }
                                            };
                                            requestQueue.add(stringRequest);

                                        }
                                    }
        );
        return view;
    }

}