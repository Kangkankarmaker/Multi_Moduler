package kk.example.multimoduler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(), DynamicDeliveryCallback  {

    //this is for fork test


    private val CUSTOMER_SUPPORT_DYNAMIC_MODULE = "onDemand"
    private lateinit var dynamicModuleDownloadUtil: DynamicModuleDownloadUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dynamicModuleDownloadUtil = DynamicModuleDownloadUtil(baseContext, this)


        val btnInstall: Button = findViewById(R.id.btn_install)
        btnInstall.setOnClickListener {
            val intent = Intent()
            intent.setClassName(this, "kk.example.oninstall.MainActivity")
            startActivity(intent)
        }


        val btnDemand: Button = findViewById(R.id.btn_demand)
        btnDemand.setOnClickListener {
            openCustomerSupportFeature()
        }


    }

    private fun openCustomerSupportFeature() {
        if (dynamicModuleDownloadUtil.isModuleDownloaded(CUSTOMER_SUPPORT_DYNAMIC_MODULE)) {
            Log.e(TAG, "Module is already downloaded" )
            startCustomerSupportActivity()
        } else {
            dynamicModuleDownloadUtil.downloadDynamicModule(CUSTOMER_SUPPORT_DYNAMIC_MODULE)
        }
    }

    private fun startCustomerSupportActivity() {
        val intent = Intent()
        intent.setClassName(
            this,
            "kk.example.ondemand.MainActivity"
        )
        startActivity(intent)
    }

    override fun onDownloading() {
        Log.e(TAG, "Downloading" )
    }

    override fun onDownloadCompleted() {
        Log.e(TAG, "Module download completed." )

    }

    override fun onInstallSuccess() {
        Log.e(TAG, "Module install Success!" )
        startCustomerSupportActivity()
    }

    override fun onFailed(errorMessage: String) {
        Log.e(TAG, "Module download or installation failed" )

    }
}