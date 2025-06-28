package ru.netology.handler

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import ru.netology.handler.databinding.ActivityMainBinding
import java.io.IOException
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val urls = listOf("netology.jpg", "sber.jpg", "tcs.jpg", "404.png")
    private var index = 0
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.load.setOnClickListener {
            if (index == urls.size) {
                index = 0
            }

            val url = "http://10.0.2.2:9999/avatars/${urls[index++]}"
            binding.image.load(url)
           // val request = Request.Builder()
             //   .url(url)
               // .build()

         //   client.newCall(request)
           //     .enqueue(
             //       object : Callback {
               //         override fun onFailure(call: Call, e: IOException) {
                 //           e.printStackTrace()
                   //     }

                     //   override fun onResponse(call: Call, response: Response) {
                       //     val bitmap = BitmapFactory.decodeStream(response.body?.byteStream())
                         //   runOnUiThread {
                           //     binding.image.setImageBitmap(bitmap)
                        //    }
                    //    }
                  //  }
              //  )
        }
    }
}

// */

/* Second version
class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()
    private val urls = listOf("netology.jpg", "sber.jpg", "tcs.jpg")
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = Handler(Looper.getMainLooper()) { message ->
            // only for demo
            val bitmap = message.data["image"] as Bitmap
            binding.image.setImageBitmap(bitmap)
            return@Handler true
        }

        binding.load.setOnClickListener {
            if (index == urls.size) {
                index = 0
            }

            val url = "http://10.0.2.2:9999/avatars/${urls[index++]}"
            val request = Request.Builder()
                .url(url)
                .build()

            client.newCall(request)
                .enqueue(object : Callback {
                    override fun onResponse(call: Call, response: Response) {
                        response.body?.use {
                            val bitmap = BitmapFactory.decodeStream(it.byteStream())
                            val message = handler.obtainMessage().apply {
                                data = bundleOf("image" to bitmap)
                            }
                            handler.sendMessage(message)
                        }
                    }

                    override fun onFailure(call: Call, e: IOException) {
                        TODO("Not yet implemented")
                    }
                })
        }
    }
}
 */

/* Third variant
class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()
    private val urls = listOf("netology.jpg", "sber.jpg", "tcs.jpg")
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.load.setOnClickListener {
            if (index == urls.size) {
                index = 0
            }

            val url = "http://10.0.2.2:9999/avatars/${urls[index++]}"
            val request = Request.Builder()
                .url(url)
                .build()

            client.newCall(request)
                .enqueue(object : Callback {
                    override fun onResponse(call: Call, response: Response) {
                        response.body?.use {
                            val bitmap = BitmapFactory.decodeStream(it.byteStream())
                            this@MainActivity.runOnUiThread {
                                binding.image.setImageBitmap(bitmap)
                            }
                        }
                    }

                    override fun onFailure(call: Call, e: IOException) {
                        TODO("Not yet implemented")
                    }
                })
        }
    }
}
 */

