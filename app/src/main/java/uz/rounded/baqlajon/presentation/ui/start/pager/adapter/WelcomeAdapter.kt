package uz.rounded.baqlajon.presentation.ui.start.pager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.getImage
import uz.rounded.baqlajon.core.extensions.getString

class WelcomeAdapter(val context: Context, private val list: List<PagerModel>) :
    PagerAdapter() {
    private lateinit var layoutInflater: LayoutInflater
    override fun getCount(): Int = list.size


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.view_pager_item, container, false)
        val imageView: ImageView = view.findViewById(R.id.pager_image)
        val title: TextView = view.findViewById(R.id.pager_title)
        val description: TextView = view.findViewById(R.id.pager_description)
        val data = list[position]
        title.text = getString(data.title)
        description.text = getString(data.description)
        imageView.setImageDrawable(getImage(context, data.image))

        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
}