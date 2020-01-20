package slawomirjankowski.tictactoe

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlin.system.exitProcess
import kotlinx.android.synthetic.main.main_menu.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        play.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_down, R.anim.slide_up)
        }
    }

    fun exit(view: View){
        finishAffinity()
        exitProcess(0)
    }
}
