package com.example.contactapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import com.example.contactapp.adapters.RvAdapter
import com.example.contactapp.adapters.RvClick
import com.example.contactapp.databinding.ActivityMainBinding
import com.example.contactapp.databinding.ItemBinding
import com.example.contactapp.models.MyShablon

class MainActivity : AppCompatActivity(), RvClick {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var rvAdapter: RvAdapter
    lateinit var list:ArrayList<MyShablon>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        list = readContactFromPhone() as ArrayList
        rvAdapter = RvAdapter(list,this)
        binding.myRv.adapter = rvAdapter
    }
    private fun readContactFromPhone(): List<MyShablon> {

        val contactsList = ArrayList<MyShablon>()
        val contacts = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null, null
        )!!
        if (contacts.moveToFirst()) {
            do {
                val index1 =
                    contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val index2 = contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                contactsList.add(
                    MyShablon(
                        contacts.getString(index1),  contacts.getString(index2)
                    )
                )
            } while (contacts.moveToNext())
        }
        contacts.close()
        contactsList.sortWith((compareBy { it.name }))
        return contactsList
    }

    override fun itemClick(myShablon: MyShablon) {
        MyData.user.number = myShablon.number
        val intent = Intent(this, SmsSendActivity::class.java)
        startActivity(intent)
    }

    override fun itemClick2(myShablon: MyShablon) {
        MyData.user.number = myShablon.number
        val intent = Intent(Intent.ACTION_CALL);
        intent.data = Uri.parse("tel:${MyData.user.number}")
        startActivity(intent)
    }

    override fun itemSwipe(position: Int) {
        val itemBinding = ItemBinding.inflate(layoutInflater)
        if (itemBinding.swipe.isSwipeEnabled){
            itemBinding.swipe.close()
        }
    }
}