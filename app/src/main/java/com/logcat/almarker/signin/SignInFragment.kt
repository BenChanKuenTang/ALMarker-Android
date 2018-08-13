package com.logcat.almarker.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.logcat.almarker.R
import com.logcat.almarker.base.BaseFragment
import com.logcat.ui_module.action_bar.ActionBar
import kotlinx.android.synthetic.main.fragment_test.*

class SignInFragment : BaseFragment() {
    private var mGoogleSignInClient: GoogleSignInClient? = null

    companion object {
        private val REQUEST_CODE_GOOGLE_SIGN_IN = 101
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        context?.let { context ->
            mGoogleSignInClient = GoogleSignIn.getClient(context, gso)
            val account = GoogleSignIn.getLastSignedInAccount(context)
            account?.let {
                authResult(it)
            }
        }

        btnSignIn.setSize(SignInButton.SIZE_STANDARD)
        btnSignIn.setOnClickListener {
            val signInIntent = mGoogleSignInClient?.signInIntent
            startActivityForResult(signInIntent, REQUEST_CODE_GOOGLE_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            authResult(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            authResult(null)
        }
    }

    private fun authResult(account: GoogleSignInAccount?) {
        account?.let {
            Toast.makeText(context, "success", Toast.LENGTH_LONG).show()
        } ?: Toast.makeText(context, "fail", Toast.LENGTH_LONG).show()
    }

    override fun getActionBarContainer(): ViewGroup? = null

    override fun getActionBarBuilder(): ActionBar.Builder? = null
}