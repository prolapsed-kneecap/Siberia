package com.charkosoff.siberia

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.charkosoff.siberia.utils.StatusUtils
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetSequence

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (StatusUtils.getStatus(this)) {
            TapTargetSequence(this)
                .targets(
                    TapTarget.forView(findViewById<Button>(R.id.texttt), "Нажмите, чтобы продемонстрировать свою способность читать")
                        .cancelable(false).transparentTarget(true).targetRadius(70)
                        .tintTarget(true).outerCircleColor(R.color.second_main)).listener(object : TapTargetSequence.Listener {
                    override fun onSequenceStep(lastTarget: TapTarget?, targetClicked: Boolean) {
                    }

                    override fun onSequenceFinish() {
                        StatusUtils.storeStatus(this@MainActivity, false)
                    }

                    override fun onSequenceCanceled(lastTarget: TapTarget) {
                    }
                }).start()
        }

    }
}


