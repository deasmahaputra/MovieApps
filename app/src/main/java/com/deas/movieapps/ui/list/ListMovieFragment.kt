package com.deas.movieapps.ui.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.deas.movieapps.BR
import com.deas.movieapps.R
import com.deas.movieapps.base.BaseFragment
import com.deas.movieapps.common.PaginationScrollListener
import com.deas.movieapps.databinding.FragmentListMovieBinding
import com.deas.movieapps.network.Status
import com.deas.movieapps.room.LocalDatabase
import com.deas.movieapps.ui.MovieViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

class ListMovieFragment : BaseFragment<FragmentListMovieBinding, MovieViewModel>(), MovieListAdapter.onItemClickListener {

    @Inject
    internal lateinit var factoryModel: ViewModelProvider.Factory

    private lateinit var viewModel: MovieViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    private lateinit var binding : FragmentListMovieBinding
    private lateinit var layoutManager  : GridLayoutManager
    private lateinit var adapter : MovieListAdapter
    private var defPage = 1
    private val localDao by lazy { LocalDatabase.getInstance(requireContext())?.localDao() }

    override fun getViewModel(): MovieViewModel {
        viewModel = ViewModelProviders.of(this, factoryModel).get(MovieViewModel::class.java)
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){
        layoutManager = GridLayoutManager(requireContext(), 2)
        binding.movieRv.layoutManager = layoutManager
        adapter = MovieListAdapter(requireContext(), this)
        binding.movieRv.adapter = adapter
        binding.movieRv.addOnScrollListener(scrollData())
        viewModel.fetchListMovie(defPage)

        viewModel.movieList.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.Loading -> showProgressDialog()
                Status.Success -> {
                    dismissProgressDialog()
                    it.data?.let { it1 -> adapter.setDataList(it1.results) }
                }
                Status.Error -> {
                    dismissProgressDialog()
                    showSimpleAlertDialog("Error", it.message?:"")
                }
            }
        })
    }

    override fun itemClicked(movieId: Int) {
        val bundle = bundleOf("movieId" to movieId.toString())
        view?.findNavController()?.navigate(R.id.view_movie_details, bundle)
    }

    private fun scrollData(): PaginationScrollListener {
        return object : PaginationScrollListener() {
            @SuppressLint("CheckResult")
            override fun onLoadMore() {
                binding.progressBar.visibility = View.VISIBLE
                Completable
                    .timer(3, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        defPage += 1
                        viewModel.fetchListMovie(defPage)
                    }, {
                        showSimpleAlertDialog("", "Reopen Apps")
                    })
            }
        }
    }


}