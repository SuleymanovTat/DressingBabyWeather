package ru.suleymanovtat.dressingbabyweather.utils

import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

class ViewPager2PagerListener(private val pager: ViewPager2) : PagerIndicatorView.PagerListener {
    private val pageChangeListener: PageChangeListener
    private var callback: PagerIndicatorView.PagerCallback? = null
    override fun setPagerCallback(callback: PagerIndicatorView.PagerCallback?) {
        this.callback = callback
        if (callback != null) {
            pager.registerOnPageChangeCallback(pageChangeListener)
            pageChangeListener.onPageScrolled(pager.currentItem, 0f, 0)
        } else {
            pager.unregisterOnPageChangeCallback(pageChangeListener)
        }
    }

    private inner class PageChangeListener : OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            callback?.setItemCount(pager.adapter!!.itemCount)
            callback?.setPageScrolled(position, positionOffset)
        }
    }

    init {
        pageChangeListener = PageChangeListener()
    }
}