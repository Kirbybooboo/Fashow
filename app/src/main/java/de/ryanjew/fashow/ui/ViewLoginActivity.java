package de.ryanjew.fashow.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.UserService;
import com.backendless.exceptions.BackendlessException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import de.ryanjew.fashow.R;
import de.ryanjew.fashow.ui.base.BaseActivity;

public class ViewLoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setupToolbar();
    }

    public void logInToAccount(View view) {
        BackendlessUser user;
        EditText usernameText = (EditText)findViewById(R.id.login_username);
        String username = usernameText.getText().toString().trim();
        EditText passwordText = (EditText)findViewById(R.id.login_password);
        String password = passwordText.getText().toString().trim();
        try
        {
            user = Backendless.UserService.login( username, password, true);
        }
        catch( BackendlessException exception )
        {
            // login failed, to get the error code, call exception.getFault().getCode()
        }

        Snackbar.make(view, "Log in successful!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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

    public void goToRegisterActivity(View view) {
        startActivity(new Intent(this, ViewRegisterActivity.class));
    }
}
