package de.ryanjew.fashow.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.BackendlessCallback;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.ryanjew.fashow.R;
import de.ryanjew.fashow.ui.base.BaseActivity;


public class ViewRegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setupToolbar();
    }

    @OnClick(R.id.fab)
    public void onFabClicked(View view) {
        if (register()) {
            Snackbar.make(view, "Created Account!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            finish();
        }
        else {
            Snackbar.make(view, "Failed to make Account!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }

    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openDrawer();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getSelfNavDrawerItem() {
        return R.id.nav_login;
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }

    private boolean register() {
        BackendlessUser user = new BackendlessUser();
        EditText emailText = (EditText)findViewById(R.id.login_email);
        String emailStr = emailText.getText().toString().trim();
        EditText usernameText = (EditText)findViewById(R.id.login_username);
        String usernameStr = usernameText.getText().toString().trim();
        EditText passwordText = (EditText)findViewById(R.id.login_password);
        String passwordStr = passwordText.getText().toString();
        EditText confirmPasswordText = (EditText)findViewById(R.id.login_confirm_password);
        String confirmPasswordStr = confirmPasswordText.getText().toString();
        if (confirmPasswordStr.equals(passwordStr)) {
            user.setEmail(emailStr);
            user.setProperty("username", usernameStr);
            user.setPassword(passwordStr);
            Backendless.UserService.register(user, new BackendlessCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser backendlessUser) {
                    Log.i("Registration", backendlessUser.getEmail() + " successfully registered");
                }
            });
            return true;
        }
        return false;



    }

}