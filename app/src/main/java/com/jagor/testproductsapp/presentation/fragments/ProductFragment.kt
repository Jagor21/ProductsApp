package com.jagor.testproductsapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jagor.testproductsapp.EMPTY
import com.jagor.testproductsapp.R
import com.jagor.testproductsapp.domain.entities.Product
import com.jagor.testproductsapp.domain.repositories.Failure
import com.jagor.testproductsapp.presentation.changeVisibilityWithGone
import com.jagor.testproductsapp.presentation.loadImageCenterCrop
import com.jagor.testproductsapp.presentation.makeToast
import com.jagor.testproductsapp.presentation.viewmodels.ProductViewModel
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.android.synthetic.main.fragment_product.pb_progress
import kotlinx.android.synthetic.main.product_layout.*

class ProductFragment : Fragment() {

    lateinit var viewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        val safeArgs =
            arguments?.let { ProductFragmentArgs.fromBundle(it).productId } ?: String.EMPTY
        viewModel.getProduct(safeArgs)
        bindViewModel()
        sr_refresh.setOnRefreshListener {
            viewModel.onRefresh(safeArgs)
            sr_refresh.isRefreshing = false
        }
    }

    private fun bindViewModel() {
        viewModel.product.observe(viewLifecycleOwner, Observer { bindProduct(it) })

        viewModel.loadingProgressBar.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading.let {
                pb_progress.changeVisibilityWithGone(it)
                g_product.changeVisibilityWithGone(!it)
            }
        })

        viewModel.failure.observe(viewLifecycleOwner, Observer { handleFailure(it) })
    }

    private fun handleFailure(f: Failure?) {
        when (f) {
            is Failure.NetworkFailure -> makeToast(getString(
                    R.string.product_please_check_your_connection,
                    f.t.localizedMessage))
            else -> makeToast(getString(R.string.product_unknown_error))
        }
    }

    private fun bindProduct(product: Product) {
        tv_product_name.text = product.name
        tv_product_price.text = getString(R.string.product_tv_product_price, product.price)
        tv_product_description.text = getString(R.string.product_description, product.description)
        iv_product_image.loadImageCenterCrop(product.imageUrl)
    }
}