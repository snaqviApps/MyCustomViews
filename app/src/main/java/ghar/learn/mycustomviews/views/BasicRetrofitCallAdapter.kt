package ghar.learn.mycustomviews.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ghar.learn.mycustomviews.databinding.CustomviewfirstBinding
import ghar.learn.mycustomviews.model.GithubUserProfile
import kotlin.reflect.full.memberProperties

class BasicRetrofitCallAdapter(private val userprofile: GithubUserProfile?) : RecyclerView.Adapter<BasicRetrofitCallAdapter.GithubProfileViewHolder>() {

    class GithubProfileViewHolder(
        private val binding : CustomviewfirstBinding):RecyclerView.ViewHolder(binding.root) {
        fun bindData(githubUserProfile: GithubUserProfile) {
            binding.cvTextViewXML.text = githubUserProfile.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubProfileViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CustomviewfirstBinding.inflate(layoutInflater)
        return GithubProfileViewHolder(binding)
    }

    // check if that is a valid utilization of getItemCount() callback
    override fun getItemCount(): Int {
        lateinit var pair: Pair<String, Any?>
        val totalProperties = userprofile?.describeContents() as Int
        for (i in 0..totalProperties) {
            userprofile::class.memberProperties.forEach { member ->
                member.typeParameters.let {
                    pair = Pair(member.name, member.returnType)
                }
            }
        }
        return pair.toList().size
    }

    override fun onBindViewHolder(holder: GithubProfileViewHolder, position: Int) {
//        TODO("Not yet implemented")
        userprofile?.let {
            holder.bindData(it)
        }
    }
}