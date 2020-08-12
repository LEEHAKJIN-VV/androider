package com.professionalandroid.apps.androider.search.click.result

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.professionalandroid.apps.androider.R
import com.professionalandroid.apps.androider.model.StoreDTO

class SearchResultPageAdapter (private var context: Context) : PagerAdapter(){
    private var markerModelList = ArrayList<StoreDTO>()

    lateinit var layoutInflater: LayoutInflater
    lateinit var postImg: ImageView
    lateinit var storeName: TextView
    lateinit var storeCategory: TextView
    lateinit var storeNumber: TextView
    lateinit var storeLocation: TextView

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return markerModelList.size
    }

    fun addItem(item: StoreDTO){
        markerModelList.add(item)
    }

    fun removeItem(){
        markerModelList.clear()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)

        val rootView = layoutInflater.inflate(R.layout.item_searchresult_marker,container,false)

        postImg = rootView.findViewById(R.id.img_searchresult_marker_postImg)
        storeName = rootView.findViewById(R.id.tv_searchresult_marker_postName)
        storeCategory = rootView.findViewById(R.id.tv_searchresult_marker_category)
        storeNumber = rootView.findViewById(R.id.tv_searchresult_marker_phoneNumber)
        storeLocation = rootView.findViewById(R.id.tv_searchresult_marker_address)

        //postImg.setImageResource(markerModelList[position].id)
        postImg.setImageResource(R.drawable.image03)
        storeName.text = markerModelList[position].name
        storeCategory.text = markerModelList[position].category
        storeNumber.text = markerModelList[position].number
        storeLocation.text = markerModelList[position].address

        rootView.setOnClickListener { // 뷰페이저 클릭 리스너
            if(position == 0)
                Log.d("hakjin","searchresut - 0번 페이지 선택")
            if(position == 1)
                Log.d("hakjin","searchresut - 1번 페이지 선택")
            if(position == 2)
                Log.d("hakjin","searchresut - 2번 페이지 선택")
        }

        container.addView(rootView,0)
        return rootView
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}













