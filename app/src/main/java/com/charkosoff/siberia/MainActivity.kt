package com.charkosoff.siberia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MaterialTapTargetPrompt.Builder(this)
            .setTarget(R.id.button)
            .setPrimaryText("Нажмите, чтобы продемонстрировать свою способность читать")
            .setPromptStateChangeListener { prompt, state ->
                if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED)
                {
                    // User has pressed the prompt target
                }
            }
            .show()
    }
}


