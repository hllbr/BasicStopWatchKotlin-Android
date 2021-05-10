package com.hllbr.basicstopwatchwithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var number = 0
    var runnable : Runnable = Runnable {  }
    var handler : Handler = Handler()
    /*

    Elimizde şuan boş bir handler ve runnable bulunmakta
     */
   // lateinit var handler : Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
            Runnable ile çalışmak için iki objeye ihtiyacım var
            1) Runnable = bir sınıf değil bir arayüzdür.Arayüzler %100 soyuttur.
            2) Handler = Runnable objelerini kullanmak (ele almak) için kullandğımız objeler
            Handler bir sınıf Runnable gibi bir arayüz değildir.

         */

    }

    fun start(view: View) {
        number = 0 //Her start butonuna basıldığında 0 dan başlamasını istiyorum her seferinde resetlensin

        //Runnable initialize operation =
        runnable = object : Runnable{
            override fun run() {
                //Bu alana ne yazarsam arkaplanda benim için çalıştırılacak
                number += 1
                textView.text = "TIME : ${number}"
                handler.postDelayed(this,1000)//postDelayed = gecikmeli şekilde başlat(rötarlı) şekilde
                //This anahtar kelimesi genelde üst yapıya referans verirken böyle yapıalrda runnable'a referans verebiliyor.

            }

        } //Soyut sınıf olduğu için CountDownTimer gibi tanımlamam gerekiyor
        //Bu aşanda runnable işlemlerim bitmiş oldu şimdi handler ile runnable ayağa kaldırmam gerekiyor
        handler.post(runnable)


    }

    fun stop(view: View) {
        handler.removeCallbacks(runnable)
        number = 0
        textView.text = "Time Stoped "
    }
}
/*
Bu yapıyı sadece kronometre yapmak için yada basic bir sayaç için değil periyodik olarak arkaplanda bir işlemi yapmak için kullanmak çok effectif oluyor.

 */