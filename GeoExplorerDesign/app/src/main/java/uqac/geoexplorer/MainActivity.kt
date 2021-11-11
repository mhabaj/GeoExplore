package uqac.geoexplorer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.View

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends)
    }
    fun GenerateQrCode(view: View?) {
        val intent = Intent(this, QrCodeActivity::class.java)
        startActivity(intent)

    }

    fun ScanQrCode(view: View?) {
        val intent = Intent(this, ReadQrCode::class.java)
        startActivity(intent)

    }
}