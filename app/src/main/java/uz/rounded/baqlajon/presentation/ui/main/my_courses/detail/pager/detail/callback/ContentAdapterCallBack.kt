package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.callback

import uz.rounded.baqlajon.domain.model.main.course.ContentItemModel


interface ContentAdapterCallBack {
    fun onItemClick(data: ContentItemModel)
}