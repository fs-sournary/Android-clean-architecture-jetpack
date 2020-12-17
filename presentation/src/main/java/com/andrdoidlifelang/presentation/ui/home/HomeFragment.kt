package com.andrdoidlifelang.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.FragmentHomeBinding
import com.andrdoidlifelang.presentation.ui.base.BaseMainNavigationFragment
import com.andrdoidlifelang.presentation.util.Constant.ANALYTICS_HOME_SCREEN_NAME
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseMainNavigationFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_home

    override val screenName: String = ANALYTICS_HOME_SCREEN_NAME

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firestore = Firebase.firestore
        binding.btAdd.setOnClickListener {
            val user = hashMapOf(
                "first" to "Ada",
                "last" to "Lovelace",
                "born" to UUID.randomUUID().toString()
            )
            firestore.collection("test")
                .add(user)
                .addOnSuccessListener {
                    Timber.d("DocumentSnapshot was added with ID: ${it.id}")
                }
                .addOnFailureListener {
                    Timber.d("Error while adding DocumentSnapshot: $it")
                }
        }
        binding.btGet.setOnClickListener {
            firestore.collection("test")
                .get()
                .addOnSuccessListener { snapshots ->
                    snapshots.forEach {
                        Timber.d("${it.id} => ${it.data}")
                    }
                }
                .addOnFailureListener {
                    Timber.d("Error getting document data: $it")
                }
        }
        firestore.collection("test").addSnapshotListener { value, error ->
            Timber.d("$value => $error")
        }
    }
}
