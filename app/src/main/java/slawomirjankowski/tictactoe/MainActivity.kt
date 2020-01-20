package slawomirjankowski.tictactoe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import slawomirjankowski.tictactoe.R.layout.activity_main
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        virginButtFill()

        back.setOnClickListener() {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_down, R.anim.slide_up)
        }
    }

    private val buttsToTouch = ArrayList<Button>()

    private var playerWins = 0
    private var cpuWins = 0

    private var winner = -1

    private var player = ArrayList<Int>()
    private var cpu = ArrayList<Int>()

    private var activePlayer = 1

    fun buttTouch(view: View){
        val buttSpanked = view as Button
        playGame(buttSpanked)
    }

    private fun virginButtFill(){
        buttsToTouch.add(butt11)

        buttsToTouch.add(butt00)
        buttsToTouch.add(butt02)
        buttsToTouch.add(butt20)
        buttsToTouch.add(butt22)

        buttsToTouch.add(butt01)
        buttsToTouch.add(butt10)
        buttsToTouch.add(butt12)
        buttsToTouch.add(butt21)
    }

    private fun playGame(buttSpanked: Button) {
        if (activePlayer == 1){
            buttSpanked.text = "X"
            buttSpanked.setBackgroundColor(Color.parseColor("#FF062699"))
            player.add(buttSpanked.id)
            buttSpanked.isEnabled = false
            activePlayer = 2
            checkWinner()
            if (winner != 1)
                autoPlay()
        } else {
            buttSpanked.text = "O"
            buttSpanked.setBackgroundColor(Color.parseColor("#FF253F19"))
            cpu.add(buttSpanked.id)
            buttSpanked.isEnabled = false
            activePlayer = 1
            checkWinner()
        }
    }

    private fun checkWinner() {
        winner = -1
        // row 1st
        if(player.contains(butt00.id)
                && player.contains(butt01.id)
                && player.contains(butt02.id)){
            winner=1
        }
        if(cpu.contains(butt00.id)
                && cpu.contains(butt01.id)
                && cpu.contains(butt02.id)){
            winner=2
        }
        // row 2nd
        if(player.contains(butt10.id)
                && player.contains(butt11.id)
                && player.contains(butt12.id)){
            winner=1
        }
        if(cpu.contains(butt10.id)
                && cpu.contains(butt11.id)
                && cpu.contains(butt12.id)){
            winner=2
        }
        // row 3rd
        if(player.contains(butt20.id)
                && player.contains(butt21.id)
                && player.contains(butt22.id)){
            winner=1
        }
        if(cpu.contains(butt20.id)
                && cpu.contains(butt21.id)
                && cpu.contains(butt22.id)){
            winner=2
        }
        // column 1st
        if(player.contains(butt00.id)
                && player.contains(butt10.id)
                && player.contains(butt20.id)){
            winner=1
        }
        if(cpu.contains(butt00.id)
                && cpu.contains(butt10.id)
                && cpu.contains(butt20.id)){
            winner=2
        }
        // column 2nd
        if(player.contains(butt01.id)
                && player.contains(butt11.id)
                && player.contains(butt21.id)){
            winner=1
        }
        if(cpu.contains(butt01.id)
                && cpu.contains(butt11.id)
                && cpu.contains(butt21.id)){
            winner=2
        }
        // column 3rd
        if(player.contains(butt02.id)
                && player.contains(butt12.id)
                && player.contains(butt22.id)){
            winner=1
        }
        if(cpu.contains(butt02.id)
                && cpu.contains(butt12.id)
                && cpu.contains(butt22.id)){
            winner=2
        }
        //diagonal 1st
        if(player.contains(butt00.id)
                && player.contains(butt11.id)
                && player.contains(butt22.id)){
            winner=1
        }
        if(cpu.contains(butt00.id)
                && cpu.contains(butt11.id)
                && cpu.contains(butt22.id)){
            winner=2
        }
        // diagonal 2nd
        if(player.contains(butt02.id)
                && player.contains(butt11.id)
                && player.contains(butt20.id)){
            winner=1
        }
        if(cpu.contains(butt02.id)
                && cpu.contains(butt11.id)
                && cpu.contains(butt20.id)){
            winner=2
        }

        if(winner != -1){
            if(winner==1){
                Toast.makeText(this," Player win the game", Toast.LENGTH_SHORT).show()
                playerWins++
            }else{
                Toast.makeText(this," Cpu win the game", Toast.LENGTH_SHORT).show()
                cpuWins++
            }
            for(butt in buttsToTouch){
                butt.isEnabled = false
            }
        }
        setPoints()
    }

    private fun autoPlay() {
        val virginButts = ArrayList<Button>()
        for (butt in buttsToTouch){
            if(!(player.contains(butt.id) || cpu.contains(butt.id)))
                virginButts.add(butt)
        }
        if (virginButts.isNotEmpty()) {
            val r = Random()
            val randIndex = r.nextInt(virginButts.size)
            val buttSpanked = virginButts[randIndex]
            playGame(buttSpanked)
        }
    }

    fun reset(view: View){
        activePlayer = 1
        virginButtFill()
        player.clear()
        cpu.clear()
        this.buttsToTouch.forEach { butt ->
            butt.isEnabled = true
            butt.text = ""
            butt.setBackgroundColor(Color.parseColor("#FFFF5722"))
        }
    }

    private fun setPoints(){
        pointsPlayer.text = "PLAYER: ${playerWins}"
        pointsCpu.text = "CPU: ${cpuWins}"
    }
}
