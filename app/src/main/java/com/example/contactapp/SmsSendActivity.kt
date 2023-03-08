package com.example.contactapp

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import com.example.contactapp.databinding.ActivitySmsSendBinding
import com.example.contactapp.databinding.ItemBinding
import com.example.contactapp.models.MyShablon

class SmsSendActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySmsSendBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Toast.makeText(this, "${MyData.user.number}", Toast.LENGTH_SHORT).show()
        binding.btnSend.setOnClickListener {
            sendSMS(MyData.user.number.toString(), binding.edtKursName.text.toString())
            Toast.makeText(this, "Jo'natildi!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    private fun sendSMS(phoneNumber: String, message: String) {
        SmsManager.getDefault( ).sendTextMessage(phoneNumber, null, message, null, null)
    }
}