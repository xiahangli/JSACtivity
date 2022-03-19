package com.example.jsactivity

import android.os.*
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.jsactivity.pluginimpl.qspanel.dagger.SubModule
import com.example.mylibrary.Inner
import java.io.File
import java.io.IOException

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() , com.example.mylibrary.Contract {
    private lateinit var iv: ImageView
    private lateinit var iv1: ImageView
    private lateinit var iv2: ImageView
    private lateinit var ll: LinearLayout
    private lateinit var threadHandler: Handler
    private lateinit var mainHandler: Handler

    private val REFRESH = 1;
    private val SLEEP = 2;
    private lateinit var inn: Inner;
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SubModule::javaClass
        val re = SubModule()
        re.doFunc(this);
        val fi = File("te")
        try {
            var createNewFile = fi.createNewFile()
            fi.deleteOnExit();
        } catch (e: IOException) {
        }
//        finally {
//        }
//        re.doFunc()
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        iv = findViewById<ImageView>(R.id.iv)
        iv1 = findViewById<ImageView>(R.id.iv1)
        ll = findViewById<LinearLayout>(R.id.ll)
        iv.setOnClickListener {

        }


        mainHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Log.e(TAG, "handleMessage: mainHandler " + Thread.currentThread().name)
                super.handleMessage(msg)
                logImageLocation(iv2)

            }
        }


//        val a = object AnimatorListenerAdapter {
//
//        }
         inn = Inner();
        inn.setListener(this)
        inn.callOuter()
val outer = Outer()
        outer.setA()
        /*var con :BiConsumer<File, ZipEntry>  = (BiConsumer<File, ZipEntry>
        { _, _-> TODO("Not yet implemented") }
                )*/


        val handlerThread = HandlerThread("bg")
        handlerThread.start()
        threadHandler = object : Handler(handlerThread.looper) {
            override fun handleMessage(msg: Message) {
                Log.e(TAG, "handleMessage: threadHandler " + Thread.currentThread().name)
                when(msg.what) {
                    SLEEP -> {
                        Thread.sleep(1000)
                        mainHandler.sendEmptyMessage(REFRESH)
                    }
//                    else
                }
            }
        }
        val stringBuilder = StringBuilder()
        val string:String = ""
        with(string, {
            this.plus("1111")
        })


        stringBuilder.also {
             it.append("also")
            return@also
        }.run {
           return@run this.append(",run")
        }.let {
          return@let  it.append(",let")
            //1
        }.apply {
            this.append(",apply")
//            return@apply 1
        }


    }



    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val a = IntArray(2)
        iv.getLocationInWindow(a)
        Log.e(TAG, "onCreate: x = " + a[0] + " y = "+a[1] )
        val a1 = IntArray(2)
        iv1.getLocationInWindow(a1)
        Log.e(TAG, "onCreate: x1 = " + a1[0] + " y1 = " + a1[1] )
        iv2 = ImageView(this)
        iv2.setImageDrawable(resources.getDrawable(R.drawable.ic_launcher_background))
        ll.addView(iv2)
        threadHandler.sendEmptyMessage(SLEEP)

//        logImageLocation(iv2)
//        iv2.post {
//            val a2 = IntArray(2)
//            iv2.getLocationOnScreen(a2)
//            val parent = iv2.parent
//            Log.e(TAG, "onCreate1: x2 = " + a2[0] + " y2 = "+a2[1] + ", parent="+parent )
//        }
    }

    private fun logImageLocation(iv2: ImageView) {
        val a2 = IntArray(2)
        iv2.getLocationInWindow(a2)
        val parent = iv2.parent
        Log.e(TAG, "logImageLocation: x2 = " + a2[0] + " y2 = " + a2[1] + ", parent=" + parent)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun callFromLib() {
        inn.setLambda { _, _ -> Log.e(TAG, "callFromLib: " ) }
        reload {
            Log.e(TAG, "callFromLib: invoke $it")
        }
    }

    private fun reload(sth :(String) -> Unit) {
        sth.invoke("test")
        sth("test2")
    }
}

class A {
    fun main() {
        val a: B = object : B {
            override fun b() {
                super.b()
            }
        }
        val a1: C = object : C() {
            override fun c() {

            }
        }
    }

    interface B {
        fun b() {

        }
    }
    open class C {
        open fun c() {

        }
    }

}

