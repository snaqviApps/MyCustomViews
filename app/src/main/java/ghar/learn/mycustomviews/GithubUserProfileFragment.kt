package ghar.learn.mycustomviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ghar.learn.mycustomviews.api.BackEndRepository
import ghar.learn.mycustomviews.databinding.FragmentGithubUserProfileBinding
import ghar.learn.mycustomviews.views.BasicRetrofitCallAdapter
import ghar.learn.mycustomviews.views.BasicRetrofitCallViewModel
import ghar.learn.mycustomviews.views.BasicRetrofitCallViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GithubUserProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GithubUserProfileFragment : Fragment(R.layout.fragment_github_user_profile) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var fragmentGithubUserProfileBinding : FragmentGithubUserProfileBinding
    private lateinit var fragmentViewModel: BasicRetrofitCallViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        return inflater.inflate(R.layout.fragment_github_user_profile, container, false).rootView     // no binding used
        fragmentGithubUserProfileBinding = FragmentGithubUserProfileBinding.inflate(layoutInflater)
        fragmentViewModel = ViewModelProvider(this, BasicRetrofitCallViewModelFactory(repository = BackEndRepository()))[BasicRetrofitCallViewModel::class.java]
        rvSetup()
        setUserDataOnRView()
        return fragmentGithubUserProfileBinding.root

    }
    private fun rvSetup() {
//        TODO("Not yet implemented")
        fragmentGithubUserProfileBinding.fragUseProfileViewModel = fragmentViewModel
        fragmentGithubUserProfileBinding.lifecycleOwner = viewLifecycleOwner
    }


    private fun setUserDataOnRView() {
//        TODO("Not yet implemented")

        fragmentViewModel.uiDataProvider?.observe(viewLifecycleOwner, Observer { githuUser ->
            val adapter = BasicRetrofitCallAdapter(githuUser)
            fragmentGithubUserProfileBinding.rvUserData.adapter = adapter
        })

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GithubUserProfile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GithubUserProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}