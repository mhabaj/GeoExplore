package uqac.geoexplorer
import android.Manifest.permission
import android.R.layout
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import eu.livotov.labs.android.camview.ScannerLiveView
import eu.livotov.labs.android.camview.ScannerLiveView.ScannerViewEventListener
import eu.livotov.labs.android.camview.scanner.decoder.zxing.ZXDecoder


class ReadQrCode  : AppCompatActivity() {
    private var camera: ScannerLiveView? = null
    private var scannedTV: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.read_qrcode)

        if (checkPermission()) {
            Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show()
        } else {
            requestPermission()
        }
        scannedTV = findViewById(R.id.idTVscanned)
        camera = findViewById<View>(R.id.camview) as ScannerLiveView
        camera!!.scannerViewEventListener = object : ScannerViewEventListener {
            override fun onScannerStarted(scanner: ScannerLiveView) {
                Toast.makeText(this@ReadQrCode, "Scanner Started", Toast.LENGTH_SHORT).show()
            }

            override fun onScannerStopped(scanner: ScannerLiveView) {
                Toast.makeText(this@ReadQrCode, "Scanner Stopped", Toast.LENGTH_SHORT).show()
            }

            override fun onScannerError(err: Throwable) {
                Toast.makeText(
                    this@ReadQrCode,
                    "Scanner Error: " + err.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onCodeScanned(data: String) {
                scannedTV!!.setText(data)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val decoder = ZXDecoder()
        // 0.5 is the area where we have
        // to place red marker for scanning.
        decoder.scanAreaPercent = 0.8
        // below method will set secoder to camera.
        camera!!.decoder = decoder
        camera!!.startScanner()
    }

    override fun onPause() {
        // on app pause the
        // camera will stop scanning.
        camera!!.stopScanner()
        super.onPause()
    }

    private fun checkPermission(): Boolean {
        // here we are checking two permission that is vibrate
        // and camera which is granted by user and not.
        // if permission is granted then we are returning
        // true otherwise false.
        val camera_permission =
            ContextCompat.checkSelfPermission(applicationContext, permission.CAMERA)
        val vibrate_permission =
            ContextCompat.checkSelfPermission(applicationContext, permission.VIBRATE)
        return camera_permission == PackageManager.PERMISSION_GRANTED && vibrate_permission == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        // this method is to request
        // the runtime permission.
        val PERMISSION_REQUEST_CODE = 200
        ActivityCompat.requestPermissions(
            this,
            arrayOf(permission.CAMERA, permission.VIBRATE),
            PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        // this method is called when user
        // allows the permission to use camera.
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size > 0) {
            val cameraaccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
            val vibrateaccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED
            if (cameraaccepted && vibrateaccepted) {
                Toast.makeText(this, "Permission granted..", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Permission Denined \n You cannot use app without providing permission",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
