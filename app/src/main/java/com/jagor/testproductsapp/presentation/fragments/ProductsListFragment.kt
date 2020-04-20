package com.jagor.testproductsapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jagor.testproductsapp.R
import com.jagor.testproductsapp.domain.repositories.Failure
import com.jagor.testproductsapp.presentation.adapters.ProductsListAdapter
import com.jagor.testproductsapp.presentation.changeVisibilityWithGone
import com.jagor.testproductsapp.presentation.makeToast
import com.jagor.testproductsapp.presentation.viewmodels.ProductListViewModel
import kotlinx.android.synthetic.main.fragment_list_products.*

class ProductsListFragment : Fragment() {

    lateinit var viewModel: ProductListViewModel
    lateinit var adapter: ProductsListAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductListViewModel::class.java)
        bindViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        sr_reload.setOnRefreshListener {
            adapter.products.clear()
            viewModel.onRefresh()
            sr_reload.isRefreshing = false
        }
    }

    private val onItemClicked: (id: String) -> Unit = {
        val action = ProductsListFragmentDirections.actionFragmentListProductsToFragmentProduct()
        action.productId = it
        findNavController().navigate(action)
    }

    private fun setAdapter() {
        adapter =
            ProductsListAdapter(
                arrayListOf(),
                onItemClicked
            )
        adapter.apply {
            rv_products_list?.layoutManager = LinearLayoutManager(context)
            rv_products_list?.adapter = adapter
        }
    }

    private fun bindViewModel() {
        viewModel.products.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.updateProducts(it)
            }
        })
        viewModel.loadingProgressBar.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading.let {
                pb_progress.changeVisibilityWithGone(it)
                rv_products_list.changeVisibilityWithGone(!it)
            }
        })

        viewModel.failure.observe(viewLifecycleOwner, Observer { handleFailure(it) })
    }

    private fun handleFailure(f: Failure?) {
        when (f) {
            is Failure.NetworkFailure -> makeToast(getString(R.string.product_download_products_error, f.t.localizedMessage))
            is Failure.UnknownFailure -> makeToast(getString(R.string.product_unknown_error))
        }
        pb_progress.changeVisibilityWithGone(false)
    }
}