package site.pnpl.igotit.view

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kotlinx.coroutines.launch
import site.pnpl.igotit.R
import site.pnpl.igotit.databinding.ActivityMainBinding
import site.pnpl.igotit.utils.uiextensions.hide
import site.pnpl.igotit.utils.uiextensions.show

class MainActivity : AppCompatActivity(), OnHeaderChangeListener {
    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        NavHostFragment.findNavController(supportFragmentManager.findFragmentById(R.id.fragment_placeholder) as NavHostFragment)
    }

    private val fragmentsWithoutToolbars = listOf(
        R.id.authFragment,
        R.id.introFragment,
        R.id.regFragment,
        R.id.restoreFragment,
    )

    private val fragmentsWithoutHeader = listOf(
        R.id.introFragment,
    )

    private val fragmentsWithAvatar = listOf(
        R.id.homeFragment,
    )

    private val fragmentsWithoutBackArrow = listOf(
        R.id.authFragment,
        R.id.homeFragment,
        R.id.catalogueFragment,
        R.id.profileFragment,
        R.id.myLessonFragment,

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setupWithNavController(navController)

        initMenu()
        initShowOrHideMainBottomBar()
        initShowOrHideHeader()

        binding.arrLeft.setOnClickListener {
            onBackClick()
        }
    }

    private fun initMenu() {
        with(binding) {
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeFragment -> {
                        navController.navigate(R.id.homeFragment)
                        true
                    }

                    R.id.myLessonFragment -> {
                        navController.navigate(R.id.myLessonFragment)
                        true
                    }

                    R.id.catalogueFragment -> {
                        navController.navigate(R.id.catalogueFragment)
                        true
                    }

                    R.id.profileFragment -> {
                        navController.navigate(R.id.profileFragment)
                        true
                    }

                    else -> false
                }
            }
        }
    }


    private fun initShowOrHideHeader() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    with(binding) {
                        if (fragmentsWithoutHeader.contains(destination.id)) {
                            header.hide()
                        } else {
                            header.show()
                        }

                        if (fragmentsWithoutBackArrow.contains(destination.id)) {
                            arrLeft.hide()
                        } else {
                            arrLeft.show()
                        }

                        if (fragmentsWithAvatar.contains(destination.id)) {
                            avatar.show()
                        } else {
                            avatar.hide()
                        }
                    }
                }
            }
        }
    }

    private fun initShowOrHideMainBottomBar() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    with(binding) {
                        if (fragmentsWithoutToolbars.contains(destination.id)) {
                            bottomNavigation.hide()
                        } else {
                            bottomNavigation.show()
                        }
                    }
                }
            }
        }
    }

    override fun onTitleTextChange(newTitle: String) {
        binding.headerTitle.text = newTitle
    }

    override fun onBackClick() {
        navController.popBackStack()
    }

    override fun hideBackArrow() {
        binding.arrLeft.hide()
    }

    override fun showBackArrow() {
        binding.arrLeft.show()
    }

    override fun hideAvatar() {
        binding.avatar.hide()
    }

    override fun showAvatar() {
        binding.avatar.show()
    }

    override fun onTitleTextChange(idRes: Int) {
        try {
            binding.headerTitle.setText(idRes)
        } catch (e: Resources.NotFoundException) {
            binding.headerTitle.text = ""
        }
    }
}