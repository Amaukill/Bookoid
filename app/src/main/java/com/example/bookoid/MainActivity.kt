package com.example.bookoid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth;
        var mail = findViewById<EditText>(R.id.mail);
        var password = findViewById<EditText>(R.id.password);
        findViewById<Button>(R.id.login).setOnClickListener() {
            if (!mail.text.toString().contains("@")) {
                Toast.makeText(this, "l'email est invalide", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(mail.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Vous êtes connecté",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                this,
                                "Mauvais Email ou Mot de passe",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }



}

