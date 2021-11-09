package uqac.geoexplorer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    fun SignUp(view: View?) {
        val intent = Intent(this, Sign_up::class.java)
        startActivity(intent)

    }

    fun LogIn(view: View?) {
        val intent = Intent(this, sign_in::class.java)
        startActivity(intent)

    }
}

