package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.video.viewholder

import android.text.method.LinkMovementMethod
import androidx.core.content.ContextCompat
import org.sufficientlysecure.htmltextview.HtmlFormatter
import org.sufficientlysecure.htmltextview.HtmlFormatterBuilder
import org.sufficientlysecure.htmltextview.HtmlResImageGetter
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.utils.Constants
import uz.rounded.baqlajon.core.utils.Prefs
import uz.rounded.baqlajon.databinding.ItemContentTextBinding
import uz.rounded.baqlajon.domain.model.main.course.ContentItemModel
import uz.rounded.baqlajon.domain.model.main.course.VideoModel

class TextViewHolder(val binding: ItemContentTextBinding) :
    BaseViewHolder(binding.root) {
    //    private val theme = ThemeManager()
    override fun bindData(data: ContentItemModel) {
        val prefs = Prefs(itemView.context)

        var htmlData = data.content
        //  "<font color='black'>${data.content}"
        htmlData = htmlData.replace("<a", "<a style=\"text-decoration: none;\"", true)
        /* if (theme.currentTheme.id == Theme.NIGHT_THEME) {
            htmlData = "<font color='white'>${
                data.content.replace(
                    "color:black",
                    "color:white",
                    true
                )
            }"
            htmlData = htmlData.replace(
                "<a",
                "<a style=\"color:#ffa140; text-decoration: none;\"",
                true
            )
        } */
        val searchData = prefs.get(prefs.searchContent, "")
        if (searchData.length >= 3)
            htmlData = htmlData.replace(
                searchData,
                "<span style=\"background-color:yellow; color:black;\">$searchData</span>"
            )
        /*binding.textContent.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null)
        val backColor =
            ContextCompat.getColor(itemView.context, theme.currentTheme.backgroundColor)
        binding.textContent.setBackgroundColor(backColor)
        binding.textContent.settings.defaultFontSize =
            prefs.get(prefs.fontSize, ContentConstants.FontSize.DEFAULT)*/
        /*binding.textContent.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
           Html.fromHtml(htmlData, Html.FROM_HTML_MODE_COMPACT)
       } else {
           Html.fromHtml(htmlData)
       }*/

        val formattedHtml = HtmlFormatter.formatHtml(
            HtmlFormatterBuilder().setHtml(htmlData)
                .setImageGetter(HtmlResImageGetter(binding.textContent.context))
        )
        binding.textContent.text = formattedHtml
        val backColor =
            ContextCompat.getColor(itemView.context, R.color.backcolor)
        val textColor =
            ContextCompat.getColor(itemView.context, R.color.main_text_color)
//        binding.textContent.setBackgroundColor(backColor)
        binding.textContent.setTextColor(textColor)
        binding.textContent.textSize =
            prefs.get(prefs.fontSize, Constants.FontSize.DEFAULT).toFloat()
        binding.textContent.movementMethod = LinkMovementMethod.getInstance()
        binding.textContent.linksClickable = true

    }
}