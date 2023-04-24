package ghar.learn.mycustomviews.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ghar.learn.mycustomviews.R
import ghar.learn.mycustomviews.databinding.CustomviewfirstBinding
import ghar.learn.mycustomviews.model.GithubUserProfile
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlin.reflect.full.memberProperties

class BasicRetrofitCallAdapter(private val userprofile: GithubUserProfile?) : RecyclerView.Adapter<BasicRetrofitCallAdapter.GithubProfileViewHolder>() {

    class GithubProfileViewHolder(private val custombinding : CustomviewfirstBinding) :
        RecyclerView.ViewHolder(custombinding.root) {
//        fun bindData(githubUserProfile: GithubUserProfile) {
        fun bindData(githubUserProfile: GithubUserProfile) {
            custombinding.cvTextViewXML.text = githubUserProfile.name

            /** use the Picasso to load the Image */
//            binding.cvImageViewXML.setImageResource(R.drawable.ic_deer_from_png_background)
            custombinding.cvTextViewXMLCompany.text = githubUserProfile.company
            custombinding.cvTextViewXmlGisTs.text = githubUserProfile.gistsUrl
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubProfileViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CustomviewfirstBinding.inflate(layoutInflater)
        return GithubProfileViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userprofile?.publicRepos ?: -1
    }

    // check if that is a valid utilization of getItemCount() callback


    override fun onBindViewHolder(holder: GithubProfileViewHolder, position: Int) {
        userprofile?.let {
            holder.bindData(it)
        }
    }
}