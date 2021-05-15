package com.charkosoff.siberia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.charkosoff.siberia.utils.StatusUtils
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetSequence
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (StatusUtils.getStatus(this)) {
            TapTargetSequence(this)
                .targets(
                    TapTarget.forView(findViewById<Button>(R.id.button), "Кнопка Входа")
                        .cancelable(false).transparentTarget(true).targetRadius(70)
                        .tintTarget(true)).listener(object : TapTargetSequence.Listener {
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


