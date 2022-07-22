package com.example.bookoid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText

import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth

import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        val mail = findViewById<EditText>(R.id.mail)
        val password = findViewById<EditText>(R.id.password)
        findViewById<Button>(R.id.login).setOnClickListener{
            if (!mail.text.toString().contains("@")) {
                displayToast(getString(R.string.InvalidMail))
            }
            else if (mail.text.toString().isEmpty()){
                displayToast(getString(R.string.EmptyMail))
            }
            else if (password.text.toString().isEmpty()){
                displayToast(getString(R.string.EmptyPassword))
            }
            else {
                auth.signInWithEmailAndPassword(mail.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            displayToast(getString(R.string.Connected))
                            val intent = Intent(this,ListBookActivity::class.java)

                            startActivity(intent)

                        } else {
                            displayToast(getString(R.string.ErrorConnection))
                        }
                    }
            }

        }

    }

    override fun onRestart() {
        super.onRestart()
        findViewById<EditText>(R.id.mail).text = null
        findViewById<EditText>(R.id.password).text = null


    }
    private fun displayToast(message : String){
        AlertDialog.Builder(this).create().run{
            setMessage(message)
            show()
        }
    }


}

