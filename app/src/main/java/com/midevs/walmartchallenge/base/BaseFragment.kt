package com.midevs.walmartchallenge.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

    private var viewDataBinding: T? = null

    private var snackbar: Snackbar? = null

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V


    override fun onCreateView(
        @NonNull inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding!!.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.snackBarMessage.observe(this, Observer { message ->
            if (message != null) showSnackbar(message) else hideSnackbar()
        })
    }

    fun getViewDataBinding(): T {
        return viewDataBinding!!
    }

    fun showSnackbar(message: String) {
        snackbar = Snackbar.make(viewDataBinding!!.root, message, Snackbar.LENGTH_SHORT)
        snackbar?.show()
    }

    fun showSnackbarWithButton(
        message: String,
        actionText: String,
        onClickListener: View.OnClickListener
    ) {
        snackbar = Snackbar.make(viewDataBinding!!.root, message, Snackbar.LENGTH_INDEFINITE)
        snackbar?.setAction(actionText, onClickListener)
        snackbar?.show()
    }

    fun hideSnackbar() {
        snackbar?.dismiss()
    }

    fun <T> Fragment.getNavigationResult(key: String = "result") =
        findNavController().currentBackStackEntry?.savedStateHandle?.get<T>(key)

    fun <T> Fragment.getNavigationResultLiveData(key: String = "result") =
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)

    fun <T> Fragment.setNavigationResult(result: T, key: String = "result") {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
    }
}